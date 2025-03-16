package com.eduplatform.edu_platform.service.impl;

import com.eduplatform.edu_platform.dto.UserDTO;
import com.eduplatform.edu_platform.entity.User;
import com.eduplatform.edu_platform.enums.UserRole;
import com.eduplatform.edu_platform.exception.CustomException;
import com.eduplatform.edu_platform.mapper.UserMapper;
import com.eduplatform.edu_platform.repository.UserRepository;
import com.eduplatform.edu_platform.service.UserService;
import com.eduplatform.edu_platform.specification.UserSpecification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, 
                           UserMapper userMapper, 
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new CustomException("Username already exists", "USER_EXISTS");
        }
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", "USER_NOT_FOUND"));
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", "USER_NOT_FOUND"));
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTOList(users);
    }

    @Override
    public List<UserDTO> findUsers(String username, UserRole role) {
        List<User> users = userRepository.findAll(UserSpecification.filterUsers(username, role));
        return userMapper.toDTOList(users);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", "USER_NOT_FOUND"));
        userRepository.delete(user);
    }
}