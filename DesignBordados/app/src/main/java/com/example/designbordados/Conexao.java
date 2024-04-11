package com.example.designbordados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {


    private static final String NAME ="banco.db";
    private static final int VERSION = 1;

    private static final String SQL_CREATE;

    static {
        SQL_CREATE = "create table cliente ( " +
                " id integer primary key autoincrement, " +
                " nome varchar(50), " +
                " fone varchar(20), " +
                " cpf varchar(50), " +
                " email varchar(50));";
    }

    public Conexao(@Nullable Context context){
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
