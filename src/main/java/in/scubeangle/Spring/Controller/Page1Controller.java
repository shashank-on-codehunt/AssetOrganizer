package in.scubeangle.Spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Page1Controller {
    @RequestMapping({"/Page1"})
    public String getIndex(Model model){

        return "Page1";
    }
}
