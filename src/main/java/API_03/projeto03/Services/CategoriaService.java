package API_03.projeto03.Services;

import API_03.projeto03.Models.Categoria;
import API_03.projeto03.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria create(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public Categoria update(Categoria categoriaAtualizada, Long id) {
        Categoria categoria = findById(id);
        categoria.setNome(categoriaAtualizada.getNome());
        return categoriaRepository.save(categoria);
    }

    public void delete(Long id) {
        Categoria categoria = findById(id);
        categoriaRepository.delete(categoria);
    }
}
