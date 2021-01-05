package in.scubeangle.Spring.convertors;

import in.scubeangle.Spring.commands.TagCommand;
import in.scubeangle.Spring.domains.Tag;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TagCommandToTag implements Converter<TagCommand, Tag> {

    @Synchronized
    @Nullable
    @Override
    public Tag convert(TagCommand command) {
        if (command == null) {
            return null;
        }
        final Tag tag = new Tag();
        tag.setId(command.getId());
        tag.setTagName(command.getTagName());
        return tag;
    }
}
