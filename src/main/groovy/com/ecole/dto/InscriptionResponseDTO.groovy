package com.ecole.dto

import com.ecole.Inscription

class InscriptionResponseDTO {

    // ── infos de l'inscription ──
    Long   id
    Date   dateInscription
    String statut

    // ── infos de l'étudiant ──
    Long   etudiantId
    String etudiantNom
    String etudiantPrenom
    String etudiantEmail

    // ── infos du cours ──
    Long   coursId
    String coursTitre
    Integer coursPlaces

    InscriptionResponseDTO (Inscription inscription) {
        this.id =  inscription.id
        this.dateInscription = inscription.dateInscription
        this.statut = inscription.statut

        this.etudiantId = inscription.etudiant.id
        this.etudiantNom = inscription.etudiant.nom
        this.etudiantPrenom = inscription.etudiant.prenom
        this.etudiantEmail =  inscription.etudiant.email

        this.coursId =  inscription.cours.id
        this.coursTitre =  inscription.cours.titre
        this.coursPlaces =  inscription.cours.place

    }
}