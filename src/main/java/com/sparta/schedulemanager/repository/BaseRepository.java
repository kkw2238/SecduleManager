package com.sparta.schedulemanager.repository;

import com.sparta.schedulemanager.entity.CustomEntity;
import com.sparta.schedulemanager.utility.ProjectProtocol;
import com.sparta.schedulemanager.utility.QueryUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.Objects;

public class BaseRepository <T extends CustomEntity> {
    protected QueryUtility queryUtility;

    public BaseRepository() {
        queryUtility = new QueryUtility();
    }

    // 데이터를 저장하는 메서드
    public void saveData(JdbcTemplate jdbcTemplate, String table, T data) throws IllegalAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String saveQuery = queryUtility.getInsertQuery(table, data, ProjectProtocol.TABLE_COLUMN_ID);

        jdbcTemplate.update(con-> con.prepareStatement(saveQuery, PreparedStatement.RETURN_GENERATED_KEYS), keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        data.setId(id);
    }

    // column의 값이 data와 일치하는 데이터를 삭제하는 함수
    public String deleteData(JdbcTemplate jdbcTemplate, String table, String columnName, String data) {
        String deleteScheduleQuery = queryUtility.getDeleteByColumnDataQuery(table, columnName, String.valueOf(data));
        jdbcTemplate.update(deleteScheduleQuery);

        return data;
    }
}
