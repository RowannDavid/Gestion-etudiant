package com.ecole

import com.ecole.dto.EtudiantRequestDTO
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*
import grails.converters.*

class EtudiantController {

	static responseFormats = ['json', 'xml']
	EtudiantService etudiantService

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def index() {
        respond etudiantService.listeEtudiants()
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def show(Long id) {
        respond etudiantService.detailEtudiant(id)
    }

    @Secured(['ROLE_ADMIN'])
    def save () {
        def dto = EtudiantRequestDTO.fromMap(request.JSON)
        respond etudiantService.creerEtudiant(dto)
    }

    @Secured(['ROLE_ADMIN'])
    def update (Long id) {
        def dto = EtudiantRequestDTO.fromMap(request.JSON, id)
        respond etudiantService.modifierEtudiant(dto, id)
    }

    @Secured(['ROLE_ADMIN'])
    def delete (Long id) {
        etudiantService.supprimerEtudiant(id)
        render status: 204
    }
}
