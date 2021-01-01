package in.scubeangle.Spring.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemCommand {
    private Long id;

    private String itemName;
    private String parentItemName;
    private int priority;
    private String tag;
    private String userId;
    private Byte[] image;
}
