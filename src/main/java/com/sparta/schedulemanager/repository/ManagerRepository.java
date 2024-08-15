package com.sparta.schedulemanager.repository;

import com.sparta.schedulemanager.entity.CustomEntity;
import com.sparta.schedulemanager.entity.Manager;
import com.sparta.schedulemanager.utility.ProjectProtocol;
import org.springframework.jdbc.core.JdbcTemplate;

public class ManagerRepository<T extends CustomEntity> extends BaseRepository<T> {

    public Manager getManagerById(JdbcTemplate jdbcTemplate, Integer id) {
        return findManagerById(jdbcTemplate, id);
    }

    // ID정보를 토대로 DataBase 조회하는 함수
    public Manager findManagerById(JdbcTemplate jdbcTemplate, Integer id) {
        String getQuery = queryUtility.getGetByColumnDataQuery(ProjectProtocol.TABLE_MANAGER, ProjectProtocol.TABLE_COLUMN_ID, String.valueOf(id));

        return jdbcTemplate.query(getQuery, resultSet -> {
            if (resultSet.next()) {
                return new Manager(resultSet);
            } else {
                return null;
            }
        });
    }
}
