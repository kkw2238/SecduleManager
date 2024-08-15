package com.sparta.schedulemanager.service;

import com.sparta.schedulemanager.entity.Schedule;
import com.sparta.schedulemanager.exception.CustomException;
import com.sparta.schedulemanager.repository.ScheduleRepository;
import com.sparta.schedulemanager.exception.ProjectErrorCode;
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
    public Schedule getScheduleById(JdbcTemplate jdbcTemplate, Integer scheduleId) {
        Schedule foundSchedule = scheduleRepository.getScheduleById(jdbcTemplate, scheduleId);

        if(foundSchedule == null) {
            throw new CustomException(ProjectErrorCode.ERROR_NOT_EXIST);
        }

        return foundSchedule;
    }

    // 특정 일자 기준으로 스케쥴 조회
    public List<Schedule> getSchedulesByDate(JdbcTemplate jdbcTemplate, String date) {
        return scheduleRepository.getSchedulesByDate(jdbcTemplate, date);
    }

    // 스케쥴 수정하는 함수
    public Schedule editSchedule(JdbcTemplate jdbcTemplate, Integer scheduleId, Schedule schedule) throws SecurityException, IllegalAccessException, NoSuchFieldException {
        Schedule foundSchedule = scheduleRepository.getScheduleById(jdbcTemplate, scheduleId);

        if(foundSchedule == null) {
            throw new CustomException(ProjectErrorCode.ERROR_NOT_EXIST);
        }
        else if (!foundSchedule.getPassword().equals(schedule.getPassword())) {
            throw new CustomException(ProjectErrorCode.ERROE_WRONG_PASSWORD);
        }

        foundSchedule.editSchedule(schedule);
        return scheduleRepository.editScheduleData(jdbcTemplate, scheduleId, foundSchedule);
    }

    // 스케쥴을 제거하는 함수
    public Integer deleteSchedule(JdbcTemplate jdbcTemplate, Integer scheduleId, String password) {
        Schedule foundSchedule = scheduleRepository.getScheduleById(jdbcTemplate, scheduleId);

        if(foundSchedule == null) {
            throw new CustomException(ProjectErrorCode.ERROR_NOT_EXIST);
        }
        else if (!foundSchedule.getPassword().equals(password)) {
            throw new CustomException(ProjectErrorCode.ERROE_WRONG_PASSWORD);
        }

        return scheduleRepository.deleteScheduleData(jdbcTemplate, scheduleId);
    }
}
