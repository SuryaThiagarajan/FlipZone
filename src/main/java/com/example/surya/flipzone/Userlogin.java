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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Userlogin extends AppCompatActivity {
    ProgressDialog pd;
    TextView t1;
    Button b;
    EditText e1,e2;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        e1=(EditText)findViewById(R.id.userusername);
        e2=(EditText)findViewById(R.id.userpassword);
        b=(Button)findViewById(R.id.userloginbutton);
        t1=(TextView)findViewById(R.id.usernoaccount);
        pd=new ProgressDialog(Userlogin.this);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Userlogin.this,UserSignup.class);
                startActivity(it);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });
    }
    public void userlogin() {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        pd.setMessage("Logging you in..");
        pd.show();
        StringRequest sr=new StringRequest(Request.Method.POST, "https://vanakkam.000webhostapp.com/userlogin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success"))
                {
                    Intent it=new Intent(Userlogin.this,UserWelcome.class);
                    it.putExtra("key",s1);
                    startActivity(it);
                    pd.dismiss();
                }
                else
                {
                    Toast.makeText(Userlogin.this,"Invalid Username or Password",Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Userlogin.this,error.toString(),Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> m=new HashMap<String, String>();
                m.put("userusername",s1);
                m.put("userpassword",s2);
                return m;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
        rq.add(sr);
    }
}
