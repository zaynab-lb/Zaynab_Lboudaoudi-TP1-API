package com.example.bank.service;

import com.example.bank.dto.RequestCompteDto;
import com.example.bank.dto.ResponseCompteDto;
import com.example.bank.entities.Compte;
import com.example.bank.mappers.CompteMapper;
import com.example.bank.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {
    private CompteRepository  compteRepository;
    private CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository,  CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public ResponseCompteDto Add_Compte(RequestCompteDto requestCompteDto) {
       Compte compte = compteMapper.DTO_to_Entity(requestCompteDto);
       Compte saved_compte = compteRepository.save(compte);

        return compteMapper.Entity_to_DTO(saved_compte);

    }

    @Override
    public List<ResponseCompteDto> GetAllCompte()
    {
        List<Compte> comptes = compteRepository.findAll();
        List<ResponseCompteDto> compteDtos = new ArrayList<>(); //vide
        for (Compte c : comptes)
        {
            compteDtos.add(compteMapper.Entity_to_DTO(c));
        }

        return compteDtos;
    }

    @Override
    public ResponseCompteDto GetCompteById(Integer id)
    {
        Compte compte = compteRepository.findById(id).orElseThrow();
        return compteMapper.Entity_to_DTO(compte);
    }

    @Override
    public ResponseCompteDto UpdateCompte(Integer id, RequestCompteDto requestCompteDto)
    {
        Compte newCompte = compteMapper.DTO_to_Entity(requestCompteDto);

        Compte compte = compteRepository.findById(id).orElseThrow();

        if(newCompte.getNom()!=null) compte.setNom(newCompte.getNom());
        if (newCompte.getTel()!=null) compte.setTel(newCompte.getTel());
        if (newCompte.getSolde()!=null)compte.setSolde(newCompte.getSolde());

        Compte saved_compte = compteRepository.save(compte);
        return compteMapper.Entity_to_DTO(saved_compte);
    }

    @Override
    public void DeleteCompte(Integer id)
    {
        compteRepository.deleteById(id);
    }

    @Override
    public ResponseCompteDto Crediter(Integer id, Double m)
    {
        Compte compte = compteRepository.findById(id).orElseThrow();
        compte.setSolde(compte.getSolde() + m);
        Compte saved = compteRepository.save(compte);
        return compteMapper.Entity_to_DTO(saved);
    }

    @Override
    public ResponseCompteDto Debiter(Integer id, Double m)
    {
        Compte compte = compteRepository.findById(id).orElseThrow();

        if (compte.getSolde() >= m)
        {
            compte.setSolde(compte.getSolde() - m);
        }
        Compte saved = compteRepository.save(compte);
        return compteMapper.Entity_to_DTO(saved);
    }
}
