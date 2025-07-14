package org.example.userapi;

import jakarta.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Country country;

    public Users(String firstName, Integer age, Country country) {
        this.firstName = firstName;
        this.age = age;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Users(){}
}
