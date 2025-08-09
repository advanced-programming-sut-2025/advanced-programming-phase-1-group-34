package org.Group34.model;

import java.io.Serializable;

public class TestObject implements Serializable {
    public String name;
    public int number;

    public TestObject(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
