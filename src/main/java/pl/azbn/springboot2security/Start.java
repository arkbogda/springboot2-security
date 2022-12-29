package pl.azbn.springboot2security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Start {

    private PasswordEncoder passwordEncoder;

    private AppUserRepo appUserRepo;

    @Autowired
    public Start(PasswordEncoder passwordEncoder, AppUserRepo appUserRepo) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepo = appUserRepo;
    }

    @PostConstruct
    public void initialize() {
        AppUser appUser = new AppUser();
        appUser.setUsername("Przemek");
        appUser.setPassword(passwordEncoder.encode("Przemek123"));
        appUserRepo.save(appUser);
    }
}
