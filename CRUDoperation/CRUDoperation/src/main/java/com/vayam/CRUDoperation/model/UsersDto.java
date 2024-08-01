package com.vayam.CRUDoperation.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UsersDto {
    
    @NotEmpty(message="the name is required")
    private String name;

    @NotEmpty(message="the email is required")
    private String email;
    // @NotNull(message = "Please enter phone number")
    private Long phone;
    @NotEmpty(message="the address is required")
    private String address;

    @Size(min=5,message="the discription should be at least 5 characters")
    @Size(max=20,message="the discription cannnot be exceed 20 characters")
    private String description;
}
