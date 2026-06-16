package com.ecole.exception

import org.springframework.web.bind.annotation.ControllerAdvice

// Elle sert à attraper automatiquement les exceptions dans toute l'application.
@ControllerAdvice
class GlobalException {

    Map CoursNotFound(CourseNotFoundException i) {
        [
                error: i.message,
                status: 404
        ]
    }
}
