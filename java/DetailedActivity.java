package com.example.hobbyroute_ver_1_2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailedActivity extends AppCompatActivity {
    private ImageView mImage;
    private TextView mTitle, mRating, mPrice;
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;
    private EditText keyword, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        mImage = findViewById(R.id.image_view);
        mPrice = findViewById(R.id.price);
        mRating = findViewById(R.id.rating);
        mTitle = findViewById(R.id.name);

        // Catching incoming intent
        final Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String keyword=intent.getStringExtra("keyword");
        String title = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        final int loca = intent.getIntExtra("location", 0);

        check(id, keyword);
        System.out.println(loca);

        if (intent !=null){
            mTitle.setText(title);
            Glide.with(DetailedActivity.this).load(image).into(mImage);
        }

        ImageButton routebtn = (ImageButton) findViewById(R.id.routeButton);
        routebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("location", loca);
                startActivity(intent);
            }
        });
    }

    private void check(final String id, final String keyword){

        mRequestQueue = Volley.newRequestQueue(DetailedActivity.this);
        // Progress

        mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String message = jsonObject.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailedActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("keyword",keyword);
                return params;
            }
        };
        mRequestQueue.add(mStringRequest);
    }


    private String getBaseUrl (){
        return "http://Your_Hobby_DB_url";
    }
}
