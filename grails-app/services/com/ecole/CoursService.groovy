package com.ecole

import com.ecole.dto.CoursRequestDTO
import com.ecole.dto.CoursResponseDTO
import grails.gorm.transactions.Transactional

@Transactional
class CoursService {
    // Evite le nommage en français
    CoursResponseDTO creerCours (CoursRequestDTO dto) {
        // Ajoute du logging check doc(Slf4j)
        // Evite le typage dynamique
        def cours = new Cours(
                titre: dto.titre,
                description: dto.description,
                dureeMinutes: dto.dureeMinutes,
                place: dto.place
        )
        
        // Utilise failOnError tu auras plus de detail sur ce qui à déclanché l'erreur de la sauvegarde car ton message ne dit pas ce qui a foiré la sauvegarde
        if (!cours.save(flush: true)) {
            throw new RuntimeException(
                    "impossible de creer un cours"
            )
        }

        
        return toResponseDTO(cours)
    }
    
    // Evite le nommage en français
    @Transactional(readOnly = true)
    List<CoursResponseDTO> listeCours() {
        // Ajoute du logging check doc(Slf4j)
        def listecours = Cours.findAll()
        return listecours.collect { cours ->
            toResponseDTO(cours)
        }
    }

    @Transactional(readOnly = true)
    CoursResponseDTO detailCours(Long id) {
        // Ajoute du logging check doc(Slf4j)
        // Crée une methode getById pour eviter la répétition de code
        def cours = Cours.get(id)
        if (!cours) {
            throw new RuntimeException(
                    "impossible de trouver le cours"
            )
        }
        return toResponseDTO(cours)
    }

    // Evite le nommage en français
    CoursResponseDTO modifierCours (CoursRequestDTO dto, Long id) {
        // Ajoute du logging check doc(Slf4j)
        // Crée une methode getById pour eviter la répétition de code
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

    // Evite le nommage en français
    void supprimerCours(Long id) {
        // Ajoute du logging check doc(Slf4j)
        // Crée une methode getById pour eviter la répétition de code
        def cours = Cours.get(id)
        if (!cours) {
            throw new RuntimeException(
                    "impossible de trouver le cours"
            )
        }
        cours.delete(flush: true)
    }

    // Evite le nommage en français
    // Crée un constrcuteur dans ton dto qui prends le domain cours en paramètre et retourne directement ton dto(Dit moi si tu ne comprends pas)
    private CoursResponseDTO toResponseDTO(Cours cours) {
        // Ajoute du logging check doc(Slf4j)
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
