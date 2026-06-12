package com.ecole.dto

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
}