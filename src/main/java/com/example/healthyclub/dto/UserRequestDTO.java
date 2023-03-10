package com.example.healthyclub.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {


    private String name;
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private int height;
    private int weight;
    private String gender;
    private String phone;
    private int age;
    private List<String> exerciseType;



}
