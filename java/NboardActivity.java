package com.example.hobbyroute_ver_1_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class NboardActivity extends AppCompatActivity {

    ArrayList<Post> postList = new ArrayList<Post>();
    private static final String BASE_URL = "http://Your_board_site_url";
    private ListView listView;
    private BoardAdapter boardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nboard);

        listView = (ListView)findViewById(R.id.listView);

        Intent intent = getIntent();
        final String name = intent.getExtras().getString("name");

       // this.InitializeMovieData();
        this.getPost();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){

                Intent intent = new Intent(getApplicationContext(),BoardsingleActivity.class);

                intent.putExtra("id",boardAdapter.getItem(position).getID());
                intent.putExtra("name",boardAdapter.getItem(position).getName());
                intent.putExtra("title",boardAdapter.getItem(position).getTitle());
                intent.putExtra("contents",boardAdapter.getItem(position).getContents());
                intent.putExtra("date",boardAdapter.getItem(position).getDate());
                intent.putExtra("username",name);
                startActivity(intent);
               // Toast.makeText(getApplicationContext(), boardAdapter.getItem(position).getName(),
                      //  Toast.LENGTH_LONG).show();
            }
        });

        ImageButton startbtn = (ImageButton) findViewById(R.id.button);
        startbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }

    private void getPost() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);

                        String name = object.getString("name");
                        String date = object.getString("date");
                        int id = object.getInt("id");
                        String title= object.getString("title");
                        String contents= object.getString("contents");
                        System.out.println(name+" "+date+" "+id+" "+title+" "+contents);
                        postList.add(new Post(id, date, name, title, contents));
                    }
                } catch (Exception e) {
                }
                boardAdapter = new BoardAdapter(NboardActivity.this,postList);
                listView.setAdapter(boardAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NboardActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(NboardActivity.this).add(stringRequest);
    }
}
