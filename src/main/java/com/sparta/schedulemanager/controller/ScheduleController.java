package com.sparta.schedulemanager.controller;

import com.sparta.schedulemanager.dto.ScheduleRequestDto;
import com.sparta.schedulemanager.dto.ScheduleResponseDto;
import com.sparta.schedulemanager.entity.Schedule;
import com.sparta.schedulemanager.repository.ScheduleRepository;
import com.sparta.schedulemanager.secure.SHA256;
import com.sparta.schedulemanager.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        ScheduleRepository repository = new ScheduleRepository();

        this.scheduleService = new ScheduleService(repository);
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/schedules")
    public ScheduleResponseDto addSchedule(@RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        try {
            // 비밀번호 원본을 저장하지 않기 위해 SHA256형태로 암호화 작업
            schedule.encryptPassword(new SHA256());
        } catch (NoSuchAlgorithmException algorithmException) {
            System.out.println("No such algorithm");
            return null;
        }

        scheduleService.addSchedule(jdbcTemplate, schedule);
        return new ScheduleResponseDto(schedule);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Integer scheduleId) {
        return null;
    }

    @GetMapping("/schedules")
    public ScheduleResponseDto getSchedules(@RequestParam String date) {
        return null;
    }

    @PutMapping("/schedules/{scheduleId}")
    public ScheduleResponseDto editSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return null;
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public Integer editSchedule(@PathVariable Integer scheduleId, @RequestBody String password) {
        return null;
    }
}
