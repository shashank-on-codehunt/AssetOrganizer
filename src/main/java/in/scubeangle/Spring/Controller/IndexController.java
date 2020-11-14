package in.scubeangle.Spring.Controller;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class IndexController {
    @RequestMapping({"/", "index"})
    public String getIndex(Model model){
        Locale locale = LocaleContextHolder.getLocale();
        String langValue = locale.toString();
        System.out.println(langValue);
        model.addAttribute(langValue);
        return "index";
    }
}
