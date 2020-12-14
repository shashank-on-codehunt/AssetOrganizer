package in.scubeangle.Spring.convertors;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.domains.Item;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemCommand  implements Converter<Item, ItemCommand> {

    @Synchronized
    @Nullable
    @Override
    public ItemCommand convert(Item source) {
        if (source == null) {
            return null;
        }
        final ItemCommand command = new ItemCommand();
        command.setId(source.getId());
        command.setItemName(source.getItemName());
        command.setParentItemName(source.getParentItemName());
        command.setPriority(source.getPriority());
        command.setTag(source.getTag());
        return command;
    }
}
