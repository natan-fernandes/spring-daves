package br.edu.uniacademia.projeto01.resource;

import br.edu.uniacademia.projeto01.models.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/alunos")
public class AlunoResource {
    static ArrayList<Aluno> alunos = new ArrayList<>();

    static {
        alunos.add(new Aluno(1, "Natan", "1234", "natan@natan.com", 20, 5, 10));
        alunos.add(new Aluno(2, "José", "4321", "jose@jose.com", 25, 6, 8));
        alunos.add(new Aluno(3, "Ronaldo", "9999", "ronaldo@ronaldo.com", 40, 10, 9));
    }

    @GetMapping()
    public ArrayList<Aluno> getAllAlunos() {
        return alunos;
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Optional<Aluno>> getAluno(@PathVariable String matricula) {
        Optional<Aluno> aluno = alunos.stream().filter(x -> x.getMatricula().equals(matricula)).findFirst();
        HttpStatus httpStatus = aluno.isPresent()
                ? HttpStatus.FOUND
                : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(aluno);
    }

    @PostMapping()
    public ResponseEntity<Optional<Aluno>> addAluno(@RequestBody Optional<Aluno> aluno) {
        boolean adicionou = aluno.isPresent() && alunos.add(aluno.get());
        HttpStatus httpStatus = adicionou
                ? HttpStatus.CREATED
                : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(aluno);
    }

    @PutMapping("/editar")
    public ResponseEntity<Aluno> alterarAluno(@RequestBody Aluno aluno) {
        Optional<Aluno> alunoSelecionado = alunos.stream()
                .filter(x -> x.getMatricula().equals(aluno.getMatricula())).findFirst();

        if (alunoSelecionado.isEmpty())
            return ResponseEntity.notFound().build();

        alunos.set(alunos.indexOf(alunoSelecionado.get()), aluno);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/remover/{matricula}")
    public ResponseEntity<Boolean> removerAluno(@PathVariable String matricula) {
        Optional<Aluno> aluno = alunos.stream().filter(x -> x.getMatricula().equals(matricula)).findFirst();
        HttpStatus httpStatus = aluno.isPresent()
                ? HttpStatus.FOUND
                : HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus).body(aluno.isPresent() && alunos.remove(aluno.get()));
    }
}
