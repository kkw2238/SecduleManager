package com.sparta.schedulemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {
    private String todo;
    private String managerName;
    private String password;
}
