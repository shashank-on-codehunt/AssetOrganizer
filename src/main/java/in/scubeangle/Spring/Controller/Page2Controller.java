package in.scubeangle.Spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Page2Controller {
    @RequestMapping({"/Page2"})
    public String getIndex(Model model){

        return "Page2";
    }
}
