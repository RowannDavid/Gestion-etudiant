package com.ecole

import com.ecole.dto.EtudiantRequestDTO
import com.ecole.dto.EtudiantResponseDTO
import com.ecole.exception.CourseNotFoundException
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class EtudiantService {

    EtudiantResponseDTO creerEtudiant (EtudiantRequestDTO dto) {

        // Début de création d'un etudiant
        log.info("Création d'un etudiant, titre={}", dto.nom)

        Etudiant etudiant = new Etudiant(
                nom: dto.nom,
                prenom: dto.prenom,
                email: dto.email,
                telephone: dto.telephone
        )

        if (!etudiant.save(flush: true, failOnError: true)) {
            throw new RuntimeException(
                    "impossible de creer un etudiant"
            )
        }

        // Création réussie
        log.info("Etudiant créé avec succès, id={}", etudiant.id)

        return new EtudiantResponseDTO(etudiant)
    }

    @Transactional(readOnly = true)
    List<EtudiantResponseDTO> listeEtudiants() {

        // Récupération de tous les etudiant
        log.info("Récupération de la liste des etudiant")

        List<Etudiant> etudiants = Etudiant.findAll()

        log.debug("Nombre de etudiant trouvés={}", etudiants.size())

        return etudiants.collect { new EtudiantResponseDTO(it) }
    }

    @Transactional(readOnly = true)
    EtudiantResponseDTO detailEtudiant(Long id) {

        // Recherche d'un cours par son identifiant
        log.info("Recherche du cours id={}", id)

        Etudiant etudiant = EtudiantId(id)

        return new EtudiantResponseDTO(etudiant)
    }

    EtudiantResponseDTO modifierEtudiant (EtudiantRequestDTO dto, Long id) {

        // Modification d'un cours existant
        log.info("Modification du cours id={}", id)

        Etudiant etudiant = EtudiantId(id)

        etudiant.nom       = dto.nom
        etudiant.prenom    = dto.prenom
        etudiant.email     = dto.email
        etudiant.telephone = dto.telephone

        if (!etudiant.save(flush: true)) {
            throw new RuntimeException(
                    "impossible de modifier l'etudiant"
            )
        }

        log.info("Cours modifié avec succès, id={}", id)

        return new EtudiantResponseDTO(etudiant)
    }

    void supprimerEtudiant(Long id) {

        // Suppression d'un etudiant
        log.info("Suppression du etudiant id={}", id)

        Etudiant etudiant = EtudiantId(id)
        etudiant.delete(flush: true, failOnError:true)

        log.info("Etudiant supprimé avec succès, id={}", id)
    }

    private Etudiant EtudiantId (Long id) {

        log.debug("Vérification de l'existence du etudiant id={}", id)

        Etudiant etudiant = Etudiant.get(id)
        if (!etudiant) {

            log.warn("Etudiant introuvable, id={}", id)

            throw new CourseNotFoundException(
                    "Etudiant ${id} introuvable"
            )
        }
    }

}
