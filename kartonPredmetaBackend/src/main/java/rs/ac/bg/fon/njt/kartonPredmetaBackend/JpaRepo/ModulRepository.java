package rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.Modul;
import java.util.List;

/**
 *
 * @author Luka
 */
public interface ModulRepository extends JpaRepository<Modul, Integer> {
    List<Modul> findByStudijskiProgramId(int studijskiProgramId);
}