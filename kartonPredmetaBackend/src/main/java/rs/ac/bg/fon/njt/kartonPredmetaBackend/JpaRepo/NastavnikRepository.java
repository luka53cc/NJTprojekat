package rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Nastavnik;
import java.util.Optional;

public interface NastavnikRepository extends JpaRepository<Nastavnik, Integer> {
    Optional<Nastavnik> findByEmail(String email);
}