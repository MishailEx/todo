package example.todo.service;

import example.todo.dto.UserDto;
import example.todo.entity.User;
import example.todo.exception.UserExistException;

public interface UserService {

    UserDto addUser(User user) throws UserExistException;

    boolean deleteUser(int id);

    UserDto updateUser(int id, User user);

    User userFindByName(String name);

    User userFindById(int id);
}
