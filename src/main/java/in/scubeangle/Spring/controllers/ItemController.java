package in.scubeangle.Spring.controllers;

import in.scubeangle.Spring.commands.ItemCommand;
import in.scubeangle.Spring.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ItemController {
    private final ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @RequestMapping("/item")
    public String newItem(Model model){
        model.addAttribute("item", new ItemCommand());
        return "item/itemForm";
    }
    @PostMapping("/item")
    public String saveOrUpdate(@ModelAttribute ItemCommand command){
        ItemCommand savedCommand = itemService.saveItemCommand(command);

        return "index";
    }
}
