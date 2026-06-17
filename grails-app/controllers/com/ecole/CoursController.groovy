package com.ecole

import com.ecole.dto.CoursRequestDTO
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*
import grails.converters.*

class CoursController {
    static responseFormats = ['json', 'xml']
    CoursService coursService

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def index() {
        respond coursService.listeCours()
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def show(Long id) {
        respond coursService.detailCours(id)
    }

    @Secured(['ROLE_ADMIN'])
    def save () {
        def dto = CoursRequestDTO.fromMap(request.JSON)
        respond coursService.creerCours(dto)

    }

    @Secured(['ROLE_ADMIN'])
    def update (Long id) {
        def dto = CoursRequestDTO.fromMap(request.JSON, id)
        respond coursService.modifierCours(dto, id)
    }

    @Secured(['ROLE_ADMIN'])
    def delete (Long id) {
        coursService.supprimerCours(id)
        render status: 204
    }
}
