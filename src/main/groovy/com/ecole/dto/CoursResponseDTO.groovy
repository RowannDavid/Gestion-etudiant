package com.ecole.dto

import com.ecole.Cours

class CoursResponseDTO {

    Long id
    String titre
    String description
    Integer dureeMinutes
    Integer place

    CoursResponseDTO (Cours cours) {
        this.id = cours.id
        this.titre = cours.titre
        this.description = cours.description
        this.dureeMinutes = cours.dureeMinutes
        this.place = cours.place
    }
}
