package example.todo.controller;

import example.todo.dto.UserDto;
import example.todo.entity.Item;
import example.todo.service.ItemService;
import example.todo.service.UserService;
import example.todo.service.impl.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/item")
public class ItemController {
    private ItemService itemService;
    private UserService userService;

    public ItemController(ItemServiceImpl itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping("/del/{id}")
    public String deleteDocument(@PathVariable(value = "id") String id) {
        itemService.delItem(id);
        return "redirect:/";
    }

    @PostMapping
    public String createItem(@RequestParam("id") String id, @RequestParam("description") String desc) {
        itemService.addItem(Item.of(desc, userService.userFindById(Integer.parseInt(id))));
        return "redirect:/";
    }

    @PostMapping("/{id}")
    public String updateItem(@PathVariable(value = "id") String id, @RequestParam("description") String desc) {
        itemService.updateItem(id, desc);
        return "redirect:/";
    }

    @GetMapping("/status")
    public String updateStatus(@RequestParam("id") String id) {
        itemService.updateStatus(id);
        return "redirect:/";
    }

    @GetMapping()
    public String item(Principal principal, Model model) {
        model.addAttribute("user", UserDto.of(userService.userFindByName(principal.getName())));
        return "item/create";
    }

    @GetMapping("/{id}")
    public String itemEdit(@PathVariable(value = "id") String id, Model model, Principal principal) {
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("user", UserDto.of(userService.userFindByName(principal.getName())));
        return "item/edit";
    }
}
