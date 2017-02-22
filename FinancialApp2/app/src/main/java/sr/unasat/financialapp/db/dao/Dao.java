package sr.unasat.financialapp.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static sr.unasat.financialapp.db.schema.Schema.DATABASE_NAME;
import static sr.unasat.financialapp.db.schema.Schema.DATABASE_VERSION;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCategory.CREATE_CATTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCurrency.CREATE_CURTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaReport.CREATE_REPTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaTransaction.CREATE_TRANTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.CREATE_USERTABLE;

public class Dao extends SQLiteOpenHelper{

    public Dao(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USERTABLE);
        db.execSQL(CREATE_TRANTABLE);
        db.execSQL(CREATE_CATTABLE);
        db.execSQL(CREATE_REPTABLE);
        db.execSQL(CREATE_CURTABLE);

        Log.i(TAG, "onCreate: succesfull");
        // setDefaultCategories();
        // setDefaultCurrencies();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void setDefaultCategories() {


    }

    private void setDefaultCurrencies(){


    }
    //methods to use db


}
