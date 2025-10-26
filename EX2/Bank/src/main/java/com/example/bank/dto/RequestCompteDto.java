package com.example.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCompteDto
{
    //id pas info porte par client car il est automatiquement crée mais pour update et delete peut utilise id en uri
    private String nom;
    private String tel;
    private Double solde;
}
