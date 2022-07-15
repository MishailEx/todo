package example.todo.controller;

import example.todo.entity.User;
import example.todo.exception.UserExistException;
import example.todo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegController {
    private UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String regPage() {
        return "registration.html";
    }

    @PostMapping
    public String addUser(@RequestParam ("name") String name, @RequestParam("password") String password) throws UserExistException {
        userService.addUser(new User(name, password));
        return "redirect:/login";
    }

    @ExceptionHandler(UserExistException.class)
    public String handleException(UserExistException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "registration";
    }
}
