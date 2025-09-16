package API_03.projeto03.Controller;

import API_03.projeto03.Models.Periodo;
import API_03.projeto03.Services.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/periodos")
public class PeriodoController {

    private final PeriodoService periodoService;

    @Autowired
    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @GetMapping
    public ResponseEntity<List<Periodo>> findAll() {
        List<Periodo> periodos = periodoService.findAll();
        return ResponseEntity.ok(periodos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Periodo> findById(@PathVariable Long id) {
        try {
            Periodo periodo = periodoService.findById(id);
            return ResponseEntity.ok(periodo);
        } catch (RuntimeException e) {
            return buildErrorResponse("Recurso não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Periodo> create(@RequestBody Periodo periodo) {
        Periodo novoPeriodo = periodoService.create(periodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPeriodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Periodo> update(@PathVariable Long id, @RequestBody Periodo periodoAtualizado) {
        try {
            Periodo atualizado = periodoService.update(periodoAtualizado, id);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return buildErrorResponse("Erro ao atualizar", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            periodoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return buildErrorResponse("Erro ao deletar", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity buildErrorResponse(String error, String mensagem, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("mensagem", mensagem);

        return ResponseEntity.status(status).body(body);
    }
}
