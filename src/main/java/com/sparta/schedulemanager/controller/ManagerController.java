package com.sparta.schedulemanager.controller;

import com.sparta.schedulemanager.dto.ManagerRequestDto;
import com.sparta.schedulemanager.dto.ManagerResponseDto;
import com.sparta.schedulemanager.entity.Manager;
import com.sparta.schedulemanager.repository.ManagerRepository;
import com.sparta.schedulemanager.repository.ScheduleRepository;
import com.sparta.schedulemanager.service.ManagerService;
import com.sparta.schedulemanager.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class ManagerController {
    private final JdbcTemplate jdbcTemplate;
    private final ManagerService managerService;

    public ManagerController(JdbcTemplate jdbcTemplate) {
        ManagerRepository repository = new ManagerRepository();

        managerService = new ManagerService(repository);
        this.jdbcTemplate = jdbcTemplate;
    }

    // 매니져 정보 추가
    @PostMapping("/managers")
    public ManagerResponseDto addManager(@RequestBody ManagerRequestDto requestDto) throws IllegalAccessException {
        Manager manager = new Manager(requestDto);

        managerService.addManager(jdbcTemplate, manager);

        return new ManagerResponseDto(manager);
    }

    @DeleteMapping("/managers/{managerId}")
    public Integer deleteSchedule(@PathVariable Integer managerId) throws NoSuchAlgorithmException {
        ScheduleRepository repository = new ScheduleRepository();
        ScheduleService scheduleService = new ScheduleService(repository);

        managerId = Integer.parseInt(managerService.deleteManager(jdbcTemplate, scheduleService, managerId));

        return managerId;
    }
}
