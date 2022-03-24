package budget.adapters;

import budget.application.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TestService testService;

    public HomeController(TestService testService) {
        this.testService = testService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/api/test")
    public List<String> facts() {
        logger.info("Serving Facts");
        return testService.tests()
                .stream()
                .map(p -> p.getNom())
                .collect(toList());
    }
}
