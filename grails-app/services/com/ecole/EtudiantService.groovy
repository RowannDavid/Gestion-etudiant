package com.ecole

import com.ecole.dto.EtudiantRequestDTO
import com.ecole.dto.EtudiantResponseDTO
import grails.gorm.transactions.Transactional

@Transactional
class EtudiantService {

    EtudiantResponseDTO creerEtudiant (EtudiantRequestDTO dto) {
        def etudiant = new Etudiant(
                nom: dto.nom,
                prenom: dto.prenom,
                email: dto.email,
                telephone: dto.telephone
        )

        if (!etudiant.save(flush: true)) {
            throw new RuntimeException(
                    "impossible de creer un etudiant"
            )
        }

        return toResponseDTO(etudiant)
    }

    @Transactional(readOnly = true)
    List<EtudiantResponseDTO> listeEtudiants() {
        def etudiants = Etudiant.findAll()
        return etudiants.collect { etudiant ->
            toResponseDTO(etudiant)
        }
    }

    @Transactional(readOnly = true)
    EtudiantResponseDTO detailEtudiant(Long id) {
        def etudiant = Etudiant.get(id)
        if (!etudiant) {
            throw new RuntimeException(
                    "impossible de trouver l'etudiant"
            )
        }
        return toResponseDTO(etudiant)
    }

    EtudiantResponseDTO modifierEtudiant (EtudiantRequestDTO dto, Long id) {
        def etudiant = Etudiant.get(id)
        if (!etudiant) {
            throw new RuntimeException(
                    "impossible de trouver l'etudiant"
            )
        }
        etudiant.nom       = dto.nom
        etudiant.prenom    = dto.prenom
        etudiant.email     = dto.email
        etudiant.telephone = dto.telephone

        if (!etudiant.save(flush: true)) {
            throw new RuntimeException(
                    "impossible de modifier l'etudiant"
            )
        }

        return toResponseDTO(etudiant)
    }

    void supprimerEtudiant(Long id) {
        def etudiant = Etudiant.get(id)
        if (!etudiant) {
            throw new RuntimeException(
                    "impossible de trouver l'etudiant"
            )
        }
        etudiant.delete(flush: true)
    }

    private EtudiantResponseDTO toResponseDTO(Etudiant etudiant) {
        new EtudiantResponseDTO(
                id: etudiant.id,
                nom: etudiant.nom,
                prenom: etudiant.prenom,
                email: etudiant.email,
                telephone: etudiant.telephone
        )
    }

}

/*
creerEtudiant()
listeEtudiants()
detailEtudiant()
modifierEtudiant()
supprimerEtudiant()
 */