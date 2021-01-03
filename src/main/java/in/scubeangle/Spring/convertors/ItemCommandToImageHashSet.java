package in.scubeangle.Spring.convertors;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.domains.Image;
import in.scubeangle.Spring.domains.Item;
import in.scubeangle.Spring.repository.ImageRepository;
import in.scubeangle.Spring.repository.ItemRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;

@Component
public class ItemCommandToImageHashSet implements Converter<ItemCommand, HashSet<Image>> {

    @Synchronized
    @Nullable
    @Override
    public HashSet<Image> convert(ItemCommand source) {
        HashSet<Image> imagesForItem = new HashSet<Image>();
        for(MultipartFile multipartFile:source.getFiles()){
            try {
                Image image = new Image();
                image.setImageName(multipartFile.getName());
                image.setPicByte(multipartFile.getBytes());
                image.setType(multipartFile.getContentType());
                imagesForItem.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imagesForItem;
    }
}
