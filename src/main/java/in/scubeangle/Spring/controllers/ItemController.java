package in.scubeangle.Spring.controllers;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.services.ImageService;
import in.scubeangle.Spring.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemController {
    private final ItemService itemService;
    private final ImageService imageService;
    public ItemController(ItemService itemService,ImageService imageService) {
        this.itemService = itemService;
        this.imageService = imageService;
    }
    @RequestMapping("/item")
    public String newItem(Model model){
        model.addAttribute("item", new ItemCommand());
        return "item/itemForm";
    }
    @PostMapping("/item")
    public String saveOrUpdate(@ModelAttribute ItemCommand command,@RequestParam("file") MultipartFile[] file, RedirectAttributes redirectAttributes){
//        ItemCommand savedCommand = itemService.saveItemCommand(command);

        return "index";
    }

    @PostMapping("/item/{id}/image")
    public String saveImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){
        imageService.saveImageFile(id, file);
        return "redirect:/item";
    }
}
