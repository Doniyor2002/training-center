package com.example.lcms_app_full.entity.template;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class AbsEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    //kim qo'shdi
//    @CreatedBy
//    @Column(nullable = false, name = "created_by")
//    private UUID createName;
    //kim o'zgartirdi
//    @LastModifiedBy
//    @Column(nullable = false)
//    private UUID updateName;

    //qachon qo'shgan
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable =false)
    private Date createDate;
//    qachon o'zgartirgan
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date updateDate;
    private boolean status = true;
}
