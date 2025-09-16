package API_03.projeto03.Controller;

import API_03.projeto03.Models.Categoria;
import API_03.projeto03.Services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.findById(id);
            return ResponseEntity.ok(categoria);
        } catch (RuntimeException e) {
            return buildErrorResponse("Recurso não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.create(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada) {
        try {
            Categoria atualizada = categoriaService.update(categoriaAtualizada, id);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return buildErrorResponse("Erro ao atualizar", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            categoriaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return buildErrorResponse("Erro ao deletar", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String error, String mensagem, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("mensagem", mensagem);

        return ResponseEntity.status(status).body(body);
    }
}
