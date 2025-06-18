# BookNote 프로젝트

## 개요
BookNote는 JWT 기반 인증을 사용하는 독서 노트 관리 애플리케이션입니다. 사용자는 책을 등록하고, 각 책에 대한 독서 노트를 작성/관리할 수 있습니다.

## 주요 기능
- JWT 기반 사용자 인증 (회원가입/로그인)
- 책 정보 등록 및 조회
- 각 책에 대한 독서 노트 작성/조회/삭제
- 사용자별 노트 관리

## 기술 스택
- **Backend**: Java 17, Spring Boot 3.4.3
- **Database**: MariaDB
- **ORM**: Spring Data JPA
- **Security**: Spring Security + JWT
- **Build Tool**: Gradle
- **Lombok**: 코드 간소화

## 프로젝트 구조
```
booknote/
├── src/main/java/com/sy/
│   ├── config/           # Spring Security 설정
│   ├── controller/       # REST API 컨트롤러
│   ├── domain/          # JPA 엔티티 (Book, Note, User)
│   ├── dto/             # 요청/응답 DTO
│   ├── exception/       # 전역 예외 처리
│   ├── jwt/             # JWT 관련 유틸리티
│   ├── service/         # 비즈니스 로직
│   └── BookNoteApplication.java
├── src/main/resources/
│   ├── application.yml  # DB 및 JPA 설정
│   └── templates/       # 뷰 템플릿
└── build.gradle
```

## 데이터베이스 설계

### ERD (Entity Relationship)
```
User (사용자)
├── id (PK)
├── email (unique)
├── password (암호화)
└── name

Book (도서)
├── id (PK)
├── title
├── author
├── publisher
├── coverImage
├── description
└── notes (List<Note>) - 1:N 관계

Note (독서 노트)
├── id (PK)
├── title
├── content
├── writer (작성자)
├── createdDate
└── book (FK) - N:1 관계
```

## API 문서

### 인증 관련 API

#### 1. 회원가입
```http
POST /signup
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "password123",
    "name": "홍길동"
}
```

#### 2. 로그인
```http
POST /login
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "password123"
}
```
**응답**: JWT 토큰 문자열

### 책 관리 API

#### 3. 책 목록 조회
```http
GET /booklist
Authorization: Bearer {JWT_TOKEN}
```

#### 4. 책 등록
```http
POST /book
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
    "title": "자바의 정석",
    "author": "남궁성",
    "publisher": "도우출판",
    "coverImage": "/images/java.jpg",
    "description": "자바 기초부터 심화까지"
}
```

#### 5. 책 상세 조회
```http
GET /book/{bookId}
Authorization: Bearer {JWT_TOKEN}
```

### 노트 관리 API

#### 6. 노트 생성
```http
POST /book/{bookId}/note
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
    "title": "독서 노트 제목",
    "content": "독서 노트 내용"
}
```

#### 7. 노트 삭제
```http
DELETE /book/{bookId}/note/{noteId}
Authorization: Bearer {JWT_TOKEN}
```

## JWT 토큰 구조

### 토큰 생성 시 포함 정보
- **Subject**: 사용자 이메일
- **Claim**: 사용자 이름
- **만료 시간**: 24시간

### 토큰 사용 방법
1. 로그인 성공 시 JWT 토큰을 받습니다
2. 이후 인증이 필요한 API 요청 시 헤더에 추가:
   ```
   Authorization: Bearer {JWT_TOKEN}
   ```

## 설치 및 실행

### 1. 데이터베이스 설정
```sql
CREATE DATABASE booknote CHARACTER SET utf8mb4;
```

### 2. application.yml 설정
```yaml
spring:
  datasource:
    url: jdbc:mariadb://localhost:3306/booknote
    username: your_username
    password: your_password
```

### 3. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 4. API 테스트
- Postman 또는 curl을 사용하여 API 테스트
- JWT 토큰은 로그인 API에서 받은 후 다른 API에 사용

## 보안 기능

### 1. 비밀번호 암호화
- BCrypt 알고리즘 사용
- 평문 비밀번호는 DB에 저장되지 않음

### 2. JWT 토큰 검증
- 모든 요청에서 JWT 토큰 유효성 검증
- 만료된 토큰은 자동으로 거부

### 3. 예외 처리
- 전역 예외 처리기로 일관된 에러 응답
- 보안 관련 예외는 적절한 HTTP 상태코드로 반환

## 개발 가이드

### 1. 새로운 API 추가
1. DTO 클래스 생성 (요청/응답)
2. Service 인터페이스에 메서드 추가
3. ServiceImpl에 비즈니스 로직 구현
4. Controller에 엔드포인트 추가

### 2. JWT 토큰에서 사용자 정보 추출
```java
String token = authHeader.replace("Bearer ", "");
String email = jwtUtil.getEmailFromToken(token);
String name = jwtUtil.getNameFromToken(token);
```

### 3. 순환참조 방지
- 엔티티를 직접 반환하지 않고 DTO로 변환
- @JsonIgnore, @JsonManagedReference 등 사용 가능

## 주의사항

1. **JWT 시크릿 키**: 운영 환경에서는 반드시 변경
2. **토큰 만료 시간**: 보안 요구사항에 맞게 조정
3. **데이터베이스 백업**: 정기적인 백업 권장
4. **로깅**: 보안 관련 로그 적절히 설정

## 향후 개선 사항

- [ ] 리프레시 토큰 구현
- [ ] 사용자 권한 관리 (ROLE)
- [ ] 노트 수정 기능
- [ ] 책 검색 기능
- [ ] 파일 업로드 (책 표지 이미지)
- [ ] API 문서화 (Swagger)

## 라이선스
이 프로젝트는 학습 목적으로 제작되었습니다. 