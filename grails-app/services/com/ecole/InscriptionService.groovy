package com.ecole

import com.ecole.dto.InscriptionRequestDTO
import com.ecole.dto.InscriptionResponseDTO
import com.ecole.exception.CourseNotFoundException
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class InscriptionService {

    InscriptionResponseDTO inscrireEtudiant(InscriptionRequestDTO dto) {

        log.info("Inscription de l'étudiant {} au cours {}", dto.etudiantId, dto.coursId)

        Etudiant etudiant = getEtudiantById(dto.etudiantId)

        Cours cours = getCoursById(dto.coursId)

        if (cours.place <= 0) {

            log.warn("Tentative d'inscription sur un cours complet id={}", cours.id)

            throw new CourseNotFoundException(
                    "Plus de places disponibles pour ce cours"
            )
        }

        Inscription dejaInscrit = Inscription.findByEtudiantAndCours(etudiant, cours)

        if (dejaInscrit) {

            log.warn("L'étudiant {} est déjà inscrit au cours {}",etudiant.id, cours.id
            )

            throw new CourseNotFoundException(
                    "Cet étudiant est déjà inscrit à ce cours"
            )
        }

        Inscription inscription = new Inscription(
                etudiant: etudiant,
                cours: cours,
                statut: "EN_ATTENTE",
                dateInscription: new Date()
        )

        inscription.save(flush: true, failOnError: true)
        cours.place--
        cours.save(flush: true, failOnError: true)

        log.info("Inscription créée avec succès id={}", inscription.id)

        return new InscriptionResponseDTO(inscription)
    }

    @Transactional(readOnly = true)
    List<InscriptionResponseDTO> listeInscriptions() {

        log.info("Récupération de la liste des inscriptions")

        List<Inscription> inscriptions = Inscription.findAll()

        log.debug("Nombre d'inscriptions trouvées={}", inscriptions.size())

        return inscriptions.collect {
            new InscriptionResponseDTO(it)
        }
    }

    @Transactional(readOnly = true)
    InscriptionResponseDTO detailInscription(Long id) {

        log.info("Consultation de l'inscription id={}", id)

        Inscription inscription = getInscriptionById(id)

        return new InscriptionResponseDTO(inscription)
    }

    void annulerInscription(Long id) {

        log.info("Annulation de l'inscription id={}", id)

        Inscription inscription = getInscriptionById(id)

        if (inscription.statut == "ANNULEE") {

            log.warn("L'inscription {} est déjà annulée", id)

            throw new CourseNotFoundException(
                    "Cette inscription est déjà annulée"
            )
        }

        inscription.cours.place++
        inscription.cours.save(flush: true, failOnError: true)
        inscription.statut = "ANNULEE"
        inscription.save(flush: true, failOnError: true)

        log.info("Inscription annulée avec succès id={}", id)
    }

    void confirmerInscription(Long id) {

        log.info("Confirmation de l'inscription id={}", id)

        Inscription inscription = getInscriptionById(id)

        if (inscription.statut != "EN_ATTENTE") {

            log.warn("Impossible de confirmer l'inscription {} statut={}", id, inscription.statut)

            throw new CourseNotFoundException(
                    "Impossible de confirmer une inscription ${inscription.statut}"
            )
        }

        inscription.statut = "CONFIRMEE"

        inscription.save(flush: true, failOnError: true)

        log.info("Inscription confirmée avec succès id={}", id)
    }

    private Etudiant getEtudiantById(Long id) {

        Etudiant etudiant = Etudiant.get(id)

        if (!etudiant) {

            log.warn("Etudiant introuvable id={}", id)

            throw new CourseNotFoundException(
                    "Etudiant ${id} introuvable"
            )
        }

        return etudiant
    }

    private Cours getCoursById(Long id) {

        Cours cours = Cours.get(id)

        if (!cours) {

            log.warn("Cours introuvable id={}", id)

            throw new CourseNotFoundException(
                    "Cours ${id} introuvable"
            )
        }

        return cours
    }

    private Inscription getInscriptionById(Long id) {

        Inscription inscription = Inscription.get(id)

        if (!inscription) {

            log.warn("Inscription introuvable id={}", id)

            throw new CourseNotFoundException(
                    "Inscription ${id} introuvable"
            )
        }

        return inscription
    }
}