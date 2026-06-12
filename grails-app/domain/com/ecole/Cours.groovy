package com.ecole

class Cours {

    String titre
    String description
    Integer dureeMinutes
    Integer place

    Date dateCreated
    Date lastUpdated

    static hasmany = {inscription:Inscription}


    static constraints = {
        titre nullable: false, blank: false
        description nullable: false, blank: false
        dureeMinutes nullable: false, min: 1
        place nullable: false, min: 0
    }
}
