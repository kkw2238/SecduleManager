package com.sparta.schedulemanager.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomEntity {
    protected int id;

    public CustomEntity() {}
    public CustomEntity(ResultSet rs) {}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}