package com.javastudio.mongo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoClientTest {

    private Logger logger = LoggerFactory.getLogger(MongoClientTest.class);

    @Test
    public void x() {
        Person person=new Person();
        person.setName("Hossein1");
        MongoDatastore.INSTANCE.get().save(person);
    }
}
