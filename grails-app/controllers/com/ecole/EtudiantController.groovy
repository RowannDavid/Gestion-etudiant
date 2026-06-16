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
        respond etudiantService.detailEtudiant(id)
    }

    def save () {
        def dto = EtudiantRequestDTO.fromMap(request.JSON)
        respond etudiantService.creerEtudiant(dto)
    }

    def update (Long id) {
        def dto = EtudiantRequestDTO.fromMap(request.JSON, id)
        respond etudiantService.modifierEtudiant(dto, id)
    }

    def delete (Long id) {
        etudiantService.supprimerEtudiant(id)
        render status: 204
    }
}
