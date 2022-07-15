package example.todo.service;

import example.todo.entity.Item;

public interface ItemService {

    void addItem(Item item);

    void delItem(String id);

    Item findById(String id);

    void updateItem(String id, String desc);

    void updateStatus(String id);
}
