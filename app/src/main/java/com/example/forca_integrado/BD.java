package com.example.forca_integrado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "banco.db";


    public BD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS tabelaPalavra (" +
                        "_id INTERGER PRIMARY KEY AUTOINCREMENT," +
                        "palavra TEXT, " +
                        "categoria TEXT)"
        );

    }

    public void salvarPalavra(Palavra p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("categoria", p.getCategoria());
        valores.put("palavra", p.getPalavraDigitada());
        db.insert("tabelaPalavra", null, valores);
        db.close();
    }

    public ArrayList<Palavra> listar_palavras() {
        ArrayList<Palavra> lista = new ArrayList<Palavra>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("tabelaPalavra", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String palavra = cursor.getString(cursor.getColumnIndexOrThrow("palavra"));
            String categoria = cursor.getString(cursor.getColumnIndexOrThrow("categoria"));
            Palavra p = new Palavra();
            p.setPalavraDigitada(palavra);
            p.setCategoria(categoria);
            lista.add(p);

        }
        cursor.close();
        db.close();
        return lista;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

