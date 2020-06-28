package com.example.android.parallelprocessing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import org.json.JSONObject

class TopicsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)


        val subjectJsonObject = JSONObject(intent.getStringExtra("subject_data"))
        val topicsJsonArray = subjectJsonObject.getJSONArray("content")
        var topicNames = arrayOfNulls<String>(topicsJsonArray.length())
        for(i in 0 until topicsJsonArray.length()) {
            topicNames[i] = topicsJsonArray.getJSONObject(i).getString("title")
        }

        var topicsListView = findViewById<ListView>(R.id.topicsList)
        val topicsListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, topicNames)
        topicsListView.adapter = topicsListAdapter

        topicsListView.setOnItemClickListener { parent, view, position, id ->
            val clickedElement = topicsListAdapter.getItem(position)
            val topicJsonObject = topicsJsonArray.getJSONObject(position)
            val title = topicJsonObject.getString("title")
            val videoId = topicJsonObject.getString("videoId")
            val transcript = topicJsonObject.getString("transcript")
            val valid = topicJsonObject.getBoolean("valid")

            if (valid) {
                Toast.makeText(this, "You clicked $clickedElement", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, VideoActivity::class.java)
                intent.putExtra("title", title)
                intent.putExtra("videoId", videoId)
                intent.putExtra("transcript", transcript)

                startActivity(intent)
            } else {
                Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
