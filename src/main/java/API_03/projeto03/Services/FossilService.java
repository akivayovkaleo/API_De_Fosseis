package API_03.projeto03.Services;

import API_03.projeto03.Dtos.FossilDto;
import API_03.projeto03.Models.Categoria;
import API_03.projeto03.Models.Fossil;
import API_03.projeto03.Models.Periodo;
import API_03.projeto03.Repositories.CategoriaRepository;
import API_03.projeto03.Repositories.FossilRepository;
import API_03.projeto03.Repositories.PeriodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FossilService {

    private final FossilRepository fossilRepository;
    private final CategoriaRepository categoriaRepository;
    private final PeriodoRepository periodoRepository;

    public FossilService(FossilRepository fossilRepository, CategoriaRepository categoriaRepository, PeriodoRepository periodoRepository) {
        this.fossilRepository = fossilRepository;
        this.categoriaRepository = categoriaRepository;
        this.periodoRepository = periodoRepository;
    }

    public List<Fossil> findAll() {
        return fossilRepository.findAll();
    }

    public Fossil findById(Long id) {
        return fossilRepository.findById(id).orElseThrow(() -> new RuntimeException("Fóssil não encontrado"));
    }

    public Fossil create(Fossil fossil) {
        return fossilRepository.save(fossil);
    }

    public Fossil createFromDto(FossilDto dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Periodo periodo = periodoRepository.findById(dto.periodoId())
                .orElseThrow(() -> new RuntimeException("Período não encontrado"));

        Fossil fossil = new Fossil();
        fossil.setNome(dto.nome());
        fossil.setIdade(dto.idade());
        fossil.setDescricao(dto.descricao());
        fossil.setCategoria(categoria);
        fossil.setPeriodo(periodo);

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
