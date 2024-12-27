package com.example.demo.service;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void deleteUser(int id);

    public UserResponseDto addUser(UserRequestDto userRequestDto);

    public List<UserResponseDto> getAllUsers();

    public UserResponseDto getUserById(int id);
}
