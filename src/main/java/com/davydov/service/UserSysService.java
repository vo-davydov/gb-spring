package com.davydov.service;

import java.util.List;
import java.util.Optional;
import com.davydov.entity.User;
import com.davydov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSysService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserSysService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<List<User>> getUsers() {
    return Optional.of(userRepository.findAll());
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public Optional<User> findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

}
