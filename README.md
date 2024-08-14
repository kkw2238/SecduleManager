# 📅SceduleManager 소개

 
## 📌ERD
![Untitled](https://github.com/user-attachments/assets/553d71f1-48fe-4da7-88a0-8c0b4c6fe0b0)

 |이름|타입|제약조건|설명|
|:------|:---|:---|:---|
|ID|VARCHAR(100)|PK, AUTO_INCREMENT| 일정의 고유번호, PK |
|todo|VARCHAR(100)|| 해야할 일 |
|managername|VARCHAR(100)|NOT NULL| 담당자 이름 |
|password|VARCHAR(64)|NOT NULL| 비밀번호  |
|edittime|SMALLDATETIME|NOT NULL| 수정일자, DATETIME 형식 |
|createtime|SMALLDATETIME|NOT NULL| 작성일자, DATETIME 형식 |

> ### 상세 설명
> ID : 일정을 추가하거나 삭제할 때, ID를 기준으로 참조.   
> &emsp;&emsp;그만큼 **핵심 Column이기도 하며 중복이 되어선 안되기에, PK로 지정**.
>    
> todo : 출근 했지만 일이 없을 수도 있기에, **NULL 가능하게 지정**.
>    
> managername : 해당 기능을 사용하기 위해서는 담당자의 정보가 필요하기에, **NOT NULL형태로 지정**.
>    
> password : 추후 수정/삭제가 가능해야 하기에, **NOT NULL형태로 지정**.
>    
> edittime : 수정 시각으로 연월일시분초 다 저장해야 하기에 SMALLDATETIME 형식으로 저장, **NOT NULL 형태로 지정**.   
>   
> createtime: 작성 시각으로 연월일시분초 다 저장해야 하기에 SMALLDATETIME 형식으로 저장, **NOT NULL 형태로 지정**.
   
<br/><br/><br/>

## 📌API명세서
 | **기능** | **Method** | **URL** | **request** | **response** | **state code** |
 | --- | --- | --- | --- | --- | --- |
 | 일정 작성 | POST | /api/scedules | 요청body | 등록 정보 | 200작성 성공 |
 | 선택한 일정 조회 | GET | /api/scedules/{sceduleId} | 요청param | 단건 일정 정보 | 200조회 성공  <br>404조회 실패 |
 | 일정 목록 조회 | GET | /api/scedules | 요청query | 다건 일정 정보 | 200조회 성공 |
 | 선택한 일정 수정 | PUT | /api/scedules/{sceduleId} | 요청body | 수정 정보 | 200수정 성공  <br>401비밀번호 불일치  <br>404조회 실패 |
 | 선택한 일정 삭제 | DELETE | /api/scedules/{sceduleId} | 요청body | 삭제정보 | 200삭제성공  <br>401비밀번호 불일치  <br>404조회 실패 |

> ### 상세 설명
> #### 🛠️일정 작성
>> #### Request   
>> ##### Request Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | requestDto | ScheduleRequestDto | 필수 | 스케쥴 정보 |
>> | password | String | 필수 | 스케쥴 비밀번호 |
>>     
>> ##### Response
>> ###### Syntax
>> ```
>> {
>> 	"scheduleId" : 1
>> }
>> ```
>>    
>> ###### Element   
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | scheduleId | Integer | 스케쥴 고유 번호 |
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
>> ##### Param Elements
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
>> | responseDto | ScheduleResponseDto | 스케쥴 정보 |
>>
>> ###### ScheduleResponseDto
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | scheduleId | Integer | 스케쥴 고유 번호 |
>> | managerName | String | 스케쥴 담당자 이름 |
>> | todo | String | 해야할 일 |
>> | editTime | String | 수정 시각 |
>> | createTime | String | 작성 시각 |
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
>> ##### Query Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | date | String | 필수 | 조회하고자 하는 날짜 |
>> 
>>     
>> ##### Response
>> ###### Syntax
>> ```
>> "responeScedules" : [
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
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | scheduleId | Integer | 필수 | 조회하고자 하는 스케쥴 고유 번호 |
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
>> ##### Param Elements
>> | **Parameter** | **Type** | **Required** | **Description** |
>> | --- | --- | --- | --- |
>> | scheduleId | Integer | 필수 | 수정하고자 하는 스케쥴 고유 번호 |
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
>> }
>> ```
>>    
>> ###### Element   
>> | **Parameter** | **Type** | **Description** |
>> | --- | --- | --- |
>> | scheduleId | Integer | 삭제한 스케쥴 고유 번호 |
>>
>> 
>> ###### State Code   
>> | **Code** | **Description** | **Detail** |
>> | --- | --- | --- |
>> | 200 | 조회 성공 | 스케쥴 조회 성공 |
>> | 401 | 비밀번호 에러 | 비밀번호가 일치하지 않음 |
>> | 404 | 조회 실패 | 해당 일자에 스케쥴이 존재하지 않음 |
>> <br/>
