package com.example.demo.service;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.CycleAvoidingMappingContext;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    // Method to delete a user by ID
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // Method to add a new user
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        User user = userMapper.dtoToEntity(userRequestDto, new CycleAvoidingMappingContext());
        User savedUser = userRepository.save(user);
        return userMapper.entityToDto(savedUser, new CycleAvoidingMappingContext());
    }

    // Method to get all users
    @Override
    public List<UserResponseDto> getAllUsers() {
            try {
                // Fetch all users from the repository
                List<User> users = userRepository.findAll();
                return userMapper.entityToDto(users, new CycleAvoidingMappingContext());
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving users", e);
            }
    }

    // Method to get a user by ID
    @Override
    public UserResponseDto getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(user -> userMapper.entityToDto(user, new CycleAvoidingMappingContext()))
                .orElse(null);
    }


}
