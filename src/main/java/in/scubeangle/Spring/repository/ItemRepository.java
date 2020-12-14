package in.scubeangle.Spring.repository;

import in.scubeangle.Spring.domains.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
