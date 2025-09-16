package API_03.projeto03.Repositories;

import API_03.projeto03.Models.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
}
