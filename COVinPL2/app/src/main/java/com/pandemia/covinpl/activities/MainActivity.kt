package com.pandemia.covinpl.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pandemia.covinpl.R
import com.pandemia.covinpl.`interface`.ApiInterface
import com.pandemia.covinpl.adapters.CovidAdapter
import com.pandemia.covinpl.models.CovidModel
import com.pandemia.covinpl.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCOVIDDataFromPL()
        FloatingButton()

    }


    fun getCOVIDDataFromPL(){

     val progressBar= findViewById<ProgressBar>(R.id.progress_bar)
     val recyvlerView= findViewById<RecyclerView>(R.id.recyclerView)
     val request= ApiService.buildService(ApiInterface::class.java)
     val call= request.getCovidData()

     call.enqueue(object: Callback<List<CovidModel>>{
         override fun onFailure(call: Call<List<CovidModel>>, t: Throwable) {

             progressBar.visibility=View.VISIBLE;
             Toast.makeText(this@MainActivity,"Wystąpił błąd "+t.localizedMessage,Toast.LENGTH_LONG).show()
         }

         override fun onResponse(call: Call<List<CovidModel>>, response: Response<List<CovidModel>>) {

             if(response.isSuccessful){

                 progressBar.visibility=(View.GONE)
                 showData(response.body()!!)

         }


     }

 })


}

    private fun showData(body: List<CovidModel>) {
        val recyclerView= findViewById<RecyclerView>(R.id.recyclerView);

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=CovidAdapter(body)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.nav_search -> Toast.makeText(this,"Clicked search",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun FloatingButton(){

        val floatingActionButton =findViewById<FloatingActionButton>(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(){

            Toast.makeText(this,"Clicked floating action button",Toast.LENGTH_LONG).show()
        }
    }
}
