package com.example.bookmanager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(value = MyEntityListener.class)
//@Table(name = "users", indexes = {@Index(columnList = "name")}, uniqueConstraints ={@UniqueConstraint(columnNames = {"email"})})
public class Users implements Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    private Long id;

    private String name;
    private String email;
    // 데이터베이스 객체가 언제 ~했는지 jpa
    //@Column(name = "crtdat")
    //@Column(unique = true)
    //@Column
    @Column(updatable = false)
    private LocalDateTime createdAt;
    //@Column(insertable = false)
    private LocalDateTime updatedAt;

    // DB 에서
    @Enumerated(value = EnumType.STRING)
    private Gender gender;


    public Users(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


//    @PrePersist
//    @PreUpdate
//    @PreRemove
//    @PostPersist
//    @PostUpdate
//    @PostRemove
//    @PostLoad

    // collector 정의
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;


    // 여기서, 어노테이션은 유의미하지만
    // 메소드 이름은 기능적으로는 무관하다. 단지 가독성을 위해 작명된 메소드 이름들이다.
//    @PrePersist
//    public void prePersist(){
////        System.out.println(">>> prePersist");
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }

//    @PostPersist
//    public void postPersist(){
//        System.out.println(">>> postPersist");
//
//    }

//    @PreUpdate
//    public void preUpdate(){
////        System.out.println(">>> preUpdate");
//        this.updatedAt = LocalDateTime.now();
//    }

//    @PostUpdate
//    public void postUpdate(){
//        System.out.println(">>> postUpdate");
//
//    }
//
//    @PreRemove
//    public void preRemove(){
//        System.out.println(">>> preRemove");
//
//    }
//
//    @PostRemove
//    public void postRemove(){
//        System.out.println(">>> postRemove");
//
//    }
//    @PostLoad
//    public void postLoad(){
//        System.out.println(">>> postLoad");
//
//    }
}
