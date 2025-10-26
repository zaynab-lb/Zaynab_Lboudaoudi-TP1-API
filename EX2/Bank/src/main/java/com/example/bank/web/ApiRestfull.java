package com.example.bank.web;

import com.example.bank.dto.RequestCompteDto;
import com.example.bank.dto.ResponseCompteDto;
import com.example.bank.service.CompteServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des comptes bancaires",
                description = "Cette API offre toutes les méthodes pour gérer les comptes bancaires",
                version = "1.0"
        ),
        servers = @Server(
                url = "http://localhost:8081"
        )
)

@RestController
@RequestMapping("/v1/comptes")
public class ApiRestfull
{
    private CompteServiceImpl compteService;

    public ApiRestfull(CompteServiceImpl compteService)
    {
        this.compteService = compteService;
    }

    @Operation(
            summary = "Ajouter nouveau compte",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Compte bien ajouter",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "Erreur client"
                    ),
                    @ApiResponse(
                            responseCode = "5xx",
                            description = "Erreur serveur"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ResponseCompteDto> add(@RequestBody RequestCompteDto requestCompteDto)
    {
        ResponseCompteDto responseCompteDto = compteService.Add_Compte(requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = "Afficher la liste des comptes",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "La liste est bien afficher",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseCompteDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "Erreur client"
                    ),
                    @ApiResponse(
                            responseCode = "5xx",
                            description = "Erreur serveur"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponseCompteDto>> getAll()
    {
        List<ResponseCompteDto> compteDtos = compteService.GetAllCompte();
        return ResponseEntity.ok(compteDtos);
    }

    @Operation(
            summary = "Afficher les informations d'un client par son id",
            parameters = @Parameter(
                    name = "id",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Client bien affiché",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "Erreur Client"
                    ),
                    @ApiResponse(
                            responseCode = "5xx",
                            description = "Erreur Serveur"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> get(@PathVariable Integer id)
    {
        ResponseCompteDto compteDtos = compteService.GetCompteById(id);
        return ResponseEntity.ok(compteDtos);

    }

    @Operation(
            summary = "Modofier les informations d'un compte",
            parameters = @Parameter(
                    name = "id",
                    required = true
            ),
            requestBody =  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Le compte est bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "Erreur client"
                    ),
                    @ApiResponse(
                            responseCode = "5xx",
                            description = "Erreur serveur"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCompteDto> update(@PathVariable Integer id, @RequestBody RequestCompteDto requestCompteDto)
    {
        ResponseCompteDto responseCompteDto = compteService.UpdateCompte(id, requestCompteDto);
        return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = "La suppression d'un compte par son id",
            parameters = @Parameter(
                    name = "id",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Compte bien supprimer"
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "Erreur client"
                    ),
                    @ApiResponse(
                            responseCode = "5xx",
                            description = "Erreur serveur"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id)
    {
        compteService.DeleteCompte(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Crediter un compte",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true
                    ),
                    @Parameter(
                            name = "m",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Le solde est bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "Erreur client"
                    ),
                    @ApiResponse(
                            responseCode = "5xx",
                            description = "Erreur serveur"
                    )
            }
    )
    @PatchMapping("/crediter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> crediter(@PathVariable Integer id, @PathVariable Double m)
    {
        ResponseCompteDto responseCompteDto = compteService.Crediter(id, m);
        return ResponseEntity.ok(responseCompteDto);
    }

    @Operation(
            summary = "Debiter un compte",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true
                    ),
                    @Parameter(
                            name = "m",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Le solde est bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCompteDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "Erreur client"
                    ),
                    @ApiResponse(
                            responseCode = "5xx",
                            description = "Erreur serveur"
                    )
            }
    )
    @PatchMapping("/debiter/{id}/{m}")
    ResponseEntity<ResponseCompteDto> debiter(@PathVariable Integer id, @PathVariable Double m)
    {
        ResponseCompteDto responseCompteDto = compteService.Debiter(id, m);
        return ResponseEntity.ok(responseCompteDto);
    }

}
