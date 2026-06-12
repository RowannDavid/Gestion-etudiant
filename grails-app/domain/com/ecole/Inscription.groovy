package com.ecole

class Inscription {

    String statut
    Date dateInscription

    Date dateCreated
    Date lastUpdated

    static belongsTo = [etudiant:Etudiant, cours:Cours]


    static constraints = {
        statut: inList: ["EN_ATTENTE", "CONFIRMEE", "ANNULEE"]
    }
}
