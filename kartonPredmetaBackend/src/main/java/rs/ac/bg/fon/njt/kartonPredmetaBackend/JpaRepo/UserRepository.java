package rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByKorisnickoIme(String korisnickoIme);
}