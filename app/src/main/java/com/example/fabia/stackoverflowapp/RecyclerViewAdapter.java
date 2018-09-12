package com.example.fabia.stackoverflowapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Answers> mDatos;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView myTextViewPuntaje;
        TextView myTextViewAceptada;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextViewPuntaje = (TextView) itemView.findViewById(R.id.textPuntaje);
            myTextViewAceptada = (TextView) itemView.findViewById(R.id.textAceptada);
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
                .inflate(R.layout.row_recycler_layout, parent,false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Answers myAnswer = (Answers) mDatos.get(position);

        TextView myTextView1 = holder.myTextViewAceptada;
        TextView myTextView2 = holder.myTextViewPuntaje;

        myTextView1.setText(myAnswer.getIsAccepted());
        myTextView2.setText(myAnswer.score);
        holder.itemView.setTag(myAnswer.answerId);
    }

    @Override
    public int getItemCount() {
        return mDatos.size();
    }

}
