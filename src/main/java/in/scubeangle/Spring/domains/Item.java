package in.scubeangle.Spring.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode()
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String parentItemName;
    private int priority;
    private String tag;
    private String userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Image> images = new HashSet<>();
}
