package API_03.projeto03.Services;

import API_03.projeto03.Models.Fossil;
import API_03.projeto03.Repositories.FossilRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FossilService {

    private final FossilRepository fossilRepository;

    public FossilService(FossilRepository fossilRepository) {
        this.fossilRepository = fossilRepository;
    }

    public List<Fossil> findAll() {
        return fossilRepository.findAll();
    }

    public Fossil findById(Long id) {
        return fossilRepository.findById(id).orElseThrow();
    }

    public Fossil create(Fossil fossil) {
        return fossilRepository.save(fossil);
    }

    public Fossil update(Fossil fossil, Long id) {
        Fossil existente = findById(id);
        fossil.setId(existente.getId());
        return fossilRepository.save(fossil);
    }

    public void delete(Long id) {
        fossilRepository.deleteById(id);
    }
}
