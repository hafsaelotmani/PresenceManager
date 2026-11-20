package com.example.presencemanager

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
class GroupesActivity : AppCompatActivity() {
    private lateinit var groupes: MutableList<Groupe>
    private lateinit var adapter: GroupeAdapter

    private var selectedPosition = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groupes)
        val listView = findViewById<ListView>(R.id.list)
        groupes = mutableListOf(
            Groupe("DEVOAM201", "Groupe de développement"),
            Groupe("DEVOAM202", "Groupe de développement"),
            Groupe("DEVOAM203", "Groupe de développement"),
            Groupe("DEVOAM204", "Groupe de développement"),
            Groupe("DEVOAM205", "Groupe de développement")
        )
        adapter = GroupeAdapter(this, groupes)
        listView.adapter = adapter

        listView.setOnItemLongClickListener { _, _, position, _ ->
            selectedPosition = position
            startSupportActionMode(actionModeCallback)
            true
        }
    }
    private val actionModeCallback =
        object : ActionMode.Callback {
            override fun onCreateActionMode(
                mode: ActionMode,
                menu: Menu
            ): Boolean {
                mode.menuInflater.inflate(
                    R.menu.menu_groupe,
                    menu
                )
                mode.title = "Groupe sélectionné"
                return true
            }
            override fun onPrepareActionMode(
                mode: ActionMode,
                menu: Menu
            ): Boolean = false
            override fun onActionItemClicked(
                mode: ActionMode,
                item: MenuItem
            ): Boolean {
                when (item.itemId) {
                    R.id.action_modifier -> {
                        modifierGroupe(selectedPosition)
                        mode.finish()
                        return true
                    }
                    R.id.action_supprimer -> {
                        supprimerGroupe(selectedPosition)
                        mode.finish()
                        return true
                    }
                }
                return false
            }
            override fun onDestroyActionMode(mode: ActionMode) {
                selectedPosition = -1
            }
        }
    private fun modifierGroupe(position: Int) {
        val groupe = groupes[position]
        val input = EditText(this)
        input.setText(groupe.nom)
        AlertDialog.Builder(this)
            .setTitle("Modifier le groupe")
            .setView(input)
            .setPositiveButton("Enregistrer") { _, _ ->
                val nouveauNom = input.text.toString().trim()
                if (nouveauNom.isNotEmpty()) {
                    groupes[position] = groupe.copy(
                        nom = nouveauNom
                    )
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }
    private fun supprimerGroupe(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Supprimer le groupe")
            .setMessage("Voulez-vous vraiment supprimer ce groupe ?")
            .setPositiveButton("Oui") { _, _ ->
                groupes.removeAt(position)
                adapter.notifyDataSetChanged()
                Toast.makeText(
                    this,
                    "Groupe supprimé",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Non", null)
            .show()
    }
}
