package com.davydov.controller.mvc;

import java.util.List;
import java.util.Optional;
import com.davydov.entity.User;
import com.davydov.service.UserSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserMvcController {
  private UserSysService userSysService;

  @Autowired
  public UserMvcController(UserSysService userSysService) {
    this.userSysService = userSysService;
  }

  @GetMapping
  public String getUsers(Model model) {
    Optional<List<User>> userList = userSysService.getUsers();
    if (userList.isPresent()) {
      userList.ifPresent(users -> model.addAttribute("users", userList.get()));
      return "user";
    }
    return "index";
  }

  @GetMapping("/{id}")
  public String deleteUser(@PathVariable Long id) {
    userSysService.deleteUser(id);
    return "redirect:user";
  }
}
