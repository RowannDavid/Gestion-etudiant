package com.ecole

import com.ecole.dto.CoursRequestDTO
import com.ecole.dto.CoursResponseDTO
import com.ecole.exception.CourseNotFoundException
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class CoursService {

    CoursResponseDTO creerCours(CoursRequestDTO dto) {

        // Début de création d'un cours
        log.info("Création d'un cours, titre={}", dto.titre)

        Cours cours = new Cours(
                titre: dto.titre,
                description: dto.description,
                dureeMinutes: dto.dureeMinutes,
                place: dto.place
        )

        cours.save(flush: true, failOnError: true)

        // Création réussie
        log.info("Cours créé avec succès, id={}", cours.id)

        return new CoursResponseDTO(cours)
    }

    @Transactional(readOnly = true)
    List<CoursResponseDTO> listeCours() {

        // Récupération de tous les cours
        log.info("Récupération de la liste des cours")

        List<Cours> listecours = Cours.findAll()

        log.debug("Nombre de cours trouvés={}", listecours.size())

        return listecours.collect {
            new CoursResponseDTO(it)
        }
    }

    @Transactional(readOnly = true)
    CoursResponseDTO detailCours(Long id) {

        // Recherche d'un cours par son identifiant
        log.info("Recherche du cours id={}", id)

        Cours cours = coursId(id)

        return new CoursResponseDTO(cours)
    }

    CoursResponseDTO modifierCours(CoursRequestDTO dto, Long id) {

        // Modification d'un cours existant
        log.info("Modification du cours id={}", id)

        Cours cours = coursId(id)

        cours.titre = dto.titre
        cours.description = dto.description
        cours.dureeMinutes = dto.dureeMinutes
        cours.place = dto.place

        cours.save(flush: true, failOnError: true)

        log.info("Cours modifié avec succès, id={}", id)

        return new CoursResponseDTO(cours)
    }

    void supprimerCours(Long id) {

        // Suppression d'un cours
        log.info("Suppression du cours id={}", id)

        Cours cours = coursId(id)

        cours.delete(flush: true, failOnError: true)

        log.info("Cours supprimé avec succès, id={}", id)
    }

    private Cours coursId(Long id) {

        log.debug("Vérification de l'existence du cours id={}", id)

        Cours cours = Cours.get(id)

        if (!cours) {

            log.warn("Cours introuvable, id={}", id)

            throw new CourseNotFoundException(
                    "Cours ${id} introuvable"
            )
        }

        return cours
    }
}
