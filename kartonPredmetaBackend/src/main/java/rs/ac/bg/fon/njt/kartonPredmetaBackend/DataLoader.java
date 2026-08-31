package rs.ac.bg.fon.njt.kartonPredmetaBackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo.UserRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.User;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByKorisnickoIme("admin").isEmpty()) {
            User admin = new User();
            admin.setKorisnickoIme("admin");
            admin.setLozinka(passwordEncoder.encode("admin123"));
            admin.setUloga("ROLE_ADMIN");
            userRepository.save(admin);
            System.out.println(">>> Kreiran podrazumevani admin nalog: admin / admin123");
        }
    }
}