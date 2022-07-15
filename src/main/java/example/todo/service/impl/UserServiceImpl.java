package example.todo.service.impl;

import example.todo.dto.UserDto;
import example.todo.entity.Status;
import example.todo.entity.User;
import example.todo.exception.UserExistException;
import example.todo.repository.UserRepository;
import example.todo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDto addUser(User user) throws UserExistException {
        User checkUser = userFindByName(user.getName());
        if (checkUser != null) {
            throw new UserExistException("user with the same name already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        User regUser = userRepository.save(user);
        return UserDto.of(regUser);
    }

    @Override
    public boolean deleteUser(int id) {
        return userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(int id, User user) {
        User updateUser = userRepository.findById(id).get();
        updateUser.setName(user.getName());
        updateUser.setPassword(updateUser.getPassword());
        updateUser.setItems(user.getItems());
        updateUser.setStatus(user.getStatus());
        updateUser.setCreated(user.getCreated());
        userRepository.save(user);
        return UserDto.of(updateUser);
    }

    @Override
    public User userFindByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User userFindById(int id) {
        return userRepository.findById(id).get();
    }
}
