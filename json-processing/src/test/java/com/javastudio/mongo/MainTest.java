package com.javastudio.mongo;

import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void readPerson() throws Exception {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("person.json")) {
            JsonParser parser = Json.createParser(new InputStreamReader(in));

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY:
                    case END_ARRAY:
                    case START_OBJECT:
                    case END_OBJECT:
                    case VALUE_FALSE:
                    case VALUE_NULL:
                    case VALUE_TRUE:
                        System.out.println(event.toString());
                        break;
                    case KEY_NAME:
                        System.out.print(event.toString() + " " +
                                parser.getString() + " - ");
                        break;
                    case VALUE_STRING:
                    case VALUE_NUMBER:
                        System.out.println(event.toString() + " " +
                                parser.getString());
                        break;
                }
            }
        }
    }
}