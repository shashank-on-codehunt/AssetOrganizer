package in.scubeangle.Spring.services;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.domains.Item;

import java.util.Set;

public interface ItemService {
    Set<Item> getItems();

    Item findById(long l);

    ItemCommand saveItemCommand(ItemCommand command);
}
