package com.example.sovelluskehitysprojekti

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sovelluskehitysprojekti.api.*

class MyAdapter(private val records: RecordList) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.records_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = records.resultCount

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = records.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.recordTitle)
    }
}
