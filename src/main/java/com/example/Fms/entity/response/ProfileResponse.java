package com.example.Fms.entity.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileResponse {
    private Integer id;
    private String username;
    private String email;
    private String phoneNumber;
    private String role;

}
