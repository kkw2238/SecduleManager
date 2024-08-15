package com.sparta.schedulemanager.dto;

import com.sparta.schedulemanager.entity.Manager;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagerResponseDto {
    private Integer Id;
    private String managerName;
    private String managerEmail;
    private String editTime;
    private String createTime;

    public ManagerResponseDto(Manager manager) {
        this.Id = manager.getId();
        this.managerName = manager.getManagerName();
        this.managerEmail = manager.getManagerEmail();
        this.editTime = manager.getEditTime();
        this.createTime = manager.getCreateTime();
    }
}
