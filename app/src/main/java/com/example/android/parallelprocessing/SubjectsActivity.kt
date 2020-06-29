package com.example.android.parallelprocessing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import org.json.JSONObject

class SubjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)

        val resId = resources.getIdentifier("data","raw", packageName)
        val iStream = resources.openRawResource(resId)

        val data = iStream.bufferedReader().use { it.readText() }
        val dataJsonObject = JSONObject(data)
        val subjectsJsonArray = dataJsonObject.getJSONArray("subjects")
        var subjects = arrayOfNulls<String>(subjectsJsonArray.length())
        for(i in 0 until subjectsJsonArray.length()) {
            subjects[i] = subjectsJsonArray.getJSONObject(i).getString("name")
        }

        var subjectsListView = findViewById<ListView>(R.id.subjectsList)
        val subjectsListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subjects)
        subjectsListView.adapter = subjectsListAdapter

        subjectsListView.setOnItemClickListener { parent, view, position, id ->
            val clickedElement = subjectsListAdapter.getItem(position)

            Toast.makeText(this, "$clickedElement", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TopicsActivity::class.java)
            intent.putExtra("subject_data", subjectsJsonArray.getJSONObject(position).toString())
            startActivity(intent)
        }
    }
}
