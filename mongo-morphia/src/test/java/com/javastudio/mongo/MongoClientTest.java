package com.javastudio.mongo;

import org.junit.jupiter.api.Test;

class MongoClientTest {

    @Test
    void x() {
        Person person = new Person();
        person.setName("Hossein1");
        MongoDatastore.INSTANCE.get().save(person);
    }
}
