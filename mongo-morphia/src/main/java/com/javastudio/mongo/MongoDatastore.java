package com.javastudio.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public enum MongoDatastore {
    INSTANCE;

    private final Logger logger = LoggerFactory.getLogger(MongoDatastore.class);

    private final int timeout = 100;

    private Datastore datastore;

    public synchronized Datastore get() {
        try {
            if (datastore == null) {
                List<ServerAddress> serverAddresses = Collections.singletonList(new ServerAddress("localhost", 27017));
                MongoClientOptions options = MongoClientOptions.builder().connectTimeout(timeout).build();
                datastore = new Morphia().map(Person.class).createDatastore(new MongoClient(serverAddresses, options), "db01");
                Objects.requireNonNull(datastore);
                datastore.ensureCaps();
                datastore.ensureIndexes();
                return datastore;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        throw new RuntimeException("Error getting datastore");
    }
}
