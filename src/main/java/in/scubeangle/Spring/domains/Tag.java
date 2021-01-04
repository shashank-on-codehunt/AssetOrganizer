package in.scubeangle.Spring.domains;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"items"})
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private Set<Item> items;

}
