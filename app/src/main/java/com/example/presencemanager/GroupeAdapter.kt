package com.example.presencemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class GroupeAdapter(
    private val context: Context,
    private val groupes: List<Groupe>
) : BaseAdapter() {

    override fun getCount(): Int = groupes.size
    override fun getItem(position: Int): Groupe = groupes[position]
    override fun getItemId(position: Int): Long = position.toLong()

    // Patron ViewHolder pour mémoriser les références aux TextView
    private class ViewHolder(val nom: TextView, val effectif: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row: View
        val holder: ViewHolder

        // 1. Recyclage de la vue avec convertView
        if (convertView == null) {
            row = LayoutInflater.from(context)
                .inflate(R.layout.item_groupe, parent, false)
            holder = ViewHolder(
                row.findViewById(R.id.txtNomGroupe),
                row.findViewById(R.id.txtEffectif)
            )
            row.tag = holder
        } else {
            row = convertView
            holder = row.tag as ViewHolder
        }

        // 2. Remplissage des données
        val groupe = getItem(position)
        holder.nom.text = groupe.nom
        holder.effectif.text = "${groupe.effectif} stagiaires"

        return row
    }
}
