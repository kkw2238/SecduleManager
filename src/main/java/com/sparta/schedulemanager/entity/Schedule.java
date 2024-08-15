package com.sparta.schedulemanager.entity;

import com.sparta.schedulemanager.dto.ScheduleRequestDto;
import com.sparta.schedulemanager.secure.SecureAlgorithm;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@NoArgsConstructor
public class Schedule {
    private int scheduleId;
    private String todo;
    private String managerName;
    private String password;
    private String editTime;
    private String createTime;

    public Schedule(ScheduleRequestDto requestDto) {
        this.scheduleId = 0;
        this.todo = requestDto.getTodo();
        this.managerName = requestDto.getManagerName();
        this.password = requestDto.getPassword();
        this.editTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.createTime = this.editTime;
    }

    // 비밀번호를 암호화 하는 함수
    public void encryptPassword(SecureAlgorithm secureAlgorithm) throws NoSuchAlgorithmException {
        this.password = secureAlgorithm.encrypt(this.password);
    }

    @Override
    public String toString() {
        return "Id: " + this.scheduleId + ", Todo: " + this.todo + ", Manager: " + this.managerName + ", Password: " + this.password + ", Edit: " + this.editTime + ", Create: " + this.createTime;
    }

    public void setScheduleId(final int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
