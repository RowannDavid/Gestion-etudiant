package com.ecole.dto

import com.ecole.Etudiant

class EtudiantResponseDTO {

    Long id
    String nom
    String prenom
    String email
    String telephone

    EtudiantResponseDTO (Etudiant etudiant) {
        this.id = etudiant.id
        this.nom = etudiant.nom
        this.prenom = etudiant.prenom
        this.email = etudiant.email
        this.telephone = etudiant.telephone
    }
}
