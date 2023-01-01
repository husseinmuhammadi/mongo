package com.javastudio.mongo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MongoClientTest {

    private Logger logger = LoggerFactory.getLogger(MongoClientTest.class);

    @Test
    void x() {
        Person person = new Person();
        person.setName("Hossein1");
        MongoDatastore.INSTANCE.get().save(person);
    }
}
