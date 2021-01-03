package in.scubeangle.Spring.repository;

import in.scubeangle.Spring.domains.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
