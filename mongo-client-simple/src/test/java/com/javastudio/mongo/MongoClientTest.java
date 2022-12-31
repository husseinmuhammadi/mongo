package com.javastudio.mongo;

import com.mongodb.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

public class MongoClientTest {

    public static final String MONGODB_NAME = "db01";
    public static final String COLLECTION_PEOPLE = "people";
    public static final String COLLECTION_PRODUCT = "product";

    private static MongoClient mongoClient;

    @BeforeClass
    public static void beforeClass() throws Exception {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    }

    @Test
    public void whenInsertPersonToNewDatabaseAndNewCollection_thenItWillCreateDatabaseAndCollection() {
        DBObject person = new BasicDBObject("name", "Hossein Mohammadi")
                .append("address", new BasicDBObject("street", "123 Fake St")
                        .append("city", "Faketon")
                        .append("state", "MA")
                        .append("zip", 12345)
                ).append("age", 42);
        mongoClient.getDB(MONGODB_NAME).getCollection(COLLECTION_PEOPLE).insert(person);
    }

    @Test
    public void x() throws UnknownHostException {
        DB database = mongoClient.getDB(MONGODB_NAME);
        DBCollection collection = database.getCollection(COLLECTION_PRODUCT);
        DBCursor cursor = collection.find();
        DBObject object = cursor.one();
    }
}
