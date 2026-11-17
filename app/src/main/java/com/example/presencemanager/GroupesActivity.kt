package com.example.presencemanager

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GroupesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groupes)

        val listView = findViewById<ListView>(R.id.list)

        val groupes = listOf(
            Groupe("DEVOAM201", "Groupe de développement"),
            Groupe("DEVOAM202", "Groupe de développement"),
            Groupe("DEVOAM203", "Groupe de développement"),
            Groupe("DEVOAM204", "Groupe de développement"),
            Groupe("DEVOAM205", "Groupe de développement")
        )

        val adapter = GroupeAdapter(this, groupes)

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val groupe = groupes[position]

            Toast.makeText(
                this,
                "Groupe sélectionné : ${groupe.nom}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
