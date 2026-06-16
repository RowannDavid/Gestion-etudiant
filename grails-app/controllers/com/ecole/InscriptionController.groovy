package com.ecole

import com.ecole.dto.InscriptionRequestDTO
import grails.rest.*
import grails.converters.*

class InscriptionController {

	static responseFormats = ['json', 'xml']
    InscriptionService inscriptionService

    def index() {
        respond inscriptionService.listeInscriptions(),
        status: 200
    }

    def show(Long id) {
        respond inscriptionService.detailInscription(id)
    }

    def save () {
        def dto = InscriptionRequestDTO.fromMpap(request.JSON)
        respond inscriptionService.inscrireEtudiant(dto)
    }

    def annuler (Long id) {
        inscriptionService.annulerInscription(id)
        render status: 204
    }

    def confirmer (Long id) {
        inscriptionService.confirmerInscription(id)
        render status: 204
    }
}
