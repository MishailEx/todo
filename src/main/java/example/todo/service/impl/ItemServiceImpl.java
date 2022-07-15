package example.todo.service.impl;

import example.todo.entity.Item;
import example.todo.entity.Status;
import example.todo.repository.ItemRepository;
import example.todo.repository.UserRepository;
import example.todo.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private UserRepository userRepository;

    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void delItem(String id) {
        itemRepository.deleteById(Integer.parseInt(id));
    }

    @Override
    public Item findById(String id) {
        return itemRepository.findById(Integer.parseInt(id));
    }

    @Override
    public void updateItem(String id, String desc) {
        Item item = itemRepository.findById(Integer.parseInt(id));
        item.setDescription(desc);
        item.setUpdated(new Date());
        itemRepository.save(item);
    }

    @Override
    public void updateStatus(String id) {
        Item item = itemRepository.findById(Integer.parseInt(id));
        String status = item.getStatus().name();
        item.setStatus(status.equals("ACTIVE") ? Status.NOT_ACTIVE : Status.ACTIVE);
        itemRepository.save(item);
    }
}
