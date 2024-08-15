package com.sparta.schedulemanager.repository;

import com.sparta.schedulemanager.entity.CustomEntity;
import com.sparta.schedulemanager.entity.Schedule;
import com.sparta.schedulemanager.utility.ProjectProtocol;
import com.sparta.schedulemanager.utility.QueryUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

public class ScheduleRepository<T extends CustomEntity> {
    private QueryUtility queryUtility;

    public ScheduleRepository() {
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

    // Schedule ID에 대한 정보를 조회하는 함수
    public Schedule getScheduleById(JdbcTemplate jdbcTemplate, String table, Integer id) {
        return findScheduleById(jdbcTemplate, table, id);
    }

    // ID정보를 토대로 DataBase 조회하는 함수
    public Schedule findScheduleById(JdbcTemplate jdbcTemplate, String table, Integer id) {
        String getQuery = queryUtility.getGetByColumnDataQuery(table, ProjectProtocol.TABLE_COLUMN_ID, String.valueOf(id));

        return jdbcTemplate.query(getQuery, resultSet-> {
            if(resultSet.next()) {
                return new Schedule(resultSet);
            } else {
                return null;
            }
        });
    }

    // 특정 일자를 기준으로 DataBase를 조회하는 함수
    public List<Schedule> getSchedulesByDate(JdbcTemplate jdbcTemplate, String table, String date) {
        // 수정 일자를 기준으로 조회
        String getSchedulesQuery = queryUtility.getGetByColumnDateQuery(table, ProjectProtocol.TABLE_COLUMN_EDITTIME, date);

        return jdbcTemplate.query(getSchedulesQuery, (rs, rowNum) -> new Schedule(rs));
    }

}
