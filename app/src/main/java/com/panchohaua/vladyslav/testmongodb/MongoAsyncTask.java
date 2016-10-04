package com.panchohaua.vladyslav.testmongodb;

import android.os.AsyncTask;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;

/**
 * Created by Vladyslav on 04.10.2016.
 */


public class MongoAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {

    private Exception exception;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> collection;
    private onDataLoaded mListener;

    public MongoAsyncTask(onDataLoaded mListener) {
        this.mListener = mListener;
    }

    protected ArrayList<String> doInBackground(Void... urls) {
        try {
            MongoClientURI connectionString = new MongoClientURI("mongodb://notnull:qwerty123456@ds021166.mlab.com:21166/datingdb");
            MongoClient mongoClient = new MongoClient(connectionString);
            this.mongoDatabase = mongoClient.getDatabase("datingdb");
            this.collection = mongoDatabase.getCollection("users");
            ArrayList<String> response = new ArrayList<>();
            MongoCursor<Document> cursor = collection.find().iterator();
            this.mongoClient.close();
            try {
                while (cursor.hasNext()) {
                    response.add(cursor.next().toJson());
                }
                return response;
            } finally {
                cursor.close();
            }
        } catch (Exception e) {
            this.exception = e;
            return null;
        }

    }

    protected void onPostExecute(ArrayList<String> response) {
        if (this.mListener != null) {
            this.mListener.onDataLoaded(response);
        }
    }
}