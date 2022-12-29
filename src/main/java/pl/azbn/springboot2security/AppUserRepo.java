package pl.azbn.springboot2security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findAllByUsername(String username);
}
