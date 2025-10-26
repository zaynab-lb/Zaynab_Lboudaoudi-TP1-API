package com.example.bank.service;

import com.example.bank.dto.RequestCompteDto;
import com.example.bank.dto.ResponseCompteDto;

import java.util.List;

public interface CompteService
{
    public ResponseCompteDto Add_Compte(RequestCompteDto requestCompteDto);
    public List<ResponseCompteDto> GetAllCompte();
    public ResponseCompteDto GetCompteById(Integer id);
    public ResponseCompteDto UpdateCompte(Integer id , RequestCompteDto requestCompteDto);
    public void DeleteCompte(Integer id);
    public ResponseCompteDto Crediter(Integer id, Double m);
    public ResponseCompteDto Debiter(Integer id, Double m);
}
