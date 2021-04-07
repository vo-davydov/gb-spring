package com.davydov.config.security;

import java.util.Optional;
import java.util.stream.Collectors;
import com.davydov.entity.User;
import com.davydov.service.UserSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private UserSysService userSysService;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserSysService userSysService, PasswordEncoder passwordEncoder) {
    this.userSysService = userSysService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Optional<User> userOptional = userSysService.findByUserName(s);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      return new org.springframework.security.core.userdetails.User(user.getUserName(), passwordEncoder.encode(user.getPassword()),
        user.getRole().stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList()));
    }
    throw new UsernameNotFoundException("Пользователь с таким логин не найден");
  }
}
