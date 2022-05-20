package edu.eci.ieti.betsgo.entity.model;

import edu.eci.ieti.betsgo.entity.dto.UserDto;

public class User {

    private String name;
    private String lastName;
    private String username;
    private UserDto dto;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDto getUserDto() {
        return dto;
    }

    public void setUserDto(UserDto userDto) {
        this.dto = userDto;
    }
}