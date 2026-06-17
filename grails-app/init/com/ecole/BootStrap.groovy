package com.ecole

import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

class BootStrap {

    @Autowired
    PasswordEncoder passwordEncoder

    def init = {
        log.info("Initialisation de la Gestion des Étudiants...")
        createAdminOnly()
        log.info("Initialisation terminée !")
    }

    @Transactional
    void createAdminOnly() {
        // Vérifier si l'admin existe déjà
        def adminUser = User.findByUsername('david')
        if (adminUser) {
            log.info("L'administrateur existe déjà")
            log.info("Username: david")
            return
        }

        log.info("Création de l'administrateur...")

        // Créer les rôles nécessaires
        def adminRole = Role.findByAuthority('ROLE_ADMIN')
                ?: new Role(authority: 'ROLE_ADMIN').save()

        def userRole = Role.findByAuthority('ROLE_USER')
                ?: new Role(authority: 'ROLE_USER').save()

        // Créer l'utilisateur admin
        String encodedPassword = passwordEncoder.encode('david')

        def admin = new User(
                username: 'david',
                password: encodedPassword,
                email: 'admin@ecole.com',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false
        )

        if (!admin.save()) {
            log.error("Erreur lors de la création de l'admin : ${admin.errors}")
            return
        }

        //  Assigner les rôles à l'admin
        UserRole.create(admin, adminRole)
        UserRole.create(admin, userRole)

    }

}