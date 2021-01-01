package in.scubeangle.Spring.services;

import in.scubeangle.Spring.domains.Item;
import in.scubeangle.Spring.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final ItemRepository itemRepository;

    public ImageServiceImpl(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(String itemId, MultipartFile file) {
        try {
            Item item = itemRepository.findById(Long.parseLong(itemId)).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }
            item.setImage(byteObjects);
            itemRepository.save(item);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
