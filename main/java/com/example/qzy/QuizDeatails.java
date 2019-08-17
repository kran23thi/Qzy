package com.example.qzy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizDeatails extends AppCompatActivity {


    TextView QzDetails;
    QzyDb qzyDb;
    Context context;
    Quiz quiz;
    AsynchDbGetQuiz asynchDbGetQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("QuizDetails","Quiz details here");
        setContentView(R.layout.activity_quiz_deatails);
        String qzid=getIntent().getStringExtra("quizId");
        Log.e("QuizDetailsActivity","Quiz details here"+qzid);

        context=getApplicationContext();

        asynchDbGetQuiz=new AsynchDbGetQuiz();
        Object obj[]=new Object[3];
        obj[0]=context;
        obj[1]="Qzy";
        obj[2]=qzid;

        quiz=(Quiz)asynchDbGetQuiz.doInBackground(obj); //Exercise5 Assynch Task used for DB Ops
        //qzyDb=new QzyDb(c,"Qzy",null,1);

        //qzyDb=new QzyDb(context,"Qzy",null,1);

        //Quiz quiz=qzyDb.getQuiz(qzid);

        QzDetails=findViewById(R.id.QzDetails);
        QzDetails.append("\r\n");
        QzDetails.append(quiz.quizId);
        QzDetails.append("\r\n");
        QzDetails.append(quiz.Title);
        QzDetails.append("\r\n");
        QzDetails.append(quiz.Category);
    }
}
