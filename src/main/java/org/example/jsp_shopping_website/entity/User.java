package org.example.jsp_shopping_website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jsp_shopping_website.entity.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;

}
