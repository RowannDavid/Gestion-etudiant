package com.ecole.dto

class CoursRequestDTO {

    String titre
    String description
    Integer dureeMinutes
    Integer place

    static CoursRequestDTO fromMap(Map data) {
        new CoursRequestDTO(
                titre: data.titre,
                description: data.description,
                dureeMinutes: data.dureeMinutes,
                place: data.place
        )
    }
}
