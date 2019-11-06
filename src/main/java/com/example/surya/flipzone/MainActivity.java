package com.example.surya.flipzone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView t1,t2;
    ImageView i1,i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.textView5);
        t2=(TextView)findViewById(R.id.textView6);
        i1=(ImageView)findViewById(R.id.imageView6);
        i2=(ImageView)findViewById(R.id.imageView7);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin();
            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user();
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user();
            }
        });
    }
    public void admin() {
        Intent it=new Intent(MainActivity.this,Adminlogin.class);
        startActivity(it);
    }
    public void user() {
        Intent it=new Intent(MainActivity.this,Userlogin.class);
        startActivity(it);
    }
}
