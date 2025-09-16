package API_03.projeto03.Repositories;

import API_03.projeto03.Models.Fossil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FossilRepository extends JpaRepository<Fossil, Long> {
}
