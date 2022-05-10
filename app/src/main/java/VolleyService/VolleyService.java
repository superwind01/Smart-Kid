package VolleyService;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import API.Book;
import API.Class;
import API.Lesson;
import API.ModelCommon;
import API.Song;
import API.Speak;
import API.Topic;
import API.Vocabulary;
import API.VocabularyByTopicLesson;

public class VolleyService {
    public static ModelCommon getRequest(Context context, String Url, final VolleyResponseListener listener){

        ModelCommon modelCommon = new ModelCommon();

        //URL API
        String url =ServiceInfo.BaseUrl + Url;

        //Get Api
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(JSONArray response) {
                Log.e("LogResponse", response.toString());
                if (response != null) {

                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();

                    //THUC HIEN LAY DATA THEO URL
                    if(Url == "Class/") {

                        ArrayList<Class> classes = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject rec = response.optJSONObject(i);
                            JsonElement mJson = parser.parse(String.valueOf(rec));
                            classes.add(gson.fromJson(mJson, Class.class));
                        }
                        modelCommon.setClasses(classes);
                    }
                    else if(Url.startsWith("Vocalbulary/"))
                    {
                        ArrayList<Vocabulary> vocabularies = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject rec = response.optJSONObject(i);
                            JsonElement mJson = parser.parse(String.valueOf(rec));
                            vocabularies.add(gson.fromJson(mJson, Vocabulary.class));
                        }
                        modelCommon.setVocabularies(vocabularies);
                    }
                    else if(Url.startsWith("Song/"))
                    {
                        ArrayList<Song> songs = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject rec = response.optJSONObject(i);
                            JsonElement mJson = parser.parse(String.valueOf(rec));
                            songs.add(gson.fromJson(mJson, Song.class));
                        }
                        modelCommon.setSongs(songs);
                    }
                    else if(Url.startsWith("Topic/"))
                    {
                        ArrayList<Topic> topics = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject rec = response.optJSONObject(i);
                            JsonElement mJson = parser.parse(String.valueOf(rec));
                            topics.add(gson.fromJson(mJson, Topic.class));
                        }
                        modelCommon.setTopics(topics);
                    }
                    else if(Url.startsWith("Book/"))
                    {
                        ArrayList<Book> books = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject rec = response.optJSONObject(i);
                            JsonElement mJson = parser.parse(String.valueOf(rec));
                            books.add(gson.fromJson(mJson, Book.class));
                        }
                        modelCommon.setBooks(books);
                    }
                    else if(Url.startsWith("Speak/"))
                    {
                        ArrayList<Speak> speaks = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject rec = response.optJSONObject(i);
                            JsonElement mJson = parser.parse(String.valueOf(rec));
                            speaks.add(gson.fromJson(mJson, Speak.class));
                        }
                        modelCommon.setSpeaks(speaks);
                    }
                    else if(Url.startsWith("Lesson/"))
                    {
                        ArrayList<Lesson> lessons = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject rec = response.optJSONObject(i);
                            JsonElement mJson = parser.parse(String.valueOf(rec));
                            lessons.add(gson.fromJson(mJson, Lesson.class));
                        }
                        modelCommon.setLessons(lessons);
                    }
                    else if(Url.startsWith("VocabularyByTopicLesson/"))
                    {
                        ArrayList<VocabularyByTopicLesson> vocabularyByTopicLessons = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject rec = response.optJSONObject(i);
                            JsonElement mJson = parser.parse(String.valueOf(rec));
                            vocabularyByTopicLessons.add(gson.fromJson(mJson, VocabularyByTopicLesson.class));
                        }
                        modelCommon.setVocabularyByTopicLessons(vocabularyByTopicLessons);
                    }
                    //TAO SU KIEN CHO LISTENER
                    listener.onResponse(modelCommon);
            } }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Log",error.toString());
            }
        });

        requestQueue.add(jsonArrayRequest);
        return modelCommon;
    }
}
