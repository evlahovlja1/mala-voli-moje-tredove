package ba.unsa.etf.rpr.resttest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final String ret = "Hello, ";


    @GetMapping("/greet")
    public Person greet(@RequestParam(name = "name", defaultValue = "World", required = false) String ime) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Person(ret+ime, 18);
    }
}
