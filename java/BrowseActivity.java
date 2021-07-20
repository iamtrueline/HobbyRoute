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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BrowseActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private static  final String BASE_URL = "http://Your_Hobby_DB_url";
    private List<Hobby> hobbylist;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        Intent intent = getIntent();
        final String name = intent.getExtras().getString("name");
        final String userlog = intent.getExtras().getString("keyword");
        final String id = intent.getExtras().getString("id");

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.hobbylist_recyclerView);
        manager = new GridLayoutManager(BrowseActivity.this, 2);
        recyclerView.setLayoutManager(manager);
        hobbylist = new ArrayList<>();

        getHobby(id);

    }
    private void getHobby (final String id){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            System.out.println(array.length());
                            for (int i = 0; i<array.length(); i++){
                                System.out.println(i);
                                JSONObject object = array.getJSONObject(i);

                                String name = object.getString("name");
                                String keyword = object.getString("keyword");
                                int location = object.getInt("location");
                                String image = object.getString("image");

                                Hobby hobby = new Hobby(name,keyword,image,location, 0.0);
                                hobbylist.add(hobby);
                            }

                        }catch (Exception e){
                        }

                        mAdapter = new RecyclerAdapter(BrowseActivity.this,hobbylist, id);
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(BrowseActivity.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(BrowseActivity.this).add(stringRequest);
    }
}
