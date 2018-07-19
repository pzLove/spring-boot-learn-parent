package com.pzlove.mybatis.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private Long id; 
    private String name; 
    private Integer age;
    private String city;

}