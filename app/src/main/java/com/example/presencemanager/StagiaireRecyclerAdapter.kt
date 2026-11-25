package com.example.presencemanager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StagiaireRecyclerAdapter(
    private val listStagiaires: List<donne>,
    private val onItemStateChanged: () -> Unit
) : RecyclerView.Adapter<StagiaireRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNom: TextView = view.findViewById(R.id.txtNom)
        val txtCef: TextView = view.findViewById(R.id.txtcef)
        val txtEtat: TextView = view.findViewById(R.id.txtEtat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stagire, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stagiaire = listStagiaires[position]
        holder.txtNom.text = stagiaire.nom
        holder.txtCef.text = "CEF : ${stagiaire.matricule}"

        updateEtatView(holder.txtEtat, stagiaire.present)

        holder.itemView.setOnClickListener {
            stagiaire.present = !stagiaire.present
            updateEtatView(holder.txtEtat, stagiaire.present)
            onItemStateChanged()
        }

        holder.txtEtat.setOnClickListener {
            stagiaire.present = !stagiaire.present
            updateEtatView(holder.txtEtat, stagiaire.present)
            onItemStateChanged()
        }
    }

    override fun getItemCount(): Int = listStagiaires.size

    private fun updateEtatView(textView: TextView, isPresent: Boolean) {
        if (isPresent) {
            textView.text = "PRÉSENT"
            textView.setTextColor(Color.parseColor("#065F46"))
            textView.setBackgroundColor(Color.parseColor("#D1FAE5"))
        } else {
            textView.text = "ABSENT"
            textView.setTextColor(Color.parseColor("#991B1B"))
            textView.setBackgroundColor(Color.parseColor("#FEE2E2"))
        }
    }
}
