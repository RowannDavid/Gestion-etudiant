package com.ecole

import com.ecole.dto.CoursRequestDTO
import com.ecole.dto.CoursResponseDTO
import grails.gorm.transactions.Transactional

@Transactional
class CoursService {

    CoursResponseDTO creerCours (CoursRequestDTO dto) {
        def cours = new Cours(
                titre: dto.titre,
                description: dto.description,
                dureeMinutes: dto.dureeMinutes,
                place: dto.place
        )

        if (!cours.save(flush: true)) {
            throw new RuntimeException(
                    "impossible de creer un cours"
            )
        }

        return toResponseDTO(cours)
    }

    @Transactional(readOnly = true)
    List<CoursResponseDTO> listeCours() {
        def listecours = Cours.findAll()
        return listecours.collect { cours ->
            toResponseDTO(cours)
        }
    }

    @Transactional(readOnly = true)
    CoursResponseDTO detailCours(Long id) {
        def cours = Cours.get(id)
        if (!cours) {
            throw new RuntimeException(
                    "impossible de trouver le cours"
            )
        }
        return toResponseDTO(cours)
    }

    CoursResponseDTO modifierCours (CoursRequestDTO dto, Long id) {
        def cours = Cours.get(id)
        if (!cours) {
            throw new RuntimeException(
                    "impossible de trouver le cours"
            )
        }
        cours.titre = dto.titre
        cours.description = dto.description
        cours.dureeMinutes = dto.dureeMinutes
        cours.place = dto.place


        if (!cours.save(flush: true)) {
            throw new RuntimeException(
                    "impossible de modifier le cours"
            )
        }

        return toResponseDTO(cours)
    }


    void supprimerCours(Long id) {
        def cours = Cours.get(id)
        if (!cours) {
            throw new RuntimeException(
                    "impossible de trouver le cours"
            )
        }
        cours.delete(flush: true)
    }

    private CoursResponseDTO toResponseDTO(Cours cours) {
        new CoursResponseDTO(
                id: cours.id,
                titre: cours.titre,
                description: cours.description,
                dureeMinutes: cours.dureeMinutes,
                place: cours.place

        )
    }
}

/*
creerCours()
listeCours()
detailCours()
modifierCours()
supprimerCours()
 */