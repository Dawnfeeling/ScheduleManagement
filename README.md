## 개요
Spring으로 CRUD를 구현한 ScheduleManagement 프로젝트

**API 명세서, ERD, SQL**

https://abalone-flat-811.notion.site/Schedule-management-Project-1301531f179080d0b9b4eed6077a2aff?pvs=4

## 주요 기능
controller
- SchedileController : Request를 받아 Service로 데이터를 넘겨준다.

dto
- ScheduleRequestDto : 요청 데이터를 받는 객체
- ScheduleResponseDto : 응답 데이터를 전달하는 객체

entity
- Schedule : 스케줄의 정보를 담는 객체

repository
- ScheduleRepository : DB 상호작용 인터페이스
- JdbcTemplateScheduleRepository : ScheduleRepository구현체
  - 저장, 조회, 수정, 삭제

service
- ScheduleService : 비즈니스 로직 인터페이스
- SceduleServiceImpl : SchedileService 구현체
  - 비밀번호 판별, null 체크등의 로직 수행

## 실행
