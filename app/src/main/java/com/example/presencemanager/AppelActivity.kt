package com.example.presencemanager

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AppelActivity : AppCompatActivity() {

    private lateinit var stagiaires: MutableList<Stagiaire>
    private lateinit var adapter: StagiaireAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appel)

        val groupeNom = intent.getStringExtra("GROUPE_NOM") ?: "Tous les groupes"

        val txtTitre = findViewById<TextView>(R.id.txtTitreAppel)
        val txtSousTitre = findViewById<TextView>(R.id.txtSousTitreAppel)
        val listView = findViewById<ListView>(R.id.listStagiaires)
        val btnValider = findViewById<Button>(R.id.btnValiderAppel)

        txtTitre.text = "Faire l'appel : $groupeNom"
        txtSousTitre.text = "Sélectionnez les présences des stagiaires"

        stagiaires = mutableListOf(
            Stagiaire(1, "ELOTMANI", "Hafsa", true),
            Stagiaire(2, "BENALI", "Aymane", true),
            Stagiaire(3, "CHRAIBI", "Lina", false),
            Stagiaire(4, "DAOUDI", "Othmane", true),
            Stagiaire(5, "EL AMrani", "Salma", true),
            Stagiaire(6, "KASSIMI", "Youssef", false),
            Stagiaire(7, "NASSIRI", "Aabla", true)
        )

        adapter = StagiaireAdapter(this, stagiaires)
        listView.adapter = adapter

        btnValider.setOnClickListener {
            val presents = stagiaires.count { it.estPresent }
            val absents = stagiaires.size - presents

            AlertDialog.Builder(this)
                .setTitle("Confirmation de l'appel")
                .setMessage("Groupe : $groupeNom\nPrésents : $presents\nAbsents : $absents\n\nVoulez-vous enregistrer cette séance ?")
                .setPositiveButton("Enregistrer") { _, _ ->
                    Toast.makeText(this, "Appel enregistré avec succès pour $groupeNom !", Toast.LENGTH_LONG).show()
                    finish()
                }
                .setNegativeButton("Annuler", null)
                .show()
        }
    }
}
