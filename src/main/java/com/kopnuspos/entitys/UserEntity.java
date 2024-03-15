package com.kopnuspos.entitys;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@Table(name = "USERS")
public class UserEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "USER_ID", nullable = false, unique = true, updatable = false)
     private UUID userId;

     @NotBlank()
     @Size(max = 70)
     @Column(name = "USERNAME", nullable = false, unique = true, length = 70)
     private String username;

     @NotBlank()
     @Size(min = 6)
     @Column(name = "PASSWORD", nullable = false)
     private String password;

     @Column(name = "CCREATED_AT", updatable = false)
     @CreationTimestamp
     private Date createdAt;

     @Column(name = "UPDATED_AT")
     @UpdateTimestamp
     private Date updatedAt;

     @JsonIgnore
     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
     private Set<AssessmentEntity> assessments = new HashSet<>();
}
