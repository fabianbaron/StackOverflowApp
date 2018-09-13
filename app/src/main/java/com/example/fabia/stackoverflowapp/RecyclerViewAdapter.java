package com.example.fabia.stackoverflowapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<Answers> mDatos;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(android.R.id.text1)
        TextView myTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public RecyclerViewAdapter(List<Answers> datos) {
        this.mDatos = datos;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView;
        myView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_selectable_list_item,parent,false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Answers myAnswer = (Answers) mDatos.get(position);
        holder.myTextView.setText(myAnswer.toString());
        holder.itemView.setTag(myAnswer.answerId);
    }

    @Override
    public int getItemCount() {
        return mDatos.size();
    }

}
