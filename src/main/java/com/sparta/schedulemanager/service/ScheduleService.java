package com.sparta.schedulemanager.service;

import com.sparta.schedulemanager.entity.Schedule;
import com.sparta.schedulemanager.repository.ScheduleRepository;
import com.sparta.schedulemanager.utility.ProjectProtocol;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 스케쥴 저장
    public void addSchedule(JdbcTemplate jdbcTemplate, Schedule schedule) throws IllegalAccessException {
        scheduleRepository.saveData(jdbcTemplate, ProjectProtocol.TABLE_SCHEDULE, schedule);
    }

    // ID 기준으로 스케쥴 조회
    public Schedule getScheduleById(JdbcTemplate jdbcTemplate, int id) {
        return scheduleRepository.getScheduleById(jdbcTemplate, ProjectProtocol.TABLE_SCHEDULE, id);
    }

    // 특정 일자 기준으로 스케쥴 조회
    public List<Schedule> getSchedulesByDate(JdbcTemplate jdbcTemplate, String date) {
        return scheduleRepository.getSchedulesByDate(jdbcTemplate, ProjectProtocol.TABLE_SCHEDULE, date);
    }
}
