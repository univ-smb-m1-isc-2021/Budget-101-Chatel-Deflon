package adapters;

import application.TestService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class HomeController {
    private final Logger log = getLogger(this.getClass());

    private final TestService testService;

    public HomeController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("facts", testService.tests());

        log.info("Home page returned - testing logstash integration");

        return "home";
    }
}
