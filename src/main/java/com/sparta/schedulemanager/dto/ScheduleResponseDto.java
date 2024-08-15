package com.sparta.schedulemanager.dto;

import com.sparta.schedulemanager.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
    private int id;
    private String todo;
    private Integer managerId;
    private String editTime;
    private String createTime;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.managerId = schedule.getManagerId();
        this.editTime = schedule.getEditTime();
        this.createTime = schedule.getCreateTime();
    }
}
