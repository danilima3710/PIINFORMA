package com.example.daniel.pi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ArtigoDAO {
    SQLiteDatabase sqLiteDatabase;

    public ArtigoDAO(Context c) {
        sqLiteDatabase = c.openOrCreateDatabase("informa", c.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("Create table if not exists artigo (id_artigo int primary key AUTOINCREMENT, titulo varchar, descricao varchar)");
    }

    public void inserir (Artigo artigo){
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", artigo.getTitulo());
        contentValues.put("descricao", artigo.getDescricao());
        sqLiteDatabase.insert("artigo", null, contentValues);


    }

    public void atualizar(Artigo artigo){
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", artigo.getTitulo());
        contentValues.put("descricao", artigo.getDescricao());
        sqLiteDatabase.update("artigo", contentValues, null, null) ;

    }
//    public ArrayList<Artigo> listarArtigo() {
//       ArrayList<Artigo> arrayListResult = new ArrayList<>();
//       Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM artigo",null);
//       cursor.moveToFirst();
//
//       while ( !cursor.isAfterLast()){
//           arrayListResult.add(new Artigo(cursor.getInt(cursor.getColumnIndex("id_artigo")),
//                                        cursor.getString(cursor.getColumnIndex("titulo")),
//                                        cursor.getString(cursor.getColumnIndex("descricao"))
//                   ));
//           cursor.moveToNext();
//       }
//       return arrayListResult;
//    }

//    public ArrayList<Artigo> listarPorTitulo(){
//        ArrayList<Artigo> arrayListResult = new ArrayList<>();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM artigo where titulo = ",null);
//        cursor.moveToFirst();
//
//        while ( !cursor.isAfterLast()){
//            arrayListResult.add(new Artigo(cursor.getInt(cursor.getColumnIndex("id_artigo")),
//                    cursor.getString(cursor.getColumnIndex("titulo")),
//                    cursor.getString(cursor.getColumnIndex("descricao"))
//            ));
//            cursor.moveToNext();
//        }
//        return arrayListResult;
//    }
    }

