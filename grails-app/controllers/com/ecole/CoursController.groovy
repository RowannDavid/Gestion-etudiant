package com.ecole

import com.ecole.dto.CoursRequestDTO
import grails.rest.*
import grails.converters.*

class CoursController {
    static responseFormats = ['json', 'xml']
    CoursService coursService

    def index() {
        respond coursService.listeCours()
    }

    def show(Long id) {(
        // Evite les try catch dans tes controllers cherche un moyen de faire(Indice faire des exceptions custom qui renverrons la bonne réponse d'erreur)
        try {
            respond coursService.detailCours(id)
        } catch (RuntimeException e) {
            respond (
                    erreur: [e.message],
                    status: 404,
            )
        }
    }

    def save () {
        // Evite les try catch dans tes controllers cherche un moyen de faire(Indice faire des exceptions custom qui renverrons la bonne réponse d'erreur)
        try {
            // Je te conseille une methode qui converti directement un map en dto ça évitera du dupliquer du code
            def dto = new CoursRequestDTO(
                    titre: request.JSON.titre,
                    description: request.JSON.description,
                    dureeMinutes: request.JSON.dureeMinutes,
                    place: request.JSON.place
            )

            respond coursService.creerCours(dto)
        } catch (RuntimeException e) {
            respond (
                    erreur: [e.message],
                    status: 400,
            )
        }
    }

    def update (Long id) {
        // Evite les try catch dans tes controllers cherche un moyen de faire(Indice faire des exceptions custom qui renverrons la bonne réponse d'erreur)
        try {
            // Je te conseille une methode qui converti directement un map en dto ça évitera du dupliquer du code
            def dto = new CoursRequestDTO(
                    titre: request.JSON.titre,
                    description: request.JSON.description,
                    dureeMinutes: request.JSON.dureeMinutes,
                    place: request.JSON.place
            )

            respond coursService.modifierCours(dto, id)
        } catch (RuntimeException e) {
            respond (
                    erreur: [e.message],
                    status: 400,
            )
        }
    }

    def delete (Long id) {
        coursService.supprimerCours(id)
        render status: 204
    }
}
