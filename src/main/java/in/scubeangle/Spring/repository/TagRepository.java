package in.scubeangle.Spring.repository;

import in.scubeangle.Spring.domains.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    Optional<Tag> findByTagName(String name);
}
