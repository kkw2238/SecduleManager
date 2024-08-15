package com.sparta.schedulemanager.controller;

import com.sparta.schedulemanager.dto.ScheduleRequestDto;
import com.sparta.schedulemanager.dto.ScheduleResponseDto;
import com.sparta.schedulemanager.entity.Schedule;
import com.sparta.schedulemanager.repository.ScheduleRepository;
import com.sparta.schedulemanager.secure.SHA256;
import com.sparta.schedulemanager.service.ScheduleService;
import com.sparta.schedulemanager.utility.ProjectProtocol;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ScheduleResponseDto addSchedule(@RequestBody ScheduleRequestDto requestDto) throws NoSuchAlgorithmException, IllegalAccessException {
        Schedule schedule = new Schedule(requestDto);

        // 비밀번호 원본을 저장하지 않기 위해 SHA256형태로 암호화 작업
        schedule.encryptPassword(new SHA256());
        scheduleService.addSchedule(jdbcTemplate, schedule);

        return new ScheduleResponseDto(schedule);
    }

    // Schedule ID를 조회하는 함수
    @GetMapping("/schedules/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Integer scheduleId) {
        Schedule schedule = scheduleService.getScheduleById(jdbcTemplate, scheduleId);

        return new ScheduleResponseDto(schedule);
    }

    // 특정 일자에 수정된 일정들을 불러오는 함수
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules(@RequestParam String date) throws ParseException {
        // 입력된 문자열을 yyyyMMdd형태로 파싱
        Date d = new SimpleDateFormat(ProjectProtocol.INPUT_DATE_FORMAT).parse(date);

        // 파싱된 값을 SQL과 비교하기 쉬운 yyyy-MM-dd 형태로 변환
        String convertedDate = new SimpleDateFormat(ProjectProtocol.COMPARE_DATE_FORMAT).format(d);
        return scheduleService.getSchedulesByDate(jdbcTemplate, convertedDate).stream()
                .map(ScheduleResponseDto::new).toList();
    }

    // 스케쥴 수정하는 함수
    @PutMapping("/schedules/{scheduleId}")
    public ScheduleResponseDto editSchedule(@PathVariable Integer scheduleId, @RequestBody ScheduleRequestDto requestDto) throws NoSuchFieldException, IllegalAccessException, NoSuchAlgorithmException {
        Schedule schedule = new Schedule(requestDto);

        // 비교를 위한 SHA256암호화
        schedule.encryptPassword(new SHA256());

        // 수정 작업 진행
        schedule = scheduleService.editSchedule(jdbcTemplate, scheduleId, schedule);

        return new ScheduleResponseDto(schedule);
    }

    // 스케쥴 삭제하는 함수
    @DeleteMapping("/schedules/{scheduleId}")
    public Integer deleteSchedule(@PathVariable Integer scheduleId, @RequestBody String password) throws NoSuchAlgorithmException {
        password = new SHA256().encrypt(password);

        return Integer.parseInt(scheduleService.deleteSchedule(jdbcTemplate, scheduleId, password));
    }
}
