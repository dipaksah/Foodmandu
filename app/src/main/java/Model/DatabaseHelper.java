package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String fname="firstname";
    public static final String lname="lastname";
    public static final String address="address";
    public static final String contact="contact";
    public static final String email="email";
    public static final String password="password";
    public static final String TABLE_NAME="user";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE user(firstname text,lastname text,address text,contact text,email text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists user");
    }


    public boolean insert(String firstname,String lastname,String address, String contact,String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("firstname",firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("address",address);
        contentValues.put("contact",contact);
        contentValues.put("email",email);
        contentValues.put("password",password);

        long ins=db.insert("user",null,contentValues);
        if (ins==-1) return false;
        else return true;
    }

    public boolean CheckAlreadyEmail(String email){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM user WHERE email=?",new String[]{email});
        if (cursor.getCount()>0){
            return false;
        }else return true;
    }
}
