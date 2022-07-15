package example.todo.security;
import example.todo.exception.NotFoundUserException;
import example.todo.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceImpl userService;

    public UserDetailsServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        example.todo.entity.User user = userService.userFindByName(name);

        if (user == null) {
            throw new NotFoundUserException("User not found");
        }
        User userJwt = new User(user.getName(), user.getPassword(), emptyList());
        log.info("loadUserByUsername - {} completed", userJwt);
        return userJwt;
    }
}