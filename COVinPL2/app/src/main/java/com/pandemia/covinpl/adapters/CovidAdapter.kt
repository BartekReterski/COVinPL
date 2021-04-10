package com.pandemia.covinpl.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.pandemia.covinpl.R
import com.pandemia.covinpl.models.CovidModel

class CovidAdapter(val covids: List<CovidModel>): RecyclerView.Adapter<CovidViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_items, parent, false)
        return CovidViewHolder(view)
    }

    override fun getItemCount(): Int {
        return covids.size
    }

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {

        return holder.bind(covids[position])

    }
}

class CovidViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    //private val photo: ImageView = itemView.findViewById(R.id.movie_photo)
    private val totalCases: TextView = itemView.findViewById(R.id.covid_total)
    private val todayCases:TextView = itemView.findViewById(R.id.covid_today)
    private val todayDeaths:TextView = itemView.findViewById(R.id.covid_today_deaths)
    private val countries=itemView.findViewById<TextView>(R.id.country);

    fun bind(data: CovidModel) {

        //Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w500${movie.poster_path}").into(photo)
        totalCases.text = "Total Cases: " + data.cases.toString()
        todayCases.text = "Today Cases: " + data.todayCases.toString()
        todayDeaths.text = "Today Deaths : " + data.todayDeaths.toString()
        countries.text = data.country

        val summaryData = "Total Cases: " + data.cases.toString() + "\n"+"Today Cases: " + data.todayCases.toString() + "\n"+"Today Deaths : " + data.todayDeaths.toString()

        itemView.setOnLongClickListener() {

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, summaryData)
            intent.type = "text/plain"
            itemView.context.startActivity(Intent.createChooser(intent, "Share To:"))

            return@setOnLongClickListener true
        }

    }

}