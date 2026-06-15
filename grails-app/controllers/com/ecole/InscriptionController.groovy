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
		// Voir Cours controller
        try {
			// Voir Cours controller
            respond inscriptionService.detailInscription(id)
        } catch (RuntimeException e) {
            respond (
                    erreur: [e.message],
                    status: 404,
            )
        }
    }

    def save () {
		// Voir Cours controller
        try {
			// Voir Cours controller
            def dto = new InscriptionRequestDTO(
                    etudiantId: request.JSON.etudiantId,
                    coursId: request.JSON.coursId
            )

            respond inscriptionService.inscrireEtudiant(dto)
        } catch (RuntimeException e) {
            respond (
                    erreur: [e.message],
                    status: 400,
            )
        }
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
