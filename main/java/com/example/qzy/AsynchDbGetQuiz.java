package com.example.qzy;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

public class AsynchDbGetQuiz extends AsyncTask {
    QzyDb qzyDb;
    Context context;
    Cursor cursor;
    @Override
    protected Object doInBackground(Object[] objects) {
        Log.e("AsynchDbGetall","Asyncch DB Get Quiz started");

        context= (Context) objects[0];
        String qzName=(String) objects[1];
        String qzid=(String) objects[2];
        Log.e("AsynchDbGetall","Sleeping");
        //sleep(10000); //Exercise5 - Tested sleep, app waits for completion.

        //Content resolution
        String URL=QzyDataProvider.CONTENT_URI+"/quizTitle/"+qzid;
        Log.e("GetDeatails","Content Resolver for: "+URL);
        Uri qzuri=Uri.parse(URL);
        cursor=this.context.getContentResolver().query(qzuri, null, null, null, "name");
        Log.e("GetDeatails","Content Resolver for: "+URL);

        cursor.moveToFirst();
        Quiz quiz=new Quiz();
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

        //Content resolution

        //qzyDb=new QzyDb(context,qzName,null,1);
        //Quiz quiz=qzyDb.getQuiz(qzid);

        return quiz;
    }
}
