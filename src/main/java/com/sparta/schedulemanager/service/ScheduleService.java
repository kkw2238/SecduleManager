package com.sparta.schedulemanager.service;

import com.sparta.schedulemanager.entity.Schedule;
import com.sparta.schedulemanager.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;


public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 스케쥴 저장
    public void addSchedule(JdbcTemplate jdbcTemplate, Schedule schedule) {
        scheduleRepository.saveSchedule(jdbcTemplate, schedule);
    }
}
