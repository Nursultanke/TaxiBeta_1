package com.example.nursultan.taxibeta;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Data> mDataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView age;
        ImageView img;


        public MyViewHolder(View v) {
            super(v);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            img= itemView.findViewById(R.id.img);
        }

    }

    public MyAdapter(List<Data> myDataSet){
        mDataSet = myDataSet;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int i) {

        Data data = mDataSet.get(i);
        myViewHolder.name.setText(data.getName());
        myViewHolder.age.setText(data.getAge());
        myViewHolder.img.setImageURI(data.getImg());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
