package pl.azbn.springboot2security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestApi {

    @GetMapping("/forAll")
    public String forAll() {
        return "You have been logoed out";
    }

    @GetMapping("/forUser")
    public String forUser(Principal principal) {
        return "Hello User: " + principal.getName();
    }

    @GetMapping("/forAdmin")
    public String forAdmin(Principal principal) {
        return "Hello Admin: " + principal.getName();
    }

}
