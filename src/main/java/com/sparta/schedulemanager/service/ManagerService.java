package com.sparta.schedulemanager.service;

import com.sparta.schedulemanager.entity.Manager;
import com.sparta.schedulemanager.exception.CustomException;
import com.sparta.schedulemanager.exception.ProjectErrorCode;
import com.sparta.schedulemanager.repository.ManagerRepository;
import com.sparta.schedulemanager.utility.ProjectProtocol;
import org.springframework.jdbc.core.JdbcTemplate;

public class ManagerService {
    ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    // 매니저를 추가하는 함수
    public void addManager(JdbcTemplate jdbcTemplate, Manager manager) throws IllegalAccessException {
        managerRepository.saveData(jdbcTemplate, ProjectProtocol.TABLE_MANAGER, manager);
    }

    // 매니저를 제거하는 함수
    public String deleteManager(JdbcTemplate jdbcTemplate, ScheduleService scheduleService, Integer managerId) {
        Manager manager = managerRepository.findManagerById(jdbcTemplate, managerId);

        if(manager == null) {
            throw new CustomException(ProjectErrorCode.ERROR_NOT_EXIST);
        }

        // 매니저 정보를 삭제하기 전에 관련된 스케쥴을 우선 삭제( 스케쥴에서 매니저 정보를 외래키로 참조하고 있기에 우선 삭제 )
        scheduleService.deleteSchedulesByManagerId(jdbcTemplate, managerId);

        // 매니저 스케쥴이 삭제된 후 매니저 정보를 삭제
        return managerRepository.deleteData(jdbcTemplate, ProjectProtocol.TABLE_MANAGER, ProjectProtocol.TABLE_COLUMN_ID, String.valueOf(managerId));
    }
}
