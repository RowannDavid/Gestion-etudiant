package com.ecole

class Etudiant {

    String nom
    String prenom
    String email
    String telephone

    Date dateCreated
    Date lastUpdated

    static hasmany = {inscription:Inscription}

    static constraints = {
        nom nullable: false, blank: false
        prenom nullable: false, blank: false
        email nullable: false, blank: false, unique: true, email: true
        telephone nullable: false, blank: false
    }
}
