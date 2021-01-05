package in.scubeangle.Spring.convertors;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.domains.Image;
import in.scubeangle.Spring.domains.Item;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class ItemToItemCommand  implements Converter<Item, ItemCommand> {

    @Synchronized
    @Nullable
    @Override
    public ItemCommand convert(Item source) {
        final ItemCommand command = new ItemCommand();
        command.setId(source.getId());
        command.setItemName(source.getItemName());
        command.setParentItemName(source.getParentItemName());
        command.setPriority(source.getPriority());
        command.setTag(source.getTags().stream().map(o -> o.getTagName()).collect(Collectors.joining(";")));
        return command;
    }
}
