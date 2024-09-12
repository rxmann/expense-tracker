package com.codex.DTO;

import com.codex.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;

    public UserDTO (User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

}
