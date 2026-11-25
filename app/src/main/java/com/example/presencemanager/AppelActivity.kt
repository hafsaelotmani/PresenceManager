package com.example.presencemanager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AppelActivity : AppCompatActivity() {

    private lateinit var listDonnes: MutableList<donne>
    private lateinit var adapter: StagiaireRecyclerAdapter
    private lateinit var txtCompteurPresents: TextView
    private lateinit var txtCompteurAbsents: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycleview)

        val groupeNom = intent.getStringExtra("GROUPE_NOM") ?: "DEVOAM202"
        val txtTitre = findViewById<TextView>(R.id.txtTitreAppelRecycler)
        txtTitre.text = "Appel — Séance 22 • $groupeNom"

        txtCompteurPresents = findViewById(R.id.txtCompteurPresents)
        txtCompteurAbsents = findViewById(R.id.txtCompteurAbsents)
        val btnTousPresents = findViewById<Button>(R.id.btnTousPresents)
        val btnValider = findViewById<Button>(R.id.btnValiderRecycler)

        listDonnes = mutableListOf(
            donne("CEF101", "ABKHICH ABDELBADIAE", true),
            donne("CEF102", "AFARIATE HANAN", true),
            donne("CEF103", "BELGHMAN ABDELGHAFOUR", true),
            donne("CEF104", "BEN MASSAOUD AHMED ADAM", true),
            donne("CEF105", "BENAZZOUZA ILIAS", true),
            donne("CEF106", "BOUDAHYR ZAKARIA", true),
            donne("CEF107", "BOUHAMIDI FARAH", true),
            donne("CEF108", "EL OTMANI HAFSA", true),
            donne("CEF109", "EL YAAKOUBI AYMEN", true),
            donne("CEF110", "ELFERGANI LAMYAA", true),
            donne("CEF111", "ELHAIRCHE MOHAMED", true),
            donne("CEF112", "ESSADEK HIBATALLAH", true),
            donne("CEF113", "HMIDATE MEHDI", true)
        )

        val rvStagiaires = findViewById<RecyclerView>(R.id.rvStagiaires)
        rvStagiaires.layoutManager = LinearLayoutManager(this)

        adapter = StagiaireRecyclerAdapter(listDonnes) {
            actualiserCompteurs()
        }
        rvStagiaires.adapter = adapter

        actualiserCompteurs()

        btnTousPresents.setOnClickListener {
            listDonnes.forEach { it.present = true }
            adapter.notifyDataSetChanged()
            actualiserCompteurs()
        }

        btnValider.setOnClickListener {
            val presents = listDonnes.count { it.present }
            val absents = listDonnes.size - presents

            AlertDialog.Builder(this)
                .setTitle("Validation de l'appel")
                .setMessage("Séance : $groupeNom\nPrésents : $presents\nAbsents : $absents\n\nVoulez-vous enregistrer cette séance ?")
                .setPositiveButton("Valider") { _, _ ->
                    Toast.makeText(this, "Appel enregistré avec succès !", Toast.LENGTH_LONG).show()
                    finish()
                }
                .setNegativeButton("Annuler", null)
                .show()
        }
    }

    private fun actualiserCompteurs() {
        val presents = listDonnes.count { it.present }
        val absents = listDonnes.size - presents

        txtCompteurPresents.text = "Présents : $presents"
        txtCompteurAbsents.text = "Absents : $absents"
    }
}
