package com.example.presencemanager

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class GroupesActivity : AppCompatActivity() {

    private lateinit var lvGroupes: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groupes)

        lvGroupes = findViewById(R.id.lvGroupes)
        val btnArrayAdapter = findViewById<Button>(R.id.btnArrayAdapter)
        val btnBaseAdapter = findViewById<Button>(R.id.btnBaseAdapter)

        // Clic sur le bouton ArrayAdapter -> Appel de la méthode 1
        btnArrayAdapter.setOnClickListener {
            afficherAvecArrayAdapter()
        }

        // Clic sur le bouton BaseAdapter -> Appel de la méthode 2
        btnBaseAdapter.setOnClickListener {
            afficherAvecBaseAdapter()
        }

        // Affichage initial par défaut avec BaseAdapter
        afficherAvecBaseAdapter()
    }

    // MÉTHODE 1 : ARRAYADAPTER (Liste simple)
    fun afficherAvecArrayAdapter() {
        val groupes = arrayOf(
            "DEVAM201", "DEVAM202", "DEVAM203",
            "DEVAM204", "DEVAM205", "DEVAM206",
            "DEVAM207", "DEVAM208", "DEVAM209", "DEVAM210"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            groupes
        )
        lvGroupes.adapter = adapter

        lvGroupes.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this,
                groupes[position],
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // MÉTHODE 2 : BASEADAPTER (Liste personnalisée avec ViewHolder)
    fun afficherAvecBaseAdapter() {
        val groupes = listOf(
            Groupe("DEVAM201", 24),
            Groupe("DEVAM202", 22),
            Groupe("DEVAM203", 26),
            Groupe("DEVAM204", 23),
            Groupe("DEVAM205", 25),
            Groupe("DEVAM206", 21),
            Groupe("DEVAM207", 20),
            Groupe("DEVAM208", 22),
            Groupe("DEVAM209", 25),
            Groupe("DEVAM210", 23)
        )

        val adapter = GroupeAdapter(this, groupes)
        lvGroupes.adapter = adapter

        lvGroupes.setOnItemClickListener { _, _, position, _ ->
            val groupe = adapter.getItem(position)
            AlertDialog.Builder(this)
                .setTitle(groupe.nom)
                .setMessage("Effectif : ${groupe.effectif} stagiaires")
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
