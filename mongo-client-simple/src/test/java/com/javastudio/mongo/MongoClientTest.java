package com.javastudio.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.Doc;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MongoClientTest {

    public static final String MONGODB_URI = "mongodb://localhost:27017";
    public static final String MONGODB_NAME = "db01";
    public static final String COLLECTION_PEOPLE = "people";
    public static final String COLLECTION_PRODUCT = "product";

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
    void whenInsertPersonToNewDatabaseAndNewCollection_thenItWillCreateDatabaseAndCollection() throws Exception {
        mongoClient.getDB(MONGODB_NAME).getCollection(COLLECTION_PEOPLE).insert(
                readResourceAsMongodbObject("data/person.json"));
    }

    private void deleteComputersFromMongodb() {
        MongoDatabase database = mongoClient.getDatabase(MONGODB_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_PRODUCT);
        Bson query = eq("productName", "Computer");
        collection.deleteMany(query);
    }

    private void deleteLaptopsFromMongodb() {
        MongoDatabase database = mongoClient.getDatabase(MONGODB_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_PRODUCT);
        Bson query = eq("productName", "Laptop");
        collection.deleteMany(query);
    }

    @Test
    void shouldFindTheProduct_whenSearchByGivenQuery() throws Exception {

        deleteComputersFromMongodb();
        deleteLaptopsFromMongodb();

        final DB mongoDb = mongoClient.getDB(MONGODB_NAME);
        final DBCollection collection = mongoDb.getCollection(COLLECTION_PRODUCT);

        collection.insert(readResourceAsMongodbList("data/products.json"));

        DBCursor cursor = collection.find(readResourceAsMongodbObject("query/product-laptop.json"));
        DBObject object = cursor.one();

        assertEquals("Laptop", object.get("productName"));

        assertEquals(
                1,
                collection.find(readResourceAsMongodbObject("query/product-computer.json")).count()
        );

        assertEquals(
                2,
                collection.find(readResourceAsMongodbObject("query/product-type-computer-and-devices.json")).count()
        );
    }

    private BasicDBObject[] readResourceAsMongodbList(final String resourceName) throws URISyntaxException, IOException {
        Object o = JSON.parse(readJsonResource(resourceName, StandardCharsets.UTF_8));
        return ((BasicDBList) o).toArray(new BasicDBObject[0]);
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
