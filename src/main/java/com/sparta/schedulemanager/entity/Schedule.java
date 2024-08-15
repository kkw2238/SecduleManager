package com.sparta.schedulemanager.entity;

import com.sparta.schedulemanager.dto.ScheduleRequestDto;
import com.sparta.schedulemanager.secure.SecureAlgorithm;
import com.sparta.schedulemanager.utility.ProjectProtocol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Schedule extends CustomEntity {
    private String todo;
    private String managerName;
    private String password;
    private String editTime;
    private String createTime;

    public Schedule(ScheduleRequestDto requestDto) {
        this.id = 0;
        this.todo = requestDto.getTodo();
        this.managerName = requestDto.getManagerName();
        this.password = requestDto.getPassword();
        this.editTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.createTime = this.editTime;
    }

    public Schedule(ResultSet rs) throws SQLException {
        this.id = rs.getInt(ProjectProtocol.TABLE_COLUMN_ID);
        this.todo = rs.getString(ProjectProtocol.TABLE_COLUMN_TODO);
        this.managerName = rs.getString(ProjectProtocol.TABLE_COLUMN_MANAGERNAME);
        this.password = rs.getString(ProjectProtocol.TABLE_COLUMN_PASSWORD);
        this.editTime = rs.getString(ProjectProtocol.TABLE_COLUMN_EDITTIME);
        this.createTime = rs.getString(ProjectProtocol.TABLE_COLUMN_CREATETIME);
    }

    public void editSchedule(Schedule schedule) {
        this.todo = schedule.getTodo();
        this.editTime = schedule.getEditTime();
        this.managerName = schedule.getManagerName();
    }

    // 비밀번호를 암호화 하는 함수
    public void encryptPassword(SecureAlgorithm secureAlgorithm) throws NoSuchAlgorithmException {
        this.password = secureAlgorithm.encrypt(this.password);
    }

    @Override
    public String toString() {
        return "Id: " + this.id + ", Todo: " + this.todo + ", Manager: " + this.managerName + ", Password: " + this.password + ", Edit: " + this.editTime + ", Create: " + this.createTime;
    }
}
