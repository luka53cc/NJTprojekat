package rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Predmet;
import java.util.Optional;

public interface PredmetRepository extends JpaRepository<Predmet, Integer>, JpaSpecificationExecutor<Predmet> {
    Optional<Predmet> findBySifra(String sifra);
    boolean existsBySifra(String sifra);
}