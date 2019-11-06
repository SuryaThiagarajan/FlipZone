package com.example.surya.flipzone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AdminSignup extends AppCompatActivity {
    ProgressDialog pd;
    TextView t1;
    Button b;
    EditText e1,e2,e3,e4,e5,e6,e7;
    String s1,s2,s3,s4,s5,s6,s7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        e1=(EditText)findViewById(R.id.adminfirstname);
        e2=(EditText)findViewById(R.id.adminlastname);
        e3=(EditText)findViewById(R.id.adminusername);
        e4=(EditText)findViewById(R.id.adminemail);
        e5=(EditText)findViewById(R.id.adminphoneno);
        e6=(EditText)findViewById(R.id.adminpassword);
        e7=(EditText)findViewById(R.id.adminreenterpassword);
        pd=new ProgressDialog(AdminSignup.this);
        b=(Button)findViewById(R.id.adminloginbutton);

        t1=(TextView)findViewById(R.id.adminaccount);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(AdminSignup.this,Adminlogin.class);
                startActivity(it);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminsignup();
            }
        });
    }
    public void adminsignup() {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        s3=e3.getText().toString();
        s4=e4.getText().toString();
        s5=e5.getText().toString();
        s6=e6.getText().toString();
        s7=e7.getText().toString();
        pd.setMessage("Creating an account..");
        pd.show();
        StringRequest sr=new StringRequest(Request.Method.POST, "https://vanakkam.000webhostapp.com/adminsignup.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AdminSignup.this,response,Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminSignup.this,error.toString(),Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> m=new HashMap<String, String>();
                m.put("adminfirstname",s1);
                m.put("adminlastname",s2);
                m.put("adminusername",s3);
                m.put("adminemail",s4);
                m.put("adminphoneno",s5);
                m.put("adminpass",s6);
                return m;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
        rq.add(sr);
    }
}

