package com.ecole

import com.ecole.dto.InscriptionRequestDTO
import com.ecole.dto.InscriptionResponseDTO
import grails.gorm.transactions.Transactional

@Transactional
class InscriptionService {

    InscriptionResponseDTO inscrireEtudiant(InscriptionRequestDTO dto) {

        def etudiant = Etudiant.get(dto.etudiantId)
        if (!etudiant) {
            throw new RuntimeException(
                    "Etudiant ${dto.etudiantId} introuvable"
            )
        }

        def cours = Cours.get(dto.coursId)
        if (!cours) {
            throw new RuntimeException(
                    "Cours ${dto.coursId} introuvable"
            )
        }

        if (cours.place <= 0) {
            throw new RuntimeException(
                    "Plus de places disponibles pour ce cours"
            )
        }

        def dejaInscrit = Inscription.findByEtudiantAndCours(etudiant, cours)
        if (dejaInscrit) {
            throw new RuntimeException(
                    "Cet étudiant est déjà inscrit à ce cours"
            )
        }

        def inscription = new Inscription(
                etudiant:   etudiant,
                cours:      cours,
                statut:     "EN_ATTENTE",
                dateInscription: new Date()
        )

        if (!inscription.save(flush: true)) {
            throw new RuntimeException(
                    "Impossible de créer l'inscription : ${inscription.errors}"
            )
        }
        cours.place--
        cours.save(flush: true)

        return toResponseDTO(inscription)
    }


    @Transactional(readOnly = true)
    List<InscriptionResponseDTO> listeInscriptions() {
        def inscriptions = Inscription.findAll()
        return inscriptions.collect { inscription ->
            toResponseDTO(inscription)
        }
    }


    @Transactional(readOnly = true)
    InscriptionResponseDTO detailInscription(Long id) {
        def inscription = Inscription.get(id)
        if (!inscription) {
            throw new RuntimeException(
                    "Inscription ${id} introuvable"
            )
        }
        return toResponseDTO(inscription)
    }


    void annulerInscription(Long id) {

        def inscription = Inscription.get(id)
        if (!inscription) {
            throw new RuntimeException(
                    "Inscription ${id} introuvable"
            )
        }

        if (inscription.statut == "ANNULEE") {
            throw new RuntimeException(
                    "Cette inscription est déjà annulée"
            )
        }

        inscription.cours.place++
        inscription.cours.save(flush: true)

        inscription.statut = "ANNULEE"
        inscription.save(flush: true)
    }


    void confirmerInscription(Long id) {

        def inscription = Inscription.get(id)
        if (!inscription) {
            throw new RuntimeException(
                    "Inscription ${id} introuvable"
            )
        }

        if (inscription.statut != "EN_ATTENTE") {
            throw new RuntimeException(
                    "Impossible de confirmer une inscription ${inscription.statut}"
            )
        }

        inscription.statut = "CONFIRMEE"
        inscription.save(flush: true)
    }


    private InscriptionResponseDTO toResponseDTO(Inscription inscription) {
        return new InscriptionResponseDTO(
                id:              inscription.id,
                dateInscription: inscription.dateInscription,
                statut:          inscription.statut,

                etudiantId:      inscription.etudiant.id,
                etudiantNom:     inscription.etudiant.nom,
                etudiantPrenom:  inscription.etudiant.prenom,
                etudiantEmail:   inscription.etudiant.email,

                coursId:         inscription.cours.id,
                coursTitre:      inscription.cours.titre,
                coursPlaces:     inscription.cours.place
        )
    }
}

/*
{
    "nom": "David",
    "prenom": "rowann david",
    "email": "test@gmail.com",
    "telephone": "0173137002"
}
{
    "titre": "Spring Boot",
    "description": "Formation Spring Boot avancée",
    "dureeMinutes": 150,
    "place": 30
}
 */