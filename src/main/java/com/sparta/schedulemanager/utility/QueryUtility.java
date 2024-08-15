package com.sparta.schedulemanager.utility;

import java.lang.reflect.Field;

// Query에 대한 Utility를 담당하는 Class
public class QueryUtility<T> {
    // Class 기반으로 InsetQuery 작성해주는 메서드
    public String getInsertQuery(String table, T data, String ignore) throws IllegalAccessException {
        StringBuilder query = new StringBuilder("INSERT INTO " + table + " (");
        StringBuilder values = new StringBuilder("VALUES (");

        // 해당 Class의 field를 순차적으로 읽어와 문자열에 append해준다.
        for (Field field : data.getClass().getDeclaredFields()) {
            if (!field.getName().equals(ignore)) {
                // field의 private데이터에 접근 할 수 있도록 임시 허용
                field.setAccessible(true);

                query.append(field.getName()).append(", ");
                values.append("\"").append(field.get(data)).append("\"").append(", ");

                // 허용 해제
                field.setAccessible(false);
            }
        }
        query.deleteCharAt(query.length() - 2);
        values.deleteCharAt(values.length() - 2);

        query.append(") ").append(values).append(")");
        return query.toString();
    }

    // 특정 Column값의 데이터를 조회하는 쿼리를 반환해주는 함수
    public String getGetByColumnDataQuery(String table, String columnName, String columnData)  {
        return "SELECT * FROM "
                + table + " WHERE " + columnName + " = " + columnData;
    }

    // 특정 시간대의 데이터를 조회하는 쿼리를 반환해주는 함수
    public String getGetByColumnDateQuery(String table, String columnName, String columnDate)  {
        return "SELECT * FROM " + table + " WHERE " + "DATE(" + columnName + ") = '" + columnDate + "'";
    }

    // 업데이트 쿼리문을 반환해주는 함수
    public String getUpdateQuery(String table, String[] fieldNames, T data, Integer id) throws IllegalAccessException, NoSuchFieldException {
        StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");

        Class<?> clazz = data.getClass();

        // SET 쿼리에 사용할 내용을 컬럼명 = "데이터" 형태로 만들어주는 함수
        for (String fieldName : fieldNames) {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            query.append(fieldName).append(" = \"").append(field.get(data)).append("\", ");
        }

        query.deleteCharAt(query.length() - 2);
        query.append(" WHERE id = ").append(id);

        return query.toString();
    }

    // 삭제 해주는 Query를 생성해주는 함수
    public String getDeleteByColumnDataQuery(String table, String columnName, String columnData) {
        return "DELETE FROM " + table + " WHERE " + columnName + " = \"" + columnData + "\"";
    }
}
