package org.example.jsp_shopping_website.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jsp_shopping_website.entity.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Users", indexes = @Index(name = "idx_email", columnList = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String firstName;
    private String lastName;

}
