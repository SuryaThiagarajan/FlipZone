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

public class Adminlogin extends AppCompatActivity {
    ProgressDialog pd;
    TextView t1;
    Button b;
    EditText e1,e2;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        e1=(EditText)findViewById(R.id.adminusername);
        e2=(EditText)findViewById(R.id.adminpassword);
        b=(Button)findViewById(R.id.adminloginbutton);
        t1=(TextView)findViewById(R.id.adminnoaccount);
        pd=new ProgressDialog(Adminlogin.this);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Adminlogin.this,AdminSignup.class);
                startActivity(it);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminlogin();
            }
        });
    }
    public void adminlogin() {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        pd.setMessage("Loging you in..");
        pd.show();
        StringRequest sr=new StringRequest(Request.Method.POST, "https://vanakkam.000webhostapp.com/adminlogin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success"))
                {
                    Intent i=new Intent(Adminlogin.this,AdminWelcome.class);
                    i.putExtra("key",s1);
                    startActivity(i);
                    pd.dismiss();
                }
                else
                {
                    Toast.makeText(Adminlogin.this,"Invalid Username or Password",Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Adminlogin.this,error.toString(),Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> m=new HashMap<String, String>();
                m.put("adminusername",s1);
                m.put("adminpassword",s2);
                return m;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
        rq.add(sr);
    }
}
