package com.example.presencemanager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StagiaireRecyclerAdapter(
    private val listStagiaires: List<donne>,
    private val onItemStateChanged: () -> Unit
) : RecyclerView.Adapter<StagiaireRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNomStagiaire: TextView = view.findViewById(R.id.txtNomStagiaire)
        val chkPresence: CheckBox = view.findViewById(R.id.chkPresence)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stagiaire, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stagiaire = listStagiaires[position]
        holder.txtNomStagiaire.text = stagiaire.nom

        holder.chkPresence.setOnCheckedChangeListener(null)
        holder.chkPresence.isChecked = stagiaire.present
        updateCheckBoxStyle(holder.chkPresence, stagiaire.present)

        holder.chkPresence.setOnCheckedChangeListener { _, isChecked ->
            stagiaire.present = isChecked
            updateCheckBoxStyle(holder.chkPresence, isChecked)
            onItemStateChanged()
        }

        holder.itemView.setOnClickListener {
            stagiaire.present = !stagiaire.present
            holder.chkPresence.isChecked = stagiaire.present
            updateCheckBoxStyle(holder.chkPresence, stagiaire.present)
            onItemStateChanged()
        }
    }

    override fun getItemCount(): Int = listStagiaires.size

    private fun updateCheckBoxStyle(checkBox: CheckBox, isPresent: Boolean) {
        if (isPresent) {
            checkBox.text = "Présent"
            checkBox.setTextColor(Color.parseColor("#059669"))
        } else {
            checkBox.text = "Absent"
            checkBox.setTextColor(Color.parseColor("#DC2626"))
        }
    }
}
