CREATE DATABASE ScheduleManager;

CREATE TABLE manager(
        ID int primary key auto_increment comment '매니저 고유번호',
        managername varchar(100) not null comment '매니저 이름',
        manageremail varchar(100) comment '매니저 이메일',
        edittime datetime not null comment '수정일',
        createtime datetime not null comment '등록일'
);

CREATE TABLE schedule(
    ID int primary key auto_increment comment '스케쥴 고유번호',
    todo varchar(100) not null comment '할 일',
    managerID int,
    password varchar(64) not null comment '비밀번호',
    edittime datetime not null comment '수정일자',
    createtime datetime not null comment '작성일자',

    FOREIGN KEY (managerID) REFERENCES  manager(ID)
);