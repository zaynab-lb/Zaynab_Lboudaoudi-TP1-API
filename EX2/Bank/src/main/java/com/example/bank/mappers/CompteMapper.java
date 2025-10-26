package com.example.bank.mappers;

import com.example.bank.dto.RequestCompteDto;
import com.example.bank.dto.ResponseCompteDto;
import com.example.bank.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper
{
    public Compte DTO_to_Entity(RequestCompteDto requestCompteDto)
    {
        Compte compte = new Compte();
        // Au lieu de getters et setter
        BeanUtils.copyProperties(requestCompteDto, compte);
        return compte;
    }

    //transfert l'entité de la couche service vers la couche web
    public ResponseCompteDto Entity_to_DTO(Compte compte)
    {
        ResponseCompteDto responseCompteDto = new ResponseCompteDto();
        BeanUtils.copyProperties(compte, responseCompteDto);
        return responseCompteDto;
    }
}
