package com.sparta.schedulemanager.controller;

import com.sparta.schedulemanager.dto.ScheduleRequestDto;
import com.sparta.schedulemanager.dto.ScheduleResponseDto;
import com.sparta.schedulemanager.entity.Schedule;
import com.sparta.schedulemanager.repository.ScheduleRepository;
import com.sparta.schedulemanager.secure.SHA256;
import com.sparta.schedulemanager.service.ScheduleService;
import com.sparta.schedulemanager.utility.ProjectProtocol;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            scheduleService.addSchedule(jdbcTemplate, schedule);
        } catch (NoSuchAlgorithmException algorithmException) {
            System.out.println("No such algorithm");
            return null;
        } catch (IllegalAccessException illegalAccessException) {
            System.out.println("Class Error");
        }

        return new ScheduleResponseDto(schedule);
    }

    // Schedule ID를 조회하는 함수
    @GetMapping("/schedules/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Integer scheduleId) {
        Schedule schedule = scheduleService.getScheduleById(jdbcTemplate, scheduleId);

        if(schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }

        return new ScheduleResponseDto(schedule);
    }

    // 특정 일자에 수정된 일정들을 불러오는 함수
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules(@RequestParam String date) {
        // 입력된 문자열을 yyyyMMdd형태로 파싱
        Date d = new Date();

        try {
            d = new SimpleDateFormat(ProjectProtocol.INPUT_DATE_FORMAT).parse(date);
        } catch (ParseException ex) {
            System.out.println("Invalid date format");
        }

        // 파싱된 값을 SQL과 비교하기 쉬운 yyyy-MM-dd 형태로 변환
        String convertedDate = new SimpleDateFormat(ProjectProtocol.COMPARE_DATE_FORMAT).format(d);
        return scheduleService.getSchedulesByDate(jdbcTemplate, convertedDate).stream()
                .map(ScheduleResponseDto::new).toList();
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
