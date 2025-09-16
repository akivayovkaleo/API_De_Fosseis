package API_03.projeto03.Services;

import API_03.projeto03.Models.Periodo;
import API_03.projeto03.Repositories.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodoService {

    private final PeriodoRepository periodoRepository;

    @Autowired
    public PeriodoService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    public List<Periodo> findAll() {
        return periodoRepository.findAll();
    }

    public Periodo findById(Long id) {
        return periodoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Período não encontrado com o ID: " + id));
    }

    public Periodo create(Periodo periodo) {
        return periodoRepository.save(periodo);
    }

    public Periodo update(Periodo periodoAtualizado, Long id) {
        Periodo periodoExistente = findById(id);
        periodoExistente.setNome(periodoAtualizado.getNome());
        return periodoRepository.save(periodoExistente);
    }

    public void delete(Long id) {
        Periodo periodo = findById(id);
        periodoRepository.delete(periodo);
    }
}
