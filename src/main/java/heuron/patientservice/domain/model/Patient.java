package heuron.patientservice.domain.model;

import heuron.patientservice.domain.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "patient")
@EntityListeners(AuditingEntityListener.class)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Boolean disease;

    private String imagePath;
    private String imageName;

    @Column(nullable = false)
    private Boolean imageUploaded = false;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Patient(String name, Integer age, Gender gender, boolean disease) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
    }

    public static Patient createPatient(String name, Integer age, Gender gender, boolean disease) {
        return Patient.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .disease(disease)
                .build();
    }

}
