package com.sparta.schedulemanager.entity;

import com.sparta.schedulemanager.dto.ManagerRequestDto;
import com.sparta.schedulemanager.utility.ProjectProtocol;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@NoArgsConstructor
public class Manager extends CustomEntity {
    private String managerName;
    private String managerEmail;
    private String editTime;
    private String createTime;

    public Manager(ManagerRequestDto requestDto) {
        this.id = 0;
        this.managerName = requestDto.getManagerName();
        this.managerEmail = requestDto.getManagerEmail();
        this.editTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.createTime = this.editTime;
    }

    public Manager(ResultSet rs) throws SQLException {
        this.id = rs.getInt(ProjectProtocol.TABLE_COLUMN_ID);
        this.managerName = rs.getString(ProjectProtocol.TABLE_COLUMN_MANAGER_NAME);
        this.editTime = rs.getString(ProjectProtocol.TABLE_COLUMN_EDITTIME);
        this.createTime = rs.getString(ProjectProtocol.TABLE_COLUMN_CREATETIME);
    }
}
