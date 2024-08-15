package com.sparta.schedulemanager.repository;

import com.sparta.schedulemanager.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Objects;

public class ScheduleRepository {
    private final String SAVE_QUERY = "INSERT INTO schedule (todo, managername, password, edittime, createtime) VALUES (?, ?, ?, ?, ?)";

    public void saveSchedule(JdbcTemplate jdbcTemplate, Schedule schedule) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con-> {
            PreparedStatement preparedStatement = con.prepareStatement(SAVE_QUERY,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, schedule.getTodo());
            preparedStatement.setString(2, schedule.getManagerName());
            preparedStatement.setString(3, schedule.getPassword());
            preparedStatement.setString(4, schedule.getEditTime());
            preparedStatement.setString(5, schedule.getCreateTime());

            return preparedStatement;
        }, keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        schedule.setScheduleId(id);
    }

    public Schedule getSchedule(JdbcTemplate jdbcTemplate, Integer id) {
        return null;
    }

    public Schedule getSchedules(JdbcTemplate jdbcTemplate, Date dateTime) {
        return null;
    }
}
