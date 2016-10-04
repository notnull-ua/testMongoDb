package com.panchohaua.vladyslav.testmongodb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;
import com.mongodb.MongoClientURI;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;


import org.bson.Document;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements onDataLoaded {


    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new MongoAsyncTask(this).execute();
        progressBar.setVisibility(View.VISIBLE);

    }

    public void onDataLoaded(ArrayList<String> response){

        progressBar.setVisibility(View.GONE);

        if(response == null) {
            Toast toast = Toast.makeText(this,"Щось пішло не так!",Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            for (String json:
                 response) {
                textView.append(json);
            }
        }
    }


}


