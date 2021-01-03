package in.scubeangle.Spring.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ItemCommand {
    private Long id;
    private MultipartFile[] files;
    private String itemName;
    private String parentItemName;
    private int priority;
    private String tag;
    private String userId;
}
