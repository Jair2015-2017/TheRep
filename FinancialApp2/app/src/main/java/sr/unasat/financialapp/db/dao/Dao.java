package sr.unasat.financialapp.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.financialapp.dto.Category;
import sr.unasat.financialapp.dto.Currency;
import sr.unasat.financialapp.dto.User;

import static android.content.ContentValues.TAG;
import static sr.unasat.financialapp.db.schema.Schema.DATABASE_NAME;
import static sr.unasat.financialapp.db.schema.Schema.DATABASE_VERSION;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCategory.BUDGET;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCategory.CAT_DESCR;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCategory.CAT_ID;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCategory.CAT_NAME;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCategory.CAT_TABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCategory.CREATE_CATTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCurrency.COUNTRY;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCurrency.CREATE_CURTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCurrency.CURRENCY;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCurrency.CURTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCurrency.CUR_ID;
import static sr.unasat.financialapp.db.schema.Schema.SchemaCurrency.CUR_LOGO;
import static sr.unasat.financialapp.db.schema.Schema.SchemaReport.CREATE_REPTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaTransaction.CREATE_TRANTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaTransaction.TRAN_TABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.CLOSING;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.CREATED;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.CREATE_USERTABLE;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.EMAIL;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.NAME_USER;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.OPENING;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.PASSWORD;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.SURNAME;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.TRANSACTIONS;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.USER_ID;
import static sr.unasat.financialapp.db.schema.Schema.SchemaUser.USER_TABLE;

public class Dao extends SQLiteOpenHelper {

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

    //methods to use db

    public Currency getCurencyByID(int id){
        Currency currency = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor=null;

        cursor = db.query(CURTABLE, null,
                CUR_ID+" = ?", new String[] { "" + id },null,null,null);
        if (cursor.moveToFirst()){
            int curID = cursor.getInt(cursor.getColumnIndex(CUR_ID));
            String country = cursor.getString(cursor.getColumnIndex(COUNTRY));
            String nameCurrency = cursor.getString(cursor.getColumnIndex(CURRENCY));
            String logo = cursor.getString(cursor.getColumnIndex(CUR_LOGO));

            currency = new Currency(curID,country,nameCurrency,logo);

        }

        return currency;
    }

    public User getUserById(int id ){
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        cursor = db.query(USER_TABLE, null,
                USER_ID+" = ?", new String[] { "" + id },null,null,null);
        if (cursor.moveToFirst()){
            int userID = cursor.getInt(cursor.getColumnIndex(USER_ID));
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String surname = cursor.getString(cursor.getColumnIndex(SURNAME));
            String name = cursor.getString(cursor.getColumnIndex(NAME_USER));
            String created = cursor.getString(cursor.getColumnIndex(CREATED));
            double opening = cursor.getDouble(cursor.getColumnIndex(OPENING));
            double transactions = cursor.getDouble(cursor.getColumnIndex(TRANSACTIONS));
            double closing = cursor.getDouble(cursor.getColumnIndex(CLOSING));
            int currencyID = cursor.getInt(cursor.getColumnIndex(CUR_ID));

            user = new User(userID, password, email, created, opening, transactions, closing, null,null,null,null,null);


        }

        return user;
    }

    public Category getCategoryById( int id ){
        Category category = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        cursor = db.query(CAT_TABLE, null,
                CAT_ID+" = ?", new String[] { "" + id },null,null,null);
        if (cursor.moveToFirst()) {

            int cat_id = cursor.getInt(cursor.getColumnIndex(CAT_ID));
            String name = cursor.getString(cursor.getColumnIndex(CAT_NAME));
            String description= cursor.getString(cursor.getColumnIndex(CAT_DESCR));
            double budget = cursor.getDouble(cursor.getColumnIndex(BUDGET));
            int userID =       cursor.getInt(cursor.getColumnIndex(USER_ID));

            category = new Category(cat_id,name,description,budget,getUserById(userID));

        }
        return category;
    }

    public Category getCategoryByName( String name ){
        Category category = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        cursor = db.query(CAT_TABLE, null,
                CAT_NAME+" = ?", new String[] { "" + name },null,null,null);
        if (cursor.moveToFirst()) {

            int cat_id = cursor.getInt(cursor.getColumnIndex(CAT_ID));
            String cat_name = cursor.getString(cursor.getColumnIndex(CAT_NAME));
            String description= cursor.getString(cursor.getColumnIndex(CAT_DESCR));
            double budget = cursor.getDouble(cursor.getColumnIndex(BUDGET));
            int userID =       cursor.getInt(cursor.getColumnIndex(USER_ID));

            category = new Category(cat_id,cat_name,description,budget,getUserById(userID));

        }
        return category;
    }

    public List<Category> getCategories(){
        List<Category> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Category category;

        Cursor  cursor = db.query(CAT_TABLE,null,null,null,null,null,null);

        if (cursor .moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(CAT_ID));
                category = getCategoryById(id);

                cursor.moveToNext();

                list.add(category);

            }while (cursor.isAfterLast() == false);

        }

        return list;

    }


    public boolean insertTransaction(ContentValues contentValues){

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TRAN_TABLE, null, contentValues)>0;
    }
}