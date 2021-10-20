package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.service.CodeService;


@Controller
public class WebController {

    @Autowired
    private CodeService service;

    @GetMapping("/code/{N}")
    public String getWeb(Model model, @PathVariable String N) {
        model.addAttribute("sharingCode", service.findSharingCodeById(N));
        return "output";
    }

    @GetMapping("/code/new")
    public String getWebNew() {
        return "input";
    }

    @GetMapping("/code/latest")
    public String getLatest(Model model) {
        model.addAttribute("latestList", service.getLatest());
        return "latest";
    }
}
