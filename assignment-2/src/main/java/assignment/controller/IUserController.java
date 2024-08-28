package assignment.controller;

import assignment.models.User;

import java.util.List;

public interface IUserController {
  void addUser(User user);

  Boolean checkUser(String rollNumber);

  void deleteUser(String rollNumber);

  List<User> getUserList();
}
