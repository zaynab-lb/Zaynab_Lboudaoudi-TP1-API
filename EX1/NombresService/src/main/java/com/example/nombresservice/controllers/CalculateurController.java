package com.example.nombresservice.controllers;

import com.example.nombresservice.entities.Nombres;
import com.example.nombresservice.services.CalculService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calculs")
public class CalculateurController
{
    private CalculService calculService;
    public CalculateurController(CalculService calculService)
    {
        this.calculService = calculService;
    }

    @PostMapping
    public ResponseEntity<Double> Calculer(@RequestBody Nombres n)
    {
        Double somme = calculService.Somme(n);
        if(n != null)
            return ResponseEntity.ok(somme);
        return ResponseEntity.internalServerError().build();
    }

}
