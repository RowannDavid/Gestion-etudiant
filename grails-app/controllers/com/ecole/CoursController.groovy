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

    def show(Long id) {
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
        try {
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
        try {
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
