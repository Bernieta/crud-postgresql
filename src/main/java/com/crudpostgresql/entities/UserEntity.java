package com.crudpostgresql.entities;

public class UserEntity {

    private int id;
    private String names;
    private int age;
    private String email;

    public UserEntity() {
    }

    public UserEntity(int id, String names, int age, String email) {
        this.id = id;
        this.names = names;
        this.age = age;
        this.email = email;
    }

    public UserEntity(String names, int age, String email) {
        this.names = names;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return this.names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "id = " + id + ", names = " + names + ", age = " + age + ", email = " + email;
    }
}
