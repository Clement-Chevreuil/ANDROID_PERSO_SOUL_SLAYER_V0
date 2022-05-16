package com.example.android_perso_soul_slayer_v0.A2_DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class A_DAOBase {

    protected final static int VERSION = 1;
    protected final static String NOM = "SOUL_SLAYER.db";

    protected SQLiteDatabase mDb = null;
    protected A_DatabaseHandler mHandler = null;

    public A_DAOBase(Context pContext) {
        this.mHandler = new A_DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
