package com.example.presencemanager

data class Stagiaire(
    val id: Int,
    val nom: String,
    val prenom: String,
    var estPresent: Boolean = true
)
