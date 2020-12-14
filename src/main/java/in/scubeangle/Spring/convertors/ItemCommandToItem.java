package in.scubeangle.Spring.convertors;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.domains.Item;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ItemCommandToItem implements Converter<ItemCommand, Item> {

    @Synchronized
    @Nullable
    @Override
    public Item convert(ItemCommand source) {
        if (source == null) {
            return null;
        }
        final Item item = new Item();
        item.setId(source.getId());
        item.setItemName(source.getItemName());
        item.setParentItemName(source.getParentItemName());
        item.setPriority(source.getPriority());
        item.setTag(source.getTag());
        return item;
    }
}
