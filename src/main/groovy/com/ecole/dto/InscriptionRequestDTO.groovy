package com.ecole.dto

class InscriptionRequestDTO {

    Long etudiantId
    Long coursId

    static InscriptionRequestDTO fromMpap(Map data){
        new InscriptionRequestDTO(
                etudiantId: data.etudiantId,
                coursId: data.coursId
        )
    }
}
