package com.example.surya.flipzone;

import android.os.DeadObjectException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Detailview extends AppCompatActivity {
    String s1,s2,s3,s4,s5;
    ImageView i1;
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);
        s1=getIntent().getStringExtra("key");
        t1=(TextView)findViewById(R.id.pdname);
        i1=(ImageView)findViewById(R.id.pdimg);
        t2=(TextView)findViewById(R.id.pdcategory);
        t3=(TextView)findViewById(R.id.pddesc);
        t4=(TextView)findViewById(R.id.price);
        t1.setText(s1);
        call();
    }
    public void call() {
        StringRequest sr=new StringRequest(Request.Method.POST, "https://vanakkam.000webhostapp.com/detailview.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Detailview.this,response,Toast.LENGTH_LONG).show();
                try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray project_details= jsonObject.getJSONArray("project_details");
                for(int i=0;i<project_details.length();i++) {
                    JSONObject actor = project_details.getJSONObject(i);
                    s2=actor.getString("image");
                    Picasso.get().load(s2).into(i1);
                    s3=actor.getString("price");
                    t4.setText("₹"+s3);
                    s4=actor.getString("description");
                    t3.setText(s4);
                    t2.setText(actor.getString("category"));

                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Detailview.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mp=new HashMap<String, String>();
                mp.put("name",s1);
                return mp;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
        rq.add(sr);
    }
}
