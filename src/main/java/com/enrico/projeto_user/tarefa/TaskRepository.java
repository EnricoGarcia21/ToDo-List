package com.enrico.projeto_user.tarefa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TarefaModel, UUID> {
}
