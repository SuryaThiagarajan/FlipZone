package com.example.surya.flipzone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class NewProduct extends AppCompatActivity {

String s1,s2,s3,s4,s5,profile;
EditText e1,e2,e3,e4;
ImageView i;
Button b1,b2;
Uri imageUri;
ProgressDialog pd;
Bitmap picture;
private static final int PICK_IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        e1=(EditText)findViewById(R.id.entername);
        e2=(EditText)findViewById(R.id.enterprice);
        e3=(EditText)findViewById(R.id.enterdesc);
        e4=(EditText)findViewById(R.id.editText);
        i=(ImageView)findViewById(R.id.productimage);
        b1=(Button)findViewById(R.id.chooseimage);
        b2=(Button)findViewById(R.id.save);
        s4=getIntent().getStringExtra("name");

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        pd=new ProgressDialog(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==1000 && data!=null)
        {
            imageUri=data.getData();
            try {
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                picture = BitmapFactory.decodeStream(imageStream);
                i.setImageBitmap(picture);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void save() {
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        s3=e3.getText().toString();
        s5=e4.getText().toString();
        convertBitmapToString(picture);
        pd.show();
        StringRequest sr=new StringRequest(Request.Method.POST, "https://vanakkam.000webhostapp.com/newproduct.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(NewProduct.this,response,Toast.LENGTH_LONG).show();
                Intent id=new Intent(NewProduct.this,UploadFinish.class);
                startActivity(id);
              pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewProduct.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mp=new HashMap<String,String>();
                mp.put("name",s1);
                mp.put("price",s2);
                mp.put("desc",s3);
                mp.put("adminname",s4);
                mp.put("category",s5);
                mp.put("image",profile);
                return mp;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
        rq.add(sr);
    }

    private void convertBitmapToString(Bitmap profilePicture) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        profilePicture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] array = byteArrayOutputStream.toByteArray();
        profile = Base64.encodeToString(array, Base64.DEFAULT);
    }
}
