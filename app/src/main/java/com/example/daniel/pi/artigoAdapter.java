package com.example.daniel.pi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class artigoAdapter extends ArrayAdapter {
    Context mContex;
    int mRecurso;
    public artigoAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

        this.mContex = context;
        this.mRecurso = resource;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContex);
        convertView = inflater.inflate(mRecurso, parent, false);

        TextView titulo = convertView.findViewById(R.id.titulo);
        ImageView image = convertView.findViewById(R.id.imageView);
        Artigo artigo = (Artigo)getItem(position);
        titulo.setText(artigo.getId_artigo());

        return convertView;
    }
}
