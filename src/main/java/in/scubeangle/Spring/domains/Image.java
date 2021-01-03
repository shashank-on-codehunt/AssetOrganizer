package in.scubeangle.Spring.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"item"})
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String imageName;
    private String type;
    @Lob
    private byte[] picByte;

    @ManyToOne
    private Item item;
}
