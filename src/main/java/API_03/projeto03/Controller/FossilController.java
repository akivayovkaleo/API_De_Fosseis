package API_03.projeto03.Controller;

import API_03.projeto03.Dtos.FossilDto;
import API_03.projeto03.Models.Fossil;
import API_03.projeto03.Services.FossilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fosseis")
public class FossilController {

    private final FossilService fossilService;

    public FossilController(FossilService fossilService) {
        this.fossilService = fossilService;
    }

    @GetMapping
    public ResponseEntity<List<Fossil>> findAll() {
        return ResponseEntity.ok(fossilService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Fossil fossil = fossilService.findById(id);
            return ResponseEntity.ok(fossil);
        } catch (RuntimeException e) {
            return buildErrorResponse("Recurso não encontrado", "Fóssil com ID " + id + " não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Fossil> create(@RequestBody Fossil fossil) {
        Fossil novoFossil = fossilService.create(fossil);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFossil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Fossil fossilAtualizado) {
        try {
            Fossil atualizado = fossilService.update(fossilAtualizado, id);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return buildErrorResponse("Erro ao atualizar", "Não foi possível atualizar o fóssil com ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            fossilService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return buildErrorResponse("Erro ao deletar", "Fóssil com ID " + id + " não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Fossil> create(@RequestBody @Validated FossilDto dto) {
        Fossil created = fossilService.createFromDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
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
