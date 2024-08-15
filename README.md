# 📅SceduleManager 소개

 
## 📌ERD
![Untitled (3)](https://github.com/user-attachments/assets/f5861c55-3178-4d5f-9a24-432b306cc720)

   
### TABLE Manager
| 이름 | 타입 | 제약조건 | 설명 |
| :-- | :-- | :-- | :-- |
| ID | VARCHAR(100) | PK, AUTO\_INCREMENT | 일정의 고유번호, PK |
| managername | VARCHAR(100) | NOT NULL | 담당자 이름 |
| manageremail | VARCHAR(100) | | 담당자의 이메일 주소 |
| edittime | DATETIME | NOT NULL | 수정일자, DATETIME 형식 |
| createtime | DATETIME | NOT NULL | 작성일자, DATETIME 형식 |
> ### 상세 설명
> #### TABLE Manager   
> ID : 매니저를 추가하거나 삭제할 때, ID를 기준으로 참조.   
> &emsp;&emsp;그만큼 **핵심 Column이기도 하며 중복이 되어선 안되기에, PK로 지정**.
>    
> managername : 담당자의 이름으로 NULL이 되어선 안되기에, **NOT NULL형태로 지정**
>    
> managerID : 담당자가 이메일을 사용하지 않을 수도 있기에 **NULL 가능하게 지정**.
>
> edittime : 수정 시각으로 연월일시분초 다 저장해야 하기에 DATETIME 형식으로 저장, **NOT NULL 형태로 지정**.   
>   
> createtime: 작성 시각으로 연월일시분초 다 저장해야 하기에 DATETIME 형식으로 저장, **NOT NULL 형태로 지정**.
<br/>
   
### TABLE Schedule
| 이름 | 타입 | 제약조건 | 설명 |
| :-- | :-- | :-- | :-- |
| ID | VARCHAR(100) | PK, AUTO\_INCREMENT | 일정의 고유번호, PK |
| todo | VARCHAR(100) | NOT NULL | 해야할 일 |
| managerID | INTEGER | FK ( Manager.ID ) | 담당자 고유번호 |
| password | VARCHAR(64) | NOT NULL | 비밀번호 |
| edittime | DATETIME | NOT NULL | 수정일자, DATETIME 형식 |
| createtime | DATETIME | NOT NULL | 작성일자, DATETIME 형식 |
> ### 상세 설명
> #### TABLE Schedule   
> ID : 일정을 추가하거나 삭제할 때, ID를 기준으로 참조.   
> &emsp;&emsp;그만큼 **핵심 Column이기도 하며 중복이 되어선 안되기에, PK로 지정**.
>    
> todo : 할 일이 없을 경우 스케쥴을 추가하지 않아야 하기에, **NOT NULL형태로 지정**.
>    
> managerID : Manager의 PK인 **ID를 사용하는 외래키**.
>    
> password : SHA256암호화 한 상태로 저장, 추후 수정/삭제가 가능해야 하기에, **NOT NULL형태로 지정**.
>    
> edittime : 수정 시각으로 연월일시분초 다 저장해야 하기에 SMALLDATETIME 형식으로 저장, **NOT NULL 형태로 지정**.   
>   
> createtime: 작성 시각으로 연월일시분초 다 저장해야 하기에 SMALLDATETIME 형식으로 저장, **NOT NULL 형태로 지정**.
   
<br/>
   
## 📌API명세서
 | **기능** | **Method** | **URL** | **request** | **response** | **state code** |
 | --- | --- | --- | --- | --- | --- |
 | 일정 작성 | POST | /api/scedules | 요청body | 등록 정보 | 200작성 성공 |
 | 선택한 일정 조회 | GET | /api/scedules/{sceduleId} | 요청param | 단건 일정 정보 | 200조회 성공  <br>404조회 실패 |
 | 일정 목록 조회 | GET | /api/scedules | 요청query | 다건 일정 정보 | 200조회 성공 |
 | 선택한 일정 수정 | PUT | /api/scedules/{sceduleId} | 요청body | 수정 정보 | 200수정 성공  <br>401비밀번호 불일치  <br>404조회 실패 |
 | 선택한 일정 삭제 | DELETE | /api/scedules/{sceduleId} | 요청body | 삭제정보 | 200삭제성공  <br>401비밀번호 불일치  <br>404조회 실패 |
 | 매니저 추가 | POST | /api/manager | 요청body | 등록 정보 | 200작성 성공 |
 | 매니저 삭제 | DELETE | /api/manager/{managerId} | 요청body | 삭제정보 | 200삭제성공 <br>404조회 실패 |

> ### 상세 설명 - 일정
> #### 🛠️일정 작성
>> #### Request
>> ##### Syntax
>> ```
>> { 
>>    "todo": "programming",
>>    "managerId": 1,
>>    "password":"password" 
>> }
>> ```
>> ##### Request Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | todo | String | 필수 | 해야할 일 |
>> | managerId | Integer | 필수 | 담당 매니저의 고유번호 |
>> | password | String | 필수 | 비밀번호 |
>>     
>> ##### Response
>> ###### Syntax
>> ```
>> {
>>     "id": 12,
>>     "todo": "contents",
>>     "managerId": 4,
>>     "editTime": "2024-08-16 00:07:28",
>>     "createTime": "2024-08-16 00:07:28"
>> }
>> ```
>>    
>> ###### Element   
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | id | Integer | 스케쥴 고유 번호 |
>> | todo | String | 해야할 일 |
>> | managerId | Integer | 담당 매니져 고유번호 |
>> | editTime | String | 스케쥴 수정 시간 |
>> | createTime | String | 스케쥴 생성 시간 |
>>
>> ###### State Code   
>> | **Code** | **Description** | **Detail** |
>> | --- | --- | --- |
>> | 200 | 작성 성공 | 해당 정보를 토대로 스케쥴 생성 성공 |
>>
>> <br/>
>    
> #### 🛠️선택한 일정 조회
>> #### Request
>> #### Syntax
>> ```
>> /api/schedules/{sceduleId}
>> ``` 
>> ##### Path Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | scheduleId | Integer | 필수 | 조회하고자 하는 스케쥴 고유 번호 |
>> 
>>     
>> ##### Response
>> ###### Syntax
>> ```
>> {
>> 	"scheduleId" : 1,
>> 	"managerName" : "이름",
>> 	"todo" : "해야할 일",
>> 	"editTime" : "2024-08-04 18:26:30",
>> 	"createTime" : "2024-08-04 18:26:30",
>> }
>> ```
>>    
>> ###### Element   
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | id | Integer | 스케쥴 고유 번호 |
>> | todo | String | 해야할 일 |
>> | managerId | Integer | 담당 매니져 고유번호 |
>> | editTime | String | 스케쥴 수정 시간 |
>> | createTime | String | 스케쥴 생성 시간 |
>> 
>> ###### State Code   
>> | **Code** | **Description** | **Detail** |
>> | --- | --- | --- |
>> | 200 | 조회 성공 | 스케쥴 조회 성공 |
>> | 404 | 조회 실패 | 해당 Id를 가진 스케쥴이 존재하지 않음 |
>>
>> <br/>
>    
> #### 🛠️일정 목록 조회
>> #### Request
>> ###### Syntax
>> ```
>> api/schedule?date={date}
>> ```
>> ##### Query Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | date | String | 필수 | 조회하고자 하는 날짜( yyyyMMdd 형태 ) |
>> 
>>     
>> ##### Response
>> ###### Syntax
>> ```
>> [
>> {
>> 	"scheduleId" : 1,
>> 	"managerName" : "이름",
>> 	"todo" : "해야할 일",
>> 	"editTime" : "2024-08-04 18:26:30",
>> 	"createTime" : "2024-08-04 18:26:30",
>> },
>> {
>> 	"scheduleId" : 2,
>> 	"managerName" : "이름2",
>> 	"todo" : "해야할 일",
>> 	"editTime" : "2024-08-04 18:29:35",
>> 	"createTime" : "2024-08-04 18:29:35",
>> },
>> {
>> 	"scheduleId" : 3,
>> 	"managerName" : "이름3",
>> 	"todo" : "해야할 일",
>> 	"editTime" : "2024-08-04 18:30:30",
>> 	"createTime" : "2024-08-04 18:30:30",
>> }
>> ]
>> ```
>>    
>> ###### Element   
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | responseDtos | List<ScheduleResponseDto> | 다수의 스케쥴 정보 |
>>
>> 
>> ###### State Code   
>> | **Code** | **Description** | **Detail** |
>> | --- | --- | --- |
>> | 200 | 조회 성공 | 스케쥴 조회 성공 |
>> | 404 | 조회 실패 | 해당 일자에 스케쥴이 존재하지 않음 |
>> <br/>
> #### 🛠️선택한 일정 수정
>> #### Request   
>> ##### Param Elements
>> ##### Syntax
>> ```
>> /api/schedules/{sceduleId}
>> ```
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | scheduleId | Integer | 필수 | 조회하고자 하는 스케쥴 고유 번호 |
>> <br/>
>>    
>> ```
>> password
>> ```
>> <br/>
>>    
>> ##### Request Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | password | String | 필수 | 스케쥴 비밀번호 |
>> 
>>     
>> ##### Response
>> ###### Syntax
>> ```
>> {
>> 	"scheduleId" : 1,
>> 	"managerName" : "이름",
>> 	"todo" : "해야할 일",
>> 	"editTime" : "2024-08-04 18:26:30",
>> 	"createTime" : "2024-08-04 18:26:30",
>> }
>> ```
>> ###### Element   
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | responseDtos | ScheduleResponseDto | 수정된 스케쥴 정보 |
>> 
>> ###### State Code   
>> | **Code** | **Description** | **Detail** |
>> | --- | --- | --- |
>> | 200 | 조회 성공 | 스케쥴 조회 성공 |
>> | 401 | 비밀번호 에러 | 비밀번호가 일치하지 않음 |
>> | 404 | 조회 실패 | 해당 일자에 스케쥴이 존재하지 않음 |
>> <br/>
>>    
> #### 🛠️선택한 일정 삭제
>> #### Request
>> ##### Syntax
>> ```
>> /api/schedules/{sceduleId}
>> ```
>> ##### Param Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | scheduleId | Integer | 필수 | 수정하고자 하는 스케쥴 고유 번호 |
>> <br/>
>>    
>> ```
>> password
>> ```
>> <br/>
>>    
>> ##### Request Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | password | String | 필수 | 스케쥴 비밀번호 |
>> 
>>     
>> ##### Response
>> ###### Syntax
>> ```
>> {scheduleId}
>> ```
>> ###### Element   
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | scheduleId | Integer | 삭제한 스케쥴 고유 번호 |
>> <br/>
>>    
>> ```
>> password
>> ```
>> ##### Request Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | password | String | 필수 | 스케쥴 비밀번호 |
>> <br/>
>>    
>> ###### State Code   
>> | **Code** | **Description** | **Detail** |
>> | --- | --- | --- |
>> | 200 | 조회 성공 | 스케쥴 조회 성공 |
>> | 401 | 비밀번호 에러 | 비밀번호가 일치하지 않음 |
>> | 404 | 조회 실패 | 해당 일자에 스케쥴이 존재하지 않음 |
>> <br/>

> ### 상세 설명 - 매니저
> #### 🛠️매니저 추가
>> #### Request 
>> ##### Syntax
>> ```
>> {
>>    "managerName": "김건우",
>>    "managerEmail": "kkw2238@naver.com"
>> }
>> ```
>> ##### Request Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | managerName | String | 필수 | 매니저 이름 |
>> | managerEmail | String | 선택 | 매니저 이메일 주소 |
>>     
>> ##### Response
>> ###### Syntax
>> ```
>> {
>>     "managerName": "김건우",
>>     "managerEmail": "kkw2238@naver.com",
>>     "editTime": "2024-08-16 00:42:50",
>>     "createTime": "2024-08-16 00:42:50",
>>     "id": 5
>> }
>> ```
>>    
>> ###### Element   
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | id | Integer | 매니저 고유 번호 |
>> | managerEmail | String | 해야할 일 |
>> | managerName | String | 매니저 이름 |
>> | editTime | String | 스케쥴 수정 시간 |
>> | createTime | String | 스케쥴 생성 시간 |
>>
>> ###### State Code   
>> | **Code** | **Description** | **Detail** |
>> | --- | --- | --- |
>> | 200 | 작성 성공 | 해당 정보를 토대로 매니저 추가 성공 |
>>
>> <br/>
>
> #### 🛠️매니저 삭제
>> #### Request
>> ##### Syntax
>> ```
>> /api/managers/{managerId}
>> ```
>> ##### Param Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | managerId | Integer | 필수 | 매니저 고유 번호 |
>> <br/>
>>    
>> ##### Response
>> ###### Syntax
>> ```
>> /api/managers/{ managerId }
>> ```
>> <br/>
>>    
>> ###### Element   
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | managerId | Integer | 삭제된 매니저 고유 번호 |
>>
>> ###### State Code   
>> | **Code** | **Description** | **Detail** |
>> | --- | --- | --- |
>> | 200 | 작성 성공 | 해당 정보를 토대로 매니저 추가 성공 |
>> | 404 | 조회 실패 | 존재하지 않거나 삭제된 매니저 |
>> <br/>
>    
