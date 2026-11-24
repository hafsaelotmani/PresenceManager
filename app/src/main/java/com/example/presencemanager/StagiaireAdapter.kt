package com.example.presencemanager

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView

class StagiaireAdapter(
    private val context: Context,
    private val stagiaires: List<Stagiaire>
) : BaseAdapter() {

    private class ViewHolder(view: View) {
        val txtNom: TextView = view.findViewById(R.id.txtNomStagiaire)
        val chkPresence: CheckBox = view.findViewById(R.id.chkPresence)
    }

    override fun getCount(): Int = stagiaires.size

    override fun getItem(position: Int): Stagiaire = stagiaires[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_stagiaire, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val stagiaire = getItem(position)
        holder.txtNom.text = "${stagiaire.nom.uppercase()} ${stagiaire.prenom}"
        holder.chkPresence.isChecked = stagiaire.estPresent

        updateStatusText(holder.chkPresence, stagiaire.estPresent)

        holder.chkPresence.setOnCheckedChangeListener { _, isChecked ->
            stagiaire.estPresent = isChecked
            updateStatusText(holder.chkPresence, isChecked)
        }

        return view
    }

    private fun updateStatusText(checkBox: CheckBox, isPresent: Boolean) {
        if (isPresent) {
            checkBox.text = "Présent"
            checkBox.setTextColor(Color.parseColor("#059669"))
        } else {
            checkBox.text = "Absent"
            checkBox.setTextColor(Color.parseColor("#DC2626"))
        }
    }
}
