package com.example.lcms_app_full.service;

import com.example.lcms_app_full.component.DateFormatter;
import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.dto.ResStudentDTO;
import com.example.lcms_app_full.dto.ResStudentForGroupDto;
import com.example.lcms_app_full.dto.StudentDTO;
import com.example.lcms_app_full.entity.*;
import com.example.lcms_app_full.entity.enums.StudentStatus;
import com.example.lcms_app_full.exception.ResourceNotFoundException;
import com.example.lcms_app_full.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DateFormatter dateFormatter;
    private final GroupRepository groupRepository;
    private final FilialRepository filialRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public ApiResponse add(StudentDTO studentDTO) throws ParseException {
        Student student=new Student();
        Group group = groupRepository.findById(studentDTO.getGroupId()).orElseThrow(() -> new ResourceNotFoundException("Filial", "id", studentDTO.getGroupId()));
        for (StudentStatus status : StudentStatus.values()) {
            if (status==StudentStatus.valueOf(studentDTO.getStudentStatus())){
                student.setStudentStatus(status);
                break;
            }
        }
        student.setFullName(studentDTO.getFullName());
        student.setPhone(studentDTO.getPhone());
        student.setMale(studentDTO.getMale());
        student.setDateOfBirth(dateFormatter.stringToDate(studentDTO.getDateOfBirth()));
        student.setPassportNo(studentDTO.getPassportNo());
        student.setPassportGivenBy(studentDTO.getPassportGivenBy());
        student.setPassportDateOfIssue(dateFormatter.stringToDate(studentDTO.getPassportDateOfIssue()));
        student.setGroup(new ArrayList<>(List.of(group)));
        student.setAddress(studentDTO.getAddress());
        Student save = studentRepository.save(student);
        if (save!=null){
            return ApiResponse.builder().data(toDTO(save)).message("Added").success(true).build();
        }
        return ApiResponse.builder().message("Something went wrong").success(false).build();
    }

    public ApiResponse<Student> getOne(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        return new ApiResponse("Student", toDTO(student), true);
    }


    public ApiResponse remove(UUID id) {
        if (!studentRepository.existsById(id)) return new ApiResponse("Bunday id li student yo'q", false);
        studentRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }

    public ApiResponse getAll(int page, int size, String search, String filialName, String courseName,String teacherName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> data = null;
        if (search.equals("") && filialName.equals("") && courseName.equals("")&&teacherName.equals("")) {
            data = studentRepository.findAll(pageable);
        }
//        else {
//            data = studentRepository.f(filialName, courseName, search,teacherName,pageable);
//        }
        return ApiResponse.builder().data(toDTOPage(data)).message("OK").success(true).build();
    }

    public ApiResponse edit(UUID id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student", "id", id));
        Group group = groupRepository.findById(studentDTO.getGroupId()).orElseThrow(() -> new ResourceNotFoundException("Filial", "id", studentDTO.getGroupId()));
        student.setFullName(studentDTO.getFullName());
        student.setAddress(studentDTO.getAddress());
        student.setBalance(studentDTO.getBalance());
        student.setStudentStatus(StudentStatus.valueOf(studentDTO.getStudentStatus()));
        List<Group> groupList = student.getGroup();
        groupList.add(group);
        student.setGroup(groupList);
        student.setPhone(studentDTO.getPhone());
        Student save = studentRepository.save(student);
        return ApiResponse.builder().success(true).message("Edited!").data(toDTO(save)).build();
    }


    // Student -> ResStudentDTO
    public ResStudentDTO toDTO(Student student) {
        List<ResStudentForGroupDto> list=new ArrayList<>();
        for (Group group : student.getGroup()) {
            list.add(ResStudentForGroupDto.builder()
                    .filialName(group.getFilial().getName())
                    .courseName(group.getCourse().getName())
                    .groupName(group.getName()).build());
        }
//        List<ResStudentForGroupDto> groupDtoList = student.getGroup().stream().map(group -> new ResStudentForGroupDto()).collect(Collectors.toList());
        return ResStudentDTO.builder()
                .fullName(student.getFullName())
                .phone(student.getPhone())
                .address(student.getAddress())
                .studentStatus(String.valueOf(student.getStudentStatus()))
                .male(student.getMale())
                .groups(list)
                .build();
    }

    //StudentList -> ResStudentDTOPAGE
    public Page<ResStudentDTO> toDTOPage(Page<Student> students) {
        List<ResStudentDTO> collect = students.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
    
}
