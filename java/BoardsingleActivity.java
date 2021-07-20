package com.example.hobbyroute_ver_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardsingleActivity extends AppCompatActivity {
    private TextView mtitle, mname, mdate, mcontents;
    private ListView listView;
    private EditText mcomment;
    ArrayList<Comment> comeList = new ArrayList<Comment>();
    private static final String BASE_URL = "http://Your_board_site_url";
    private CommentAdapter comeAdapter;
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardsingle);

        mtitle=(TextView)findViewById(R.id.title);
        mname=findViewById(R.id.name);
        mdate=findViewById(R.id.date);
        mcontents=findViewById(R.id.contents);

        listView = (ListView)findViewById(R.id.listView);

        Intent intent = getIntent();

        final int id = intent.getIntExtra("id",-1);
        String title =intent.getStringExtra("title");
        final String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String contents = intent.getStringExtra("contents");
        final String username = intent.getStringExtra(("username"));

        if (intent !=null){
            mtitle.setText(title);
            mname.setText(name);
            mdate.setText(date);
            mcontents.setText(contents);
        }

    }}

