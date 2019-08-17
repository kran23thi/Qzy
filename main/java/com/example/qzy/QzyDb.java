package com.example.qzy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class QzyDb extends SQLiteOpenHelper {//Exercise5

    private static final String DBNAME="Qzy.db";
    private static final int DBVERSION=1;

    private static final String TABLE_quiz="Quiz";
    private static final String CREATE_TABLE_Quiz="CREATE TABLE "+TABLE_quiz+"(quizId TEXT, Title TEXT, Category TEXT,postedDate TEXT)";


    public QzyDb(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.e("qzyDb","new quiz being creted context"+context.toString());
        Log.e("qzyDb","Quiz DB name:"+name);
    }

    /*
        public QzyDb(Recycler_Quizes_Adapter context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DBNAME, factory, DBVERSION);
        }
    */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_Quiz);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    boolean insertIntoQuizDb(String quizId,String Title,String Category,String postedDate)
    {
        ContentValues values=new ContentValues();
        values.put("quizId",quizId);
        values.put("Title",Title);
        values.put("Category",Category);
        values.put("postedDate",postedDate);
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        Log.e("qzyDb Insertion","Quiz Dab name"+sqLiteDatabase.getPath().toString());
        Log.e("qzyDb","Quiz Dab name"+sqLiteDatabase.getPath().toString());
        long rowId=sqLiteDatabase.insert(TABLE_quiz,null,values);
        sqLiteDatabase.close();
        return rowId>0;
    }

    /*
    boolean updatePeson(int pid,String pname)
    {
        ContentValues values=new ContentValues();
        values.put("pid",pid);
        values.put("pname",pname);
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String whereClause="pid = ?";
        String[] whereArgs=new String[]{String.valueOf(pid)};

        long rowId=sqLiteDatabase.update(TABLE_PERSON,values, whereClause, whereArgs);
        sqLiteDatabase.close();
        return rowId>0;
    } */

    boolean deleteQuiz(String quizId,String Title)
    {
        ContentValues values=new ContentValues();
        values.put("quizId",quizId);
        values.put("Title",Title);
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String whereClause="quizId = ?";
        String[] whereArgs=new String[]{String.valueOf(quizId)};

        long rowId=sqLiteDatabase.delete(TABLE_quiz,whereClause,whereArgs);
        sqLiteDatabase.close();
        return rowId>0;
    }

    Quiz getQuiz(String quizId)
    {
        Quiz quiz=new Quiz();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Log.e("qzyDb get","Quiz Dab name"+sqLiteDatabase.getPath().toString());
        String qry="SELECT * FROM "+TABLE_quiz+" WHERE Title"+"="+'"'+quizId+'"';
        Log.e("quiz get","Query:"+qry);
        Cursor cursor=sqLiteDatabase.rawQuery(qry, null);
        cursor.moveToFirst();
        if(cursor.isFirst())
        {
            while(!cursor.isAfterLast())
            {
                quiz.quizId=cursor.getString(cursor.getColumnIndex("quizId"));
                Log.e("GetQuiz","Quiz Id"+quiz.quizId);
                quiz.Title=cursor.getString(cursor.getColumnIndex("Title"));
                quiz.Category=cursor.getString(cursor.getColumnIndex("Category"));
                //  quiz.tags=cursor.getString(cursor.getColumnIndex("tags"));
                quiz.postedDate=cursor.getString(cursor.getColumnIndex("postedDate"));
                cursor.moveToNext();
            }

        }
        cursor.close();
        sqLiteDatabase.close();
        return quiz;
    }


    ArrayList<Quiz> getAllQuiz()
    {
        ArrayList<Quiz> Quizes=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Log.e("qzyDb get all","Quiz Dab name"+sqLiteDatabase.getPath().toString());
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_quiz,null);
        cursor.moveToFirst();
        if(cursor.isFirst())
        {
            while(!cursor.isAfterLast())
            {
                Quiz quiz=new Quiz();
                quiz.quizId=cursor.getString(cursor.getColumnIndex("quizId"));
                quiz.Title=cursor.getString(cursor.getColumnIndex("Title"));
                quiz.Category=cursor.getString(cursor.getColumnIndex("Category"));
              //  quiz.tags=cursor.getString(cursor.getColumnIndex("tags"));
                quiz.postedDate=cursor.getString(cursor.getColumnIndex("postedDate"));
                Quizes.add(quiz);
                cursor.moveToNext();

            }

        }
        cursor.close();
        sqLiteDatabase.close();
        return Quizes;
    }
}
