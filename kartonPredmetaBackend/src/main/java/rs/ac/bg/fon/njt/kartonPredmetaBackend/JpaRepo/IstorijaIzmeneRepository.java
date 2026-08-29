package rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.IstorijaIzmene;
import java.util.List;

public interface IstorijaIzmeneRepository extends JpaRepository<IstorijaIzmene, Integer> {
    List<IstorijaIzmene> findByPredmetIdOrderByDatumVremeDesc(int predmetId);
}