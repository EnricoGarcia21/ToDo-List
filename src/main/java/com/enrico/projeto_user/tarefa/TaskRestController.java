package com.enrico.projeto_user.tarefa;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "task")
public class TaskRestController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping(value = "create")
    public TarefaModel create(@RequestBody TarefaModel tarefa) {

        var task = this.taskRepository.save(tarefa);
        return task;

    }
}
