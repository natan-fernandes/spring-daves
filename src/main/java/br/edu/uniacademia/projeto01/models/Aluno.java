package br.edu.uniacademia.projeto01.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Aluno {
    @Id
    private long id;

    private String nome;
    private String matricula;
    private String email;
    private int idade;
    private int periodo;
    private double nota;
}
