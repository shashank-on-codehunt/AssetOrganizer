package in.scubeangle.Spring.convertors;

import in.scubeangle.Spring.commands.TagCommand;
import in.scubeangle.Spring.domains.Tag;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TagToTagCommand implements Converter<Tag, TagCommand> {

    @Synchronized
    @Nullable
    @Override
    public TagCommand convert(Tag source) {
        if (source == null) {
            return null;
        }
        final TagCommand tagCommand = new TagCommand();
        tagCommand.setId(source.getId());
        tagCommand.setTagName(source.getTagName());
        return tagCommand;
    }
}
