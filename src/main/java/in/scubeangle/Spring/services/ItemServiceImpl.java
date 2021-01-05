package in.scubeangle.Spring.services;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.convertors.ItemCommandToImageHashSet;
import in.scubeangle.Spring.convertors.ItemCommandToItem;
import in.scubeangle.Spring.convertors.ItemToItemCommand;
import in.scubeangle.Spring.domains.Item;
import in.scubeangle.Spring.repository.ImageRepository;
import in.scubeangle.Spring.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final ItemCommandToItem itemCommandToItem;
    private final ItemToItemCommand itemToItemCommand;
    private final ItemCommandToImageHashSet itemCommandToImageHashSet;

    @Override
    public Set<Item> getItems() {
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findAll().iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Item findById(long l) {
        Optional<Item> itemOptional = itemRepository.findById(l);
        if (!itemOptional.isPresent()) {
            throw new RuntimeException("Item not Found");
        }
        return itemOptional.get();
    }

    @Override
    @Transactional
    public ItemCommand saveItemCommand(ItemCommand command) {
        Item detachedItem = itemCommandToItem.convert(command);
        detachedItem.setImages(itemCommandToImageHashSet.convert(command));
        Item savedItem = itemRepository.save(detachedItem);
        log.debug("Saved Item : " + savedItem.getId());
        return itemToItemCommand.convert(savedItem);
    }
}
