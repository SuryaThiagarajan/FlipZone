package com.example.surya.flipzone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserWelcome extends AppCompatActivity {
    String s1;
    TextView t;
    List<Pojo> imagesList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);
        imagesList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyle);
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        s1=getIntent().getStringExtra("key");
        t=(TextView)findViewById(R.id.textView2);
        t.setText("Hello "+s1);
        name();

    }

    public void name() {
        StringRequest sr=new StringRequest(Request.Method.POST, "https://vanakkam.000webhostapp.com/userdisplay.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray project_details = jsonObject.getJSONArray("project_details");
                            for (int i = 0; i < project_details.length(); i++) {
                                Pojo images = new Pojo();
                                JSONObject actor = project_details.getJSONObject(i);
                                images.setS1(actor.getString("name"));
                                images.setS2(actor.getString("price"));
                                images.setS3(actor.getString("image"));
                                imagesList.add(images);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        recyclerViewadapter = new Adapter(imagesList, getApplicationContext());
                        recyclerView.setAdapter(recyclerViewadapter);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(sr);
    }

}

