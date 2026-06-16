package com.ecole.dto

class EtudiantRequestDTO {

    String nom
    String prenom
    String email
    String telephone

    static EtudiantRequestDTO fromMap(Map data) {
        new EtudiantRequestDTO(
                nom: data.nom,
                prenom: data.prenom,
                email: data.email,
                telephone: data.telephone
        )
    }
}
