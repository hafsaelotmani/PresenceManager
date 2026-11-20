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

    override fun getCount(): Int {
        return groupes.size
    }

    override fun getItem(position: Int): Groupe {
        return groupes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_groupe, parent, false)
        val txtNom = view.findViewById<TextView>(
            R.id.txtNomGroupe
        )
        txtNom.text = groupes[position].nom
        return view
    }


}
