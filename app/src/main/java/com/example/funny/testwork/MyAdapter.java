package com.example.funny.testwork;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private Context context;
    private ArrayList<Model> modelList;

    public MyAdapter(ArrayList<Model> list) {
        this.modelList = list;
    }


    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyHolder holder, int position) {


        Model model = modelList.get(position);
        holder.login.setText(model.getLogin());
        Picasso.get().load(model.getUrl_avatar())
                .into(holder.avatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        protected TextView login;
        protected ImageView avatar;


        public MyHolder(View itemView) {
            super(itemView);
            login = (TextView) itemView.findViewById(R.id._loginI);
            avatar = (ImageView) itemView.findViewById(R.id._avatarI);
        }
    }
}
