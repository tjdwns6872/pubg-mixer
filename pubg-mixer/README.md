# PUBG Mixer - Team Building Tool

PUBG 게임에서 팀을 구성할 때 맵과 랜드마크를 자동으로 배정해주는 웹 애플리케이션입니다.

## 📋 프로젝트 구조

```
pubg-mixer/
├── backend/          # Spring Boot 4.0.3 (Java 21)
├── frontend/         # React + TypeScript + Vite
├── db/              # MySQL 8.4 Dockerfile
├── docker-compose.yml
└── .gitignore
```

## 🛠️ 필수 환경

- **Docker** (20.10+)
- **Docker Compose** (2.0+)

## 🚀 시작하기

### 1️⃣ 프로젝트 클론

```bash
git clone <repository-url>
cd pubg-mixer
```

### 2️⃣ 전체 스택 빌드 및 실행

```bash
docker-compose up --build
```

### 3️⃣ 서비스 접근

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **MySQL**: localhost:33307 (user: mixer, password: mixer)

## 📦 자동 생성되는 것들

빌드 시 다음이 **자동으로 생성**됩니다:
- ✅ MySQL 데이터베이스 (`mixer`)
- ✅ 모든 테이블 (Flyway 마이그레이션)
  - member
  - team
  - team_member
  - map
  - landmark
  - match_history
  - flyway_schema_history

## 🗄️ 데이터베이스 확인

```bash
# 테이블 목록 확인
docker-compose exec db mysql -u mixer -pmixer mixer -e "SHOW TABLES;"

# 마이그레이션 이력 확인
docker-compose exec db mysql -u mixer -pmixer mixer -e "SELECT * FROM flyway_schema_history;"
```

## 🛑 컨테이너 중지

```bash
# 컨테이너 중지
docker-compose down

# 컨테이너 + 볼륨 제거 (DB 초기화)
docker-compose down -v
```

## 📝 마이그레이션 추가 방법

새로운 테이블이나 스키마 변경이 필요할 때:

1. `backend/src/main/resources/db/migration/` 디렉토리에 파일 생성
2. 파일명 형식: `V{버전}__설명.sql`
   ```sql
   -- V2__add_team_status.sql
   ALTER TABLE team ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';
   ```
3. `docker-compose up --build` 실행
4. Flyway가 자동으로 새 마이그레이션 실행

## 🔧 개발 환경 설정

### Backend 로컬 개발
```bash
cd backend
./gradlew bootRun
```

### Frontend 로컬 개발
```bash
cd frontend
npm install
npm run dev
```

## 📚 주요 기술 스택

### Backend
- Spring Boot 4.0.3
- Spring Data JPA
- Hibernate 7.2.4
- MySQL Driver
- Flyway (DB 마이그레이션)
- Lombok
- QueryDSL 5.1.0

### Frontend
- React 19.2.0
- TypeScript 5.9.3
- Vite 7.3.1
- Nginx (프로덕션)

### Database
- MySQL 8.4
- Flyway 11.14.1

## 🐳 Docker 네트워크

서비스들이 `mixer-network`라는 Docker 네트워크로 연결되어 있습니다:
- Backend → DB: `jdbc:mysql://db:3306/mixer`
- Frontend → Backend: `http://backend:8080`

## 📖 Flyway 마이그레이션 동작

1. Backend 시작 시 `FlywayConfig.java`에서 Flyway 초기화
2. `classpath:db/migration` 경로의 SQL 파일 스캔
3. `flyway_schema_history` 테이블에서 실행 이력 확인
4. 미실행 마이그레이션만 자동 실행
5. 새 환경에서도 동일하게 자동 생성

## 🔐 주의사항

- `.env` 파일은 커밋하지 않기 (민감한 정보)
- 프로덕션 배포 시 `MYSQL_ROOT_PASSWORD` 변경 필수
- Docker 컨테이너 이름 충돌 확인

## 📞 문제 해결

### 포트 충돌
```bash
# 이미 사용 중인 포트 확인
# docker-compose.yml에서 포트 변경
```

### DB 연결 오류
```bash
# DB 상태 확인
docker-compose exec db mysqladmin -u mixer -pmixer status

# Backend 로그 확인
docker-compose logs backend
```

### Flyway 마이그레이션 실패
```bash
# 마이그레이션 이력 확인
docker-compose exec db mysql -u mixer -pmixer mixer -e "SELECT * FROM flyway_schema_history;"

# Backend 로그에서 Flyway 메시지 확인
docker-compose logs backend | grep -i flyway
```