package br.edu.uniacademia.projeto01.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/calculadora")
@CrossOrigin("http://localhost:3000/")
public class CalculadoraResource {

    @GetMapping
    public String instrucoes() {
        return "ok";
    }

    @GetMapping("/soma/{v1}/{v2}")
    public double soma(@PathVariable double v1,
                       @PathVariable double v2) {
        return v1 + v2;
    }

    @GetMapping("/subtracao")
    public double subtracao(@RequestParam double v1,
                            @RequestParam double v2) {
        return v1 - v2;
    }
/*
    @PostMapping("/somatorio")
    public double somatorio(@RequestBody Map<String, Double> valores) {
        double total = 0;
        for (Double v: valores.values()) {
            total += v;
        }
        return total;
    }
  */
    @PostMapping("/somatorio")
    public ResponseEntity<Double> somatorio(@RequestBody Map<String, Double> valores) {
        double total = 0;
        for (Double v: valores.values()) {
            total += v;
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(total);
    }

    @GetMapping("/potencia")
    public ResponseEntity<Double> somatorio(@RequestParam double base,
                                            @RequestParam double expoente) {
        if (base == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            double resultado = Math.pow(base, expoente);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultado);
        }
    }
}
