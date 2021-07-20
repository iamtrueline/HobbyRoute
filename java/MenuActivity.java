package com.example.hobbyroute_ver_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        final String name = intent.getExtras().getString("name");
        final String keyword = intent.getExtras().getString("keyword");

        String message = name + "님, 게시판을 이용해보시겠어요?";
        Toast.makeText(MenuActivity.this,message,Toast.LENGTH_SHORT).show();

        ImageButton startbtn = (ImageButton) findViewById(R.id.plusbtn);
        startbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), NboardActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }

        });
    }
}
