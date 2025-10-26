package com.example.nombresservice.services;

import com.example.nombresservice.entities.Nombres;
import org.springframework.stereotype.Service;

@Service
public class CalculService
{
    public Double Somme(Nombres n)
    {
        return n.getA() +  n.getB();
    }
}
