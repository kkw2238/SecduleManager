package com.sparta.schedulemanager.repository;

import com.sparta.schedulemanager.entity.CustomEntity;
import com.sparta.schedulemanager.entity.Schedule;
import com.sparta.schedulemanager.utility.QueryUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Objects;

public class ScheduleRepository<T extends CustomEntity> {
    private QueryUtility queryUtility;

    public ScheduleRepository() {
        queryUtility = new QueryUtility();
    }

    // 데이터를 저장하는 메서드
    public void saveData(JdbcTemplate jdbcTemplate, String table, T data) throws IllegalAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String saveQuery = queryUtility.getInsertQuery(table, data, "id");

        jdbcTemplate.update(con-> con.prepareStatement(saveQuery, PreparedStatement.RETURN_GENERATED_KEYS), keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        data.setId(id);
    }

    // Schedule ID에 대한 정보를 조회하는 함수
    public Schedule getScheduleById(JdbcTemplate jdbcTemplate, String table, Integer id) {
        return findScheduleById(jdbcTemplate, table, id);
    }

    // ID정보를 토대로 DataBase 조회하는 함수
    public Schedule findScheduleById(JdbcTemplate jdbcTemplate, String table, Integer id) {
        String getQuery = queryUtility.getGetByColumnDataQuery(table, "id", String.valueOf(id));

        return jdbcTemplate.query(getQuery, resultSet-> {
            if(resultSet.next()) {
                return new Schedule(resultSet);
            } else {
                return null;
            }
        });
    }

    public Schedule getSchedules(JdbcTemplate jdbcTemplate, Date dateTime) {
        return null;
    }
}
