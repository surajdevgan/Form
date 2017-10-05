package com.example.suraj.form;

import java.io.Serializable;

/**
 * Created by suraj on 16-09-2017.
 */

public class Bean implements Serializable{
    int id;
    String name, age;

    public Bean() {

    }

    public Bean(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Bean(int id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

