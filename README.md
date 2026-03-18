# 이메일 알림 발송 API 템플릿

연습용으로 바로 시작할 수 있게 최소 구조만 잡아둔 Spring Boot + JPA + Oracle 템플릿이다.

## 구성
- EmailTemplate: 메일 템플릿 관리
- EmailHistory: 발송 이력 저장
- Template CRUD API
- Direct / Template 기반 메일 발송 API
- Oracle DDL 포함

## 폴더 구조
```text
src/main/java/com/example/emailapi
├─ domain
│  ├─ email
│  │  ├─ controller
│  │  ├─ dto
│  │  └─ service
│  ├─ history
│  │  ├─ entity
│  │  └─ repository
│  └─ template
│     ├─ controller
│     ├─ dto
│     ├─ entity
│     ├─ repository
│     └─ service
└─ global
   └─ exception
```

## 먼저 할 일
1. Oracle에 `src/main/resources/db/oracle/ddl.sql` 실행
2. `application.yml`에서 Oracle 계정 / SMTP 계정 수정
3. 앱 실행 후 Postman으로 API 호출

## API 예시
### 1) 템플릿 등록
`POST /api/templates`

```json
{
  "code": "WELCOME",
  "name": "회원가입 완료",
  "subjectTemplate": "{{name}}님, 가입이 완료되었습니다.",
  "bodyTemplate": "안녕하세요 {{name}}님. 가입을 환영합니다.",
  "useYn": "Y"
}
```

### 2) 템플릿 기반 메일 발송
`POST /api/emails/send/template`

```json
{
  "toEmail": "test@example.com",
  "templateCode": "WELCOME",
  "variables": {
    "name": "형님"
  }
}
```

### 3) 직접 메일 발송
`POST /api/emails/send/direct`

```json
{
  "toEmail": "test@example.com",
  "subject": "테스트 메일",
  "body": "메일 발송 API 테스트입니다."
}
```

### 4) 발송 이력 조회
`GET /api/emails/history`

## 1차에서 일부러 뺀 것
- 첨부파일
- 예약 발송
- CC / BCC
- HTML 메일 전송
- 재발송 API
- 수신자 테이블 분리

이건 기본 CRUD / JPA 흐름 익힌 다음 2차로 붙이면 된다.
