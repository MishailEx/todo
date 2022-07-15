package example.todo.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "name", "description"})
public class Item extends BaseEntity {

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Item(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public static Item of(String desc, User user) {
        Item item = new Item();
        item.setUser(user);
        item.setDescription(desc);
        item.setStatus(Status.ACTIVE);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        return item;
    }

    @Override
    public String toString() {
        return "Item{ description='" + description + '}';
    }
}
