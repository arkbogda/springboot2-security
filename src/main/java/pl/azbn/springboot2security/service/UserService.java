package pl.azbn.springboot2security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.azbn.springboot2security.entity.AppUser;
import pl.azbn.springboot2security.repo.AppUserRepo;

@Service
public class UserService {

    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public void addNewUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepo.save(user);
    }
}
