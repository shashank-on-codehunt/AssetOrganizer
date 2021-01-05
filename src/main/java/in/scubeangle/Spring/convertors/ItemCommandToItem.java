package in.scubeangle.Spring.convertors;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.commands.TagCommand;
import in.scubeangle.Spring.domains.Item;
import in.scubeangle.Spring.domains.Tag;
import in.scubeangle.Spring.repository.TagRepository;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ItemCommandToItem implements Converter<ItemCommand, Item> {

    private final TagCommandToTag tagCommandToTag;
    private final TagRepository tagRepository;

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
        item.setTags(Arrays.stream(source.getTag().split(";")).map(o -> tagRepository.findByTagName(o).orElseGet(() -> tagCommandToTag.convert(new TagCommand(o)))).collect(Collectors.toSet()));
        return item;
    }
}
