package com.example.lcms_app_full.service;

import com.example.lcms_app_full.dto.ApiResponse;
import com.example.lcms_app_full.dto.GroupDTO;
import com.example.lcms_app_full.dto.ResGroupDTO;
import com.example.lcms_app_full.entity.*;
import com.example.lcms_app_full.exception.ResourceNotFoundException;
import com.example.lcms_app_full.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final FilialRepository filialRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public ApiResponse add(GroupDTO groupDTO) {
        Filial filial = filialRepository.findById(groupDTO.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("Filial", "id", groupDTO.getFilialId()));
        Course course = courseRepository.findById(groupDTO.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course", "id", groupDTO.getCourseId()));
        Group group = new Group();
        group.setCourse(course);
        group.setFilial(filial);

        //nomini saqlashdan oldin tekshirish shu filialda shunaqa oldin guruh ochilmaganmi?
        if (groupRepository.existsByNameAndFilial_IdAndCourse_Id(groupDTO.getName(), groupDTO.getFilialId(), groupDTO.getCourseId()))
            throw new RuntimeException("Bunday nomli guruh mavjud!!!");
        group.setName(groupDTO.getName());
        Group save = groupRepository.save(group);
        if (save!=null){
            return ApiResponse.builder().data(toDTO(save)).message("Added").success(true).build();
        }
        return ApiResponse.builder().message("Something went wrong").success(false).build();
    }

    public ApiResponse<Group> getOne(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group", "id", id));
        return new ApiResponse("Group", toDTO(group), true);
    }


    public ApiResponse remove(Long id) {
        if (!groupRepository.existsById(id)) return new ApiResponse("Bunday id li guruh yo'q", false);
        groupRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }

    public ApiResponse getAll(int page, int size, String search, String filialName, String courseName,String teacherName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Group> data = null;
        if (search.equals("") && filialName.equals("") && courseName.equals("")&&teacherName.equals("")) {
            data = groupRepository.findAll(pageable);
        }
        else {
            data = groupRepository.findAllByFilial_NameContainingIgnoreCaseAndCourse_NameContainingIgnoreCaseAndNameContainingIgnoreCaseAAndTeacher_FullNameContainingIgnoreCase(filialName, courseName, search,teacherName,pageable);
        }
        return ApiResponse.builder().data(toDTOPage(data)).message("OK").success(true).build();
    }

    public ApiResponse edit(Long id, GroupDTO groupDTO) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("group", "id", id));
        Filial filial = filialRepository.findById(groupDTO.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("Filial", "id", groupDTO.getFilialId()));
        Teacher teacher = teacherRepository.findById(groupDTO.getTeacherId()).orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", groupDTO.getTeacherId()));
        Course course = courseRepository.findById(groupDTO.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course", "id", groupDTO.getCourseId()));
        group.setName(groupDTO.getName());
        group.setCourse(course);
        group.setFilial(filial);
        group.setTeacher(teacher);
        Group save = groupRepository.save(group);
        return ApiResponse.builder().success(true).message("Edited!").data(toDTO(save)).build();
    }
    public ApiResponse addTeacherToGroup(Long id, UUID teacherId) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("group", "id",id));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Teacher", "id",teacherId));
        group.setTeacher(teacher);
        Group save = groupRepository.save(group);
        return ApiResponse.builder().message("Saved").success(true).data(toDTO(save)).build();
    }

    // Group -> ResGroupDTO
    public ResGroupDTO toDTO(Group group) {
        List<Group> groups=new ArrayList<>(Arrays.asList(group));
        List<Student> students = studentRepository.findAllByGroup(groups);
        List<String> list=new ArrayList<>();
        for (Student student : students) {list.add(student.getFullName());}
        return ResGroupDTO.builder()
                .name(group.getName())
                .courseName(group.getCourse().getName())
                .filialName(group.getFilial().getName())
                .teacherName(group.getTeacher().getFullName())
                .StudentName(list)
                .build();
    }

    //GroupList -> ResGroupDTOPAGE
    public Page<ResGroupDTO> toDTOPage(Page<Group> groups) {
        List<ResGroupDTO> collect = groups.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }


}

