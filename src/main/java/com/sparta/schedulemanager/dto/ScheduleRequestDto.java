package com.sparta.schedulemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {
    private String todo;
    private Integer managerId;
    private String password;
}
