package com.example.forca_integrado;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<RecyHolder> {

    private ArrayList<Palavra> lista;
    @NonNull
    @Override
    public RecyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new RecyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyHolder holder, int position) {
        //falha rsrs
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
