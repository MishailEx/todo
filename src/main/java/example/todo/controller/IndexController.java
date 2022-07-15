package example.todo.controller;

import example.todo.dto.UserDto;
import example.todo.entity.Item;
import example.todo.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private UserService service;

    public IndexController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", getAuthUser());
        model.addAttribute("items", getAuthUser().getItems());
        return "index";
    }

    @GetMapping("/filter")
    public String filterTask(Model model) {
        List<Item> list = getAuthUser().getItems()
                .stream().filter(item -> item.getStatus().name().equals("ACTIVE")).collect(Collectors.toList());
        model.addAttribute("items", list);
        return "index :: #content";
    }

    @GetMapping("/all")
    public String all(Principal principal, Model model) {
        model.addAttribute("items", getAuthUser().getItems());
        return "index :: #content";
    }

    private UserDto getAuthUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return UserDto.of(service.userFindByName(username));
    }
}
