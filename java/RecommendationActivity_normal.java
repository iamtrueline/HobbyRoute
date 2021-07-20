package com.example.hobbyroute_ver_1_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendationActivity_normal extends AppCompatActivity {

    private ProgressBar progressBar;
    private static final String BASE_URL = "http://Your_Hobby_DB_url";
    private List<Hobby> hobbylist;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_normal);

        Intent intent = getIntent();
        final String name = intent.getExtras().getString("name");
        final String userlog = intent.getExtras().getString("keyword");
        final String id = intent.getExtras().getString("id");

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.hobbylist_recyclerView);
        manager = new GridLayoutManager(RecommendationActivity_normal.this, 2);
        recyclerView.setLayoutManager(manager);
        hobbylist = new ArrayList<>();

        getHobby(userlog, id);
    }

    private void getHobby(final String userlog, final String id) {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);

                        String name = object.getString("name");
                        String keyword = object.getString("keyword");
                        int location = object.getInt("location");
                        String image = object.getString("image");

                        Hobby hobby = new Hobby(name, keyword, image, location, 0.0);
                        double mark = markHobby(hobby.getKeyword(), userlog);
                        hobby.setMark(mark);
                        hobbylist.add(hobby);
                        //if you use it, you'll check the score of each hobby.
                        //System.out.println("Hobby: "+hobby.getName()+", score: "+hobby.getMark());
                    }
                } catch (Exception e) {
                }

                Collections.sort(hobbylist);
                mAdapter = new RecyclerAdapter(RecommendationActivity_normal.this, hobbylist, id);
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RecommendationActivity_normal.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        });
        Volley.newRequestQueue(RecommendationActivity_normal.this).add(stringRequest);

    }

    private double markHobby(String hobbykeyword, String userlog){
        String longer = hobbykeyword, shorter = userlog;

        if (hobbykeyword.length() < userlog.length()) {
            longer = userlog;
            shorter = hobbykeyword;
        }

        int longerLength = longer.length();
        if (longerLength == 0) return 1.0;

        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    private int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int[] costs = new int[s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];

                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
}
