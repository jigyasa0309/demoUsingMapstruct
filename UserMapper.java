package com.example.demo.mapper;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

  // Mapping from User entity to UserResponseDto
  @Mapping(target = "name", source = "user.name")
  @Mapping(target = "email", source = "user.email")
  @Mapping(target = "address", source = "user.address")
  UserResponseDto entityToDto(User user, @Context CycleAvoidingMappingContext context);

  // Mapping from UserRequestDto to User entity
  @Mapping(target = "name", source = "name")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "address", source = "address")
  User dtoToEntity(UserRequestDto userRequestDto, @Context CycleAvoidingMappingContext context);


  // Mapping from List of User entities to List of UserResponseDto
  default List<UserResponseDto> entityToDto(List<User> users, @Context CycleAvoidingMappingContext context) {
    return users.stream()
            .map(user -> entityToDto(user, context))
            .collect(Collectors.toList());
  }
  }
