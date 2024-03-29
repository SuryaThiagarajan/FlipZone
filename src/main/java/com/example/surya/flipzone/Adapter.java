package com.example.surya.flipzone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    List<Pojo> images;
    Context context;
    String s1;

    public Adapter(List<Pojo> getDataAdapter, Context context) {
        super();
        this.images=getDataAdapter;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Pojo getDataAdapter1=images.get(position);
        holder.t1.setText(getDataAdapter1.getS1());
        s1=getDataAdapter1.getS2();
        holder.t2.setText("₹ "+s1);
        Picasso.get().load(getDataAdapter1.getS3()).into(holder.i1);
        holder.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(context.getApplicationContext(),Detailview.class);
                in.putExtra("key",getDataAdapter1.getS1());
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView t1,t2;
        public ImageView i1;
        public CardView c;
        public ViewHolder(View itemView) {

            super(itemView);

            t1=(TextView)itemView.findViewById(R.id.textView100);
            t2=(TextView)itemView.findViewById(R.id.textView101);
            i1=(ImageView)itemView.findViewById(R.id.imageView2);
            c=(CardView)itemView.findViewById(R.id.card);
        }

    }

}
