package com.ecole

import com.ecole.dto.InscriptionRequestDTO
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*
import grails.converters.*

class InscriptionController {

	static responseFormats = ['json', 'xml']
    InscriptionService inscriptionService

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def index() {
        respond inscriptionService.listeInscriptions(),
        status: 200
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def show(Long id) {
        respond inscriptionService.detailInscription(id)
    }
    @Secured(['ROLE_USER'])
    def save () {
        def dto = InscriptionRequestDTO.fromMpap(request.JSON)
        respond inscriptionService.inscrireEtudiant(dto)
    }
    @Secured(['ROLE_ADMIN'])
    def annuler (Long id) {
        inscriptionService.annulerInscription(id)
        render status: 204
    }
    @Secured(['ROLE_ADMIN'])
    def confirmer (Long id) {
        inscriptionService.confirmerInscription(id)
        render status: 204
    }
}
