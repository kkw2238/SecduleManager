package com.sparta.schedulemanager.dto;

import com.sparta.schedulemanager.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
    private int scheduleId;
    private String todo;
    private String managerName;
    private String editTime;
    private String createTime;

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.todo = schedule.getTodo();
        this.managerName = schedule.getManagerName();
        this.editTime = schedule.getEditTime();
        this.createTime = schedule.getCreateTime();
    }
}
