package com.example.hobbyroute_ver_1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Intent intent = getIntent();
        final String name = intent.getExtras().getString("name");
        final String keyword = intent.getExtras().getString("keyword");
        final String id = intent.getExtras().getString("id");

        ImageButton menubtn = (ImageButton) findViewById(R.id.menu);
        menubtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(name.equals("NAME"))
                {
                    Toast.makeText(SelectActivity.this,"로그인하셔야 이용할 수 있어요.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("keyword", keyword);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });

        ImageButton browsebtn = (ImageButton) findViewById(R.id.browsebtn);
        browsebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), BrowseActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("keyword", keyword);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        ImageButton rcmdbtn = (ImageButton) findViewById(R.id.rcmdbtn);
        rcmdbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(name.equals("NAME"))
                {
                    Toast.makeText(SelectActivity.this,"로그인하셔야 이용할 수 있어요.",Toast.LENGTH_SHORT).show();
                }
                else if(keyword.equals("null")){
                    Toast.makeText(SelectActivity.this,"신규 유저는 '둘러보기'부터 이용해 주세요.",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), RecommendationActivity_normal.class);
                    intent.putExtra("name", name);
                    intent.putExtra("keyword", keyword);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
    }
}
