package example.todo.dto;

import example.todo.entity.Item;
import example.todo.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private int id;
    private String name;
    private List<Item> items;

    public UserDto(int id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public static UserDto of(User user) {
        return new UserDto(user.getId(), user.getName(), user.getItems());
    }
}
