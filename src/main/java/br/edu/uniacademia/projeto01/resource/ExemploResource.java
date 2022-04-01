package br.edu.uniacademia.projeto01.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/primeiro")
public class ExemploResource {

    @GetMapping
    public String testando() {
        return "teste";
    }

    @GetMapping("/ok")
    public String testando1() {

        return "diajdaidjaioda";
    }
}
