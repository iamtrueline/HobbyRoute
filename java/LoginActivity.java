package com.example.hobbyroute_ver_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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

public class LoginActivity extends AppCompatActivity {

    private EditText id, password;
    private ImageButton loginbtn;
    private ProgressBar mProgress;

    // Volley variables
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.id_editText);
        password = findViewById(R.id.password_editText);
        loginbtn = (ImageButton) findViewById(R.id.loginbtn);
        mProgress = findViewById(R.id.progressBar);
        mProgress.setVisibility(View.GONE);

        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               signIn(id.getText().toString(), password.getText().toString());
            }
        });

        ImageButton signupbtn = (ImageButton) findViewById(R.id.signupbtn);
        signupbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        ImageButton nonmemberbtn = (ImageButton) findViewById(R.id.nonmemberbtn);
        nonmemberbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                intent.putExtra("name", "NAME");
                intent.putExtra("keyword", "");
                intent.putExtra("id", "");
                startActivity(intent);
            }
        });
    }

    private void signIn( final String id, final String password) {

        mProgress.setVisibility(View.VISIBLE);
        // Initializing Request queue
        mRequestQueue = Volley.newRequestQueue(LoginActivity.this);

        mStringRequest = new StringRequest(Request.Method.POST, getBaseUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    String message = jsonObject.getString("message");
                    String name = jsonObject.getString("name");
                    String id = jsonObject.getString("id");
                    String keyword = jsonObject.getString("keyword");

                    if (success.equals("1")) {
                        mProgress.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                        // Finish
                        finish();
                        // Start activity dashboard
                        Intent intent = new Intent(LoginActivity.this,SelectActivity.class);

                        intent.putExtra("name", name);
                        intent.putExtra("keyword", keyword);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                    if (success.equals("0")) {
                        mProgress.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    mProgress.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgress.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this,"",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("password",password);
                return params;
            }
        };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);
    }

    private String getBaseUrl (){
        // Personal website for login
        return "http://Your_web_site_url";
    }
}
