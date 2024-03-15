package com.kopnuspos.entitys;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Set;

import java.sql.Date;
import java.util.HashSet;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ASSESSMENT")
public class AssessmentEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "ASSESSMENT_ID", nullable = false, unique = true, updatable = false)
     private UUID assessmentId;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
     private UserEntity user;

     @NotBlank()
     @Size(max = 70)
     @Column(name = "TITLE", nullable = false, unique = true, length = 70)
     private String title;

     @NotBlank()
     @Column(name = "DESCRIPTION", nullable = false)
     private String description;

     @NotNull()
     @Column(name = "DEADLINE", updatable = false)
     @CreationTimestamp
     private Date deadline;


     @Column(name = "CCREATED_AT", updatable = false)
     @CreationTimestamp
     private Date createdAt;

     @Column(name = "UPDATED_AT")
     @UpdateTimestamp
     private Date updatedAt;
     
}
