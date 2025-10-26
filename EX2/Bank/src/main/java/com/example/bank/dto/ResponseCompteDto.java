package com.example.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCompteDto
{
    private Integer id;
    private String nom;
    private String tel;
    private Double solde;
}
