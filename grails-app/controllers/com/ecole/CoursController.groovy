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
        respond coursService.detailCours(id)
    }

    def save () {
        def dto = CoursRequestDTO.fromMap(request.JSON)
        respond coursService.creerCours(dto)

    }

    def update (Long id) {
        def dto = CoursRequestDTO.fromMap(request.JSON, id)
        respond coursService.modifierCours(dto, id)
    }

    def delete (Long id) {
        coursService.supprimerCours(id)
        render status: 204
    }
}
