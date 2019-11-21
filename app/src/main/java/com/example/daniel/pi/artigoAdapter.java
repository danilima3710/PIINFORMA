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

class ArtigoAdapter extends ArrayAdapter<Artigo> {
    Context mContex;
    int mRecurso;
    ArrayList<Artigo>  mDataSet;

    public ArtigoAdapter(@NonNull Context context, int resource, @NonNull List<Artigo> Artigo) {
        super(context, resource, Artigo);
        this.mContex = context;
        this.mRecurso = resource;

    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        //Inflar lyaout com item de recurso recebido no construtor
        LayoutInflater inflater = LayoutInflater.from(mContex);
        convertView = inflater.inflate(mRecurso, parent, false);

        //Associando as referências dos objetos instanciados na view com variávaeis locais.
        TextView textViewtitulo = convertView.findViewById(R.id.textViewTitulo);
        TextView textViewDescricao = convertView.findViewById(R.id.textViewDescricao);
       // ImageView image = convertView.findViewById(R.id.textViewDescricao);

        //Recuperando objeto com dados de artigo na posição soliciada pelo listview
        Artigo artigo = getItem(position);

        //Setando dados do objeto artigo no item de layout inflado em convertView
        textViewtitulo.setText(artigo.getTitulo());
        textViewDescricao.setText(artigo.getResumo());


        return convertView;
    }
}
