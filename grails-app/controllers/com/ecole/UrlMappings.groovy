package com.ecole

class UrlMappings {

    static mappings = {

        // api ETUDIANTS
        "/api/etudiants"(controller: "etudiant") {
             action = [
                     GET: "index",
                     POST: "save"
             ]
        }
        "/api/etudiants/$id"(controller: "etudiant") {
            action =  [ GET: "show", POST: "update", DELETE: "delete"]
        }

        // api Cours
        "/api/cours"(controller: "cours") {
            action = [GET: "index", POST: "save"]
        }
        "/api/cours/$id"(controller: "cours") {
            action = [ GET: "show", POST: "update", DELETE: "delete"]
        }

        // api inscription
        "/api/inscriptions"(controller: "inscription") {
            action = [
                    GET : "index",
                    POST: "save"
            ]
        }

        "/api/inscriptions/$id"(controller: "inscription") {
            action = [
                    GET : "show"
            ]
        }
        "/api/inscriptions/$id/confirmer"(controller: "inscription", action: "confirmer", method: "POST")
        "/api/inscriptions/$id/annuler"(controller: "inscription", action: "annuler", method: "POST")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}

/*
 autre methode

    "/api/etudiants"(controller: "etudiant", action: [ GET: "index", POST: "save"])
 */

/*
── ETUDIANTS ──
GET    /api/etudiants          → liste
GET    /api/etudiants/1        → détail
POST   /api/etudiants          → créer
PUT    /api/etudiants/1        → modifier
DELETE /api/etudiants/1        → supprimer

── COURS ──
GET    /api/cours              → liste
GET    /api/cours/1            → détail
POST   /api/cours              → créer
PUT    /api/cours/1            → modifier
DELETE /api/cours/1            → supprimer

── INSCRIPTIONS ──
GET    /api/inscriptions       → liste
GET    /api/inscriptions/1     → détail
POST   /api/inscriptions       → inscrire un étudiant
PUT    /api/inscriptions/1/confirmer  → confirmer
PUT    /api/inscriptions/1/annuler    → annuler
 */