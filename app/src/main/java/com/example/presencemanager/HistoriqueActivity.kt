package com.example.presencemanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class SeanceHistorique(
    val date: String,
    val groupe: String,
    val presents: Int,
    val absents: Int
)

class HistoriqueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historique)

        val listView = findViewById<ListView>(R.id.listHistorique)

        val historique = listOf(
            SeanceHistorique("20/02/2025 - 08:30", "DEVOAM201", 18, 2),
            SeanceHistorique("19/02/2025 - 14:00", "DEVOAM202", 15, 3),
            SeanceHistorique("18/02/2025 - 10:15", "DEVOAM203", 20, 0),
            SeanceHistorique("17/02/2025 - 08:30", "DEVOAM204", 17, 1),
            SeanceHistorique("16/02/2025 - 11:00", "DEVOAM205", 19, 1)
        )

        listView.adapter = object : BaseAdapter() {
            override fun getCount(): Int = historique.size
            override fun getItem(position: Int): SeanceHistorique = historique[position]
            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(this@HistoriqueActivity)
                    .inflate(R.layout.item_historique, parent, false)

                val item = getItem(position)
                view.findViewById<TextView>(R.id.txtDateHistorique).text = item.date
                view.findViewById<TextView>(R.id.txtGroupeHistorique).text = item.groupe
                view.findViewById<TextView>(R.id.txtBilanHistorique).text =
                    "${item.presents} Présents • ${item.absents} Absents"

                return view
            }
        }
    }
}
