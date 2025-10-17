package com.enrico.projeto_user.tarefa;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TarefaModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String descricao;
    @Column(length = 50)
    private String titulo;

    @CreationTimestamp
    private LocalDateTime data_criacao;
    private LocalDateTime data_termino;
    private LocalDateTime data_comeco;
    private String prioridade;

    private UUID idUser;
}
