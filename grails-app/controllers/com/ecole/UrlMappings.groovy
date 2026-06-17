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

        // Routes d'authentification
        "/auth/login"(controller: 'login', action: 'authenticate', method: 'POST')
        "/auth/logout"(controller: 'logout', action: 'index', method: 'POST')
        "/auth/validate"(controller: 'validate', action: 'token', method: 'GET')

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
