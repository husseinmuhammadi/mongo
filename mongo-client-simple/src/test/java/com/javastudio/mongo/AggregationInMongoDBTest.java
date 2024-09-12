package com.javastudio.mongo;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class AggregationInMongoDBTest {
    public static final String MONGODB_URI = "mongodb+srv://tolerant-silkworm:1bYnQgu6p7Xcuq8yw8Kgan@tst-rpms-pl-1.2mtsy.mongodb.net/?retryWrites=true&w=majority";
    public static final String MONGODB_NAME = "rpms-tst";

    private MongoClient mongoClient;

    @BeforeEach
    void setUp() {
        mongoClient = new MongoClient(new MongoClientURI(MONGODB_URI));
    }

    @AfterEach
    void tearDown() {
        mongoClient.close();
    }

    @Test
    void filter() throws Exception {
        DBCursor cursor = mongoClient.getDB(MONGODB_NAME).getCollection("zone-retail-prices").find(
                readResourceAsMongodbObject("query/rpms/zone-retail-prices-filter.json")
        );

        DBObject one = cursor.one();
        int count = cursor.count();

        System.out.println("-".repeat(80));
        System.out.println(one);
        System.out.println(count);
        System.out.println("-".repeat(80));
    }

    @Test
    void lookup() throws Exception {
        DBCursor cursor = mongoClient.getDB(MONGODB_NAME).getCollection("zone-retail-prices").find(
                readResourceAsMongodbObject("query/rpms/zone-retail-prices-filter.json")
        );


    }

    private DBObject readResourceAsMongodbObject(final String resourceName) throws URISyntaxException, IOException {
        Object o = JSON.parse(readJsonResource(resourceName, StandardCharsets.UTF_8));
        return (DBObject) o;
    }

    private String readJsonResource(final String resourceName, final Charset cs) throws URISyntaxException, IOException {
        return Files.readString(
                Paths.get(this.getClass().getClassLoader().getResource(resourceName).toURI()),
                cs
        );
    }
}