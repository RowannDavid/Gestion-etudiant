package com.ecole.exception

// Elle sert à représenter une erreur métier précise.
class CourseNotFoundException extends RuntimeException{

    CourseNotFoundException(String message) {
        super(message)
    }
}
