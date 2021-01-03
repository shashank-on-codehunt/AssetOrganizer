package in.scubeangle.Spring.convertors;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.domains.Image;
import in.scubeangle.Spring.domains.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;

public class ImageToItemCommand implements Converter<HashSet<Image>,MultipartFile[]> {

    @Override
    public MultipartFile[] convert(HashSet<Image> source) {
        return new MultipartFile[0];
    }
}
