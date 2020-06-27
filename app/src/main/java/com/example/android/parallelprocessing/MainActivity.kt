package com.example.android.parallelprocessing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.topicList)
        val topicsArray = resources.getStringArray(R.array.topicsArray)
        val videoIdsArray = resources.getStringArray(R.array.videoIdArray)
        val topicsListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, topicsArray)

        listView.setOnItemClickListener { parent, view, position, id ->
            val clickedElement = topicsListAdapter.getItem(position)

            if (position < videoIdsArray.size) {
                Toast.makeText(this, "You clicked $clickedElement", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, VideoActivity::class.java)
                val videoId = videoIdsArray[position]
                intent.putExtra("position", position)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
