package com.enrico.projeto_user.tarefa;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "task")
public class TaskRestController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping(value = "create")
    public ResponseEntity<?> create(@RequestBody TarefaModel tarefa, HttpServletRequest request) {

        var idUser = request.getAttribute("idUser");
        tarefa.setId((UUID) idUser);

        var currentDate = LocalDateTime.now();

        if (currentDate.isAfter(tarefa.getData_comeco()) || currentDate.isAfter(tarefa.getData_termino())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A data de início/data de término deve ser maior que a data atual");
        }

        if (tarefa.getData_comeco().isAfter(tarefa.getData_termino())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A data de início deve ser menor do que a data de término");
        }

        var task = this.taskRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping(value = "list-task")
    public List<TarefaModel> getAll(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

}
