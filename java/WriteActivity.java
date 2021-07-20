package com.example.hobbyroute_ver_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WriteActivity extends AppCompatActivity {

    private EditText mtitle, mcontent;
    private ImageButton assign;
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        mtitle = findViewById((R.id.title));
        mcontent = findViewById(R.id.content);
        assign = findViewById(R.id.assign);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        assign.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View v){
                    createPost(name,mtitle.getText().toString(),mcontent.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), NboardActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
                });
    }
    private void createPost(final String name, final String title, final String contents){
        mRequestQueue = Volley.newRequestQueue(WriteActivity.this);
        mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");
                    String message = jsonObject.getString("message");

                    if (success.equals("1")) {
                        Toast.makeText(WriteActivity.this,message,Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(WriteActivity.this,message,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(WriteActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WriteActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("name",name);
                params.put("title",title);
                params.put("contents",contents);
                return params;
            }
        };
        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);
    }
    private String getBaseUrl (){
        return "http://Your_board_site_url";
    }
}
