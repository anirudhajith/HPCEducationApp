# HPC Education App

The three activities SubjectsActivity, TopicsActivity and VideoActivity constitute the the three levels of the tree. 
* The topmost level, SubjectsActivity displays a list of all the subjects. 
* On selecting a subject, TopicsActivity is loaded up with a list of the topics corresponding to that subject. 
* When a topic is clicked, VideoActivity starts containing an embedded YouTube video corresponding to the topic and a transcript underneath.

All the data associated with the app including lists of topics, video URLs and transcripts are stored in a central json file at res/raw/data.json. The schema should be self-explanatory on examination. This file is read only by SubjectsActivity and the requisite subset of information required by the next activity is passed on using Intents.

Since the official YouTube API for Android is famously a nightmare, we use a 3rd-party library (which works like a charm) to embed the YouTube videos: https://github.com/PierfrancescoSoffritti/android-youtube-player

The apk can be found under app/release/
