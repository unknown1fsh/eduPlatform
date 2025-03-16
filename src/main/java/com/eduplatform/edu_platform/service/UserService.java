package com.eduplatform.edu_platform.service;

import com.eduplatform.edu_platform.dto.UserDTO;
import com.eduplatform.edu_platform.enums.UserRole;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    List<UserDTO> findUsers(String username, UserRole role); // Specification ile dinamik sorgu
    void deleteUser(Long id);
}