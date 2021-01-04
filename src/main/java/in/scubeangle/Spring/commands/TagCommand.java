package in.scubeangle.Spring.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagCommand {
    private Long id;
    private String tagName;

    public TagCommand(String tagName) {
        tagName=tagName;
    }
}
