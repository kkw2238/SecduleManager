package com.sparta.schedulemanager.entity;

import com.sparta.schedulemanager.dto.ScheduleRequestDto;
import com.sparta.schedulemanager.secure.SecureAlgorithm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        this.id = rs.getInt("id");
        this.todo = rs.getString("todo");
        this.managerName = rs.getString("managername");
        this.password = rs.getString("password");
        this.editTime = rs.getString("edittime");
        this.createTime = rs.getString("createtime");
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
