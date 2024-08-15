package com.sparta.schedulemanager.utility;

// 전역으로 사용하는 문자열, 포멧을 묶어두는 Class 추후에 값이 변경될 경우 일괄적으로 처리하기 위해 사용
public class ProjectProtocol {
    public static String INPUT_DATE_FORMAT = "yyyyMMdd";
    public static String COMPARE_DATE_FORMAT = "yyyy-MM-dd";

    public static String TABLE_SCHEDULE = "schedule";
    public static String TABLE_MANAGER = "manager";

    public static String TABLE_COLUMN_ID = "id";
    public static String TABLE_COLUMN_MANAGER_ID = "managerid";
    public static String TABLE_COLUMN_MANAGER_NAME = "managername";
    public static String TABLE_COLUMN_EDITTIME = "edittime";
    public static String TABLE_COLUMN_TODO = "todo";
    public static String TABLE_COLUMN_PASSWORD = "password";
    public static String TABLE_COLUMN_CREATETIME = "createtime";
}
