package com.ecole

import com.ecole.dto.EtudiantRequestDTO
import grails.rest.*
import grails.converters.*

class EtudiantController {

	static responseFormats = ['json', 'xml']
	EtudiantService etudiantService

    def index() {
        respond etudiantService.listeEtudiants()
    }

    def show(Long id) {
		// Voir Cours controller
        try {
            respond etudiantService.detailEtudiant(id)
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
            def dto = new EtudiantRequestDTO(
                    nom: request.JSON.nom,
                    prenom: request.JSON.prenom,
                    email: request.JSON.email,
                    telephone: request.JSON.telephone
            )

            respond etudiantService.creerEtudiant(dto)
        } catch (RuntimeException e) {
            respond (
                    erreur: [e.message],
                    status: 400,
            )
        }
    }

    def update (Long id) {
		// Voir Cours controller
        try {
			// Voir Cours controller
            def dto = new EtudiantRequestDTO(
                    nom: request.JSON.nom,
                    prenom: request.JSON.prenom,
                    email: request.JSON.email,
                    telephone: request.JSON.telephone
            )

            respond etudiantService.modifierEtudiant(dto, id)
        } catch (RuntimeException e) {
            respond (
                    erreur: [e.message],
                    status: 400,
            )
        }
    }

    def delete (Long id) {
        etudiantService.supprimerEtudiant(id)
        render status: 204
    }
}
