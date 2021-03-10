package com.example.sovelluskehitysprojekti

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sovelluskehitysprojekti.api.*
import org.w3c.dom.Text

class MyAdapter(private val records: RecordList) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.records_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = records.records.size

    //resultCount, buildings, formats, nonPresenterAuthors/name: "Sibelius", Suuret säveltäjät title
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = records.records[position].title
        holder.format.text = records.records[position].formats[0].translated
        holder.author.text = records.records[position].nonPresenterAuthors[0].name
        holder.building0.text = records.records[position].buildings[0].translated
        holder.building1.text = records.records[position].buildings[1].translated
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.recordTitle)
        val format: TextView = itemView.findViewById(R.id.recordResultItemFormat)
        val author: TextView = itemView.findViewById(R.id.recordAuthor)
        val building0: TextView = itemView.findViewById(R.id.recordBuilding0)
        val building1: TextView = itemView.findViewById(R.id.recordBuilding1)
    }
}
