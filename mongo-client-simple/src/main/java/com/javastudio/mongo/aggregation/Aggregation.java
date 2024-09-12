package com.javastudio.mongo.aggregation;

import com.mongodb.MongoClient;

public class Aggregation {

    private final MongoClient mongoClient;

    public Aggregation(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    void project(String databaseName){
//        mongoClient.getDatabase(databaseName).aggregate();
    }

}
