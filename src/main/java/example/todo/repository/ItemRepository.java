package example.todo.repository;

import example.todo.entity.Item;
import example.todo.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    @EntityGraph(attributePaths = {"user"})
    Item findById(int id);
}
