package com.example.surya.flipzone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminWelcome extends AppCompatActivity {
    String s1;
    TextView t1;
    CardView c1,c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
        t1=(TextView)findViewById(R.id.helloadmin);
        s1=getIntent().getStringExtra("key");
        t1.setText("Hello "+s1);
        c1=(CardView)findViewById(R.id.newproduct);
        c2=(CardView)findViewById(R.id.productmanagement);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(AdminWelcome.this,NewProduct.class);
                it.putExtra("name",s1);
                startActivity(it);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminWelcome.this,ProductManagement.class);
                startActivity(i);
            }
        });
    }
}
