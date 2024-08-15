package com.sparta.schedulemanager.entity;

// Entity들은 공통적으로 고유번호를 갖고 있기에 별도로 분리
public class CustomEntity {
    protected int id;

    public CustomEntity() {}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}