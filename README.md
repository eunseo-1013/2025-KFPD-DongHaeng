# 동행 

<p align="center">
    <img src="https://github.com/user-attachments/assets/bcf0689a-1f9d-40ad-afcc-7284d3015f4f" alt="동행 로고" width="200"/>
</p>

<p align="center"><strong>"지금, 여기서, 잠깐의 도움"으로 일상의 장벽을 함께 넘습니다</strong></p>

> <p align="center">휠체어 사용자와 이웃을 실시간으로 연결하는 소셜 동행 매칭 플랫폼</p>

---

## 📚 Table of Contents

- [Collaborators](#collaborators)
- [Introduction](#introduction)
- [System Structure](#system-structure)
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Database Schema](#database-schema)
- [API Documentation](#api-documentation)
- [How to Start](#how-to-start)
- [Development Roadmap](#development-roadmap)
- [License](#license)

---

## 👥 Collaborators

<h3 align="center">Team</h3>

<div align="center">

|                          Backend                          |                        Frontend                        |                        Frontend                         |                       Frontend                        |                       UI/UX Design                        |
| :----------------------------------------------------: | :-----------------------------------------------------: | :------------------------------------------------------: | :---------------------------------------------------: | :---------------------------------------------------: |
|          조영찬          |          장수민          |          장은서          |          손정연          |          김우인          |

</div>

---

## 💡 Introduction

### 문제 인식

**기존의 이동 지원 서비스들**에서는 휠체어 사용자가 **즉흥적으로 외출**하기 위해서 사전 예약과 복잡한 절차를 거쳐야 하는 **번거로운 과정이 필요**합니다. 

장애인 콜택시, 활동지원사 등은 계획된 일정에 따른 이동에는 효과적이지만, "지금 당장 5분만 도와주세요"와 같은 일상 속 즉흥적인 도움 요청에는 부적합합니다.

우리가 살아가는 도시에는 상점 입구의 작은 턱, 몇 칸의 계단, 좁은 골목길 등 **'마지막 10미터(Last 10-meters)'의 장벽**이 존재합니다.

### 우리의 솔루션

저희 **동행**은 기존과 차별화된 **실시간 매칭**, 즉, **빠르고 가벼운 도움**을 목표로 하고 있습니다.

<details>
    <summary><h3>📋 프로젝트 기획 상세보기</h3></summary>

### 기획 배경

#### 왜 동행인가?

우리가 살아가는 도시에는 눈에 잘 보이지 않는 수많은 장벽이 존재합니다. 특히 휠체어를 이용하는 이동 약자에게, 상점 입구의 작은 턱 하나, 몇 칸의 계단, 좁은 골목길 등은 가고 싶은 곳을 포기하게 만드는 거대한 벽이 됩니다.

물론, '장애인 콜택시'나 '활동지원사'와 같은 훌륭한 공적 지원 제도가 존재합니다. 하지만 이러한 서비스는 계획된 일정에 따른 '목적지까지의 이동'을 돕는 것에 중점을 둡니다. "지금 당장, 저 카페에 들어가기 위해 5분만 도와주세요" 또는 "저기 언덕까지만 같이 가주세요"와 같은 **일상 속 즉흥적이고 단기적인 도움 요청**을 해결하기에는 너무 무겁고 경직된 방식입니다.

#### 핵심 가치

1. **즉시성 및 유연성 확보**  
   도움이 필요한 바로 그 순간, 그 장소에서 실시간으로 도움을 요청하고 받을 수 있는 유연한 시스템

2. **사회적 관계망 형성**  
   '돌봄'이라는 일방적인 관계를 넘어, '산책'과 '만남'이라는 키워드를 통해 수평적 관계에서 자연스럽게 교류

3. **지속가능한 참여 유도**  
   봉사 시간 인정, 지역 상점 연계 포인트 등 의미 있는 보상 시스템과 게이미피케이션을 통한 지속가능한 생태계

### 차별화 포인트

| 구분 | 기존 서비스 | 동행 |
|------|------------|------|
| **시간** | 계획 기반, 사전 예약 | 즉흥적, 실시간 매칭 |
| **규모** | 장거리 이동 중심 | 짧은 거리, 작은 도움 |
| **관계** | 서비스 제공자 ↔ 수혜자 | 동행자 ↔ 동행자 |
| **분위기** | 공식적, 절차적 | 캐주얼, 따뜻함 |
| **보상** | 의무/업무 | 재미 + 의미 + 보상 |

</details>

---

## 🏗️ System Structure

### 전체 시스템 구성도

```mermaid
graph TB
    subgraph "Frontend Layer"
        A[Android App<br/>Kotlin]
        B[iOS App<br/>Swift]
    end
    
    subgraph "API Gateway"
        C[Nginx<br/>Reverse Proxy]
    end
    
    subgraph "Backend Layer"
        D[Node.js Server<br/>Express + TypeScript]
        E[Socket.io Server<br/>Real-time Communication]
    end
    
    subgraph "Database Layer"
        F[(Cloud SQL<br/>MySQL 8.0)]
        G[(Memorystore<br/>Redis 7.0)]
    end
    
    subgraph "Storage Layer"
        H[Cloud Storage<br/>Image Files]
    end
    
    subgraph "External Services"
        I[Firebase FCM<br/>Push Notification]
        J[카카오맵 API<br/>Map Display]
        K[SK T map API<br/>Route Navigation]
        L[PASS API<br/>Identity Verification]
    end
    
    A -->|HTTPS| C
    B -->|HTTPS| C
    C -->|Proxy| D
    C -->|WebSocket| E
    
    D --> F
    D --> G
    E --> G
    D --> H
    D --> I
    
    A -.->|Direct Call| J
    A -.->|Direct Call| K
    B -.->|Direct Call| J
    B -.->|Direct Call| K
    
    D --> L
    
    style A fill:#4CAF50
    style B fill:#4CAF50
    style D fill:#2196F3
    style E fill:#2196F3
    style F fill:#FF9800
    style G fill:#FF9800
```

### 주요 컴포넌트

#### Frontend (Mobile App)
- **Android/iOS 네이티브 앱**
- 실시간 위치 기반 지도 UI
- 즉시 매칭 및 알림 시스템
- 카카오맵, SK T map 직접 연동

#### Backend (API Server)
- **Node.js 20 LTS + Express.js**
- TypeScript 기반 타입 안전성
- RESTful API 설계
- Socket.io 실시간 통신
- Prisma ORM을 통한 데이터 관리

#### Database
- **Cloud SQL (MySQL 8.0)**: 주요 데이터 저장
- **Memorystore (Redis 7.0)**: 캐싱, 세션, 실시간 위치
- Prisma ORM을 통한 타입 안전 쿼리

#### Infrastructure (GCP)
- **Compute Engine**: Node.js 서버 호스팅
- **Cloud Load Balancing**: 트래픽 분산
- **Cloud Storage**: 이미지 파일 저장
- **Cloud Monitoring**: 시스템 모니터링

#### External Services
- **Firebase FCM**: 푸시 알림
- **카카오맵 API**: 지도 표시 (프론트엔드)
- **SK T map API**: 경로 탐색 (프론트엔드)
- **PASS API**: 본인 인증

---

## 🎯 사용자 플로우

### 전체 사용자 여정

```mermaid
flowchart TD
    Start([앱 시작]) --> Login{로그인<br/>여부}
    
    Login -->|No| SignUp[회원가입]
    Login -->|Yes| RoleSelect{역할 선택}
    
    SignUp --> Verify[본인 인증<br/>PASS]
    Verify --> Profile[프로필 설정<br/>- 사진<br/>- 자기소개<br/>- 사용자 유형]
    Profile --> RoleSelect
    
    RoleSelect -->|요청자| RequesterMain[요청자<br/>메인 화면]
    RoleSelect -->|도우미| HelperMain[도우미<br/>메인 화면]
    
    %% 요청자 플로우
    RequesterMain --> ReqMap[지도에서<br/>주변 확인]
    ReqMap --> CreateReq[동행 요청<br/>생성]
    CreateReq --> ReqInput[목적지 &<br/>소요시간 입력]
    ReqInput --> Waiting[매칭 대기]
    Waiting --> MatchCheck1{매칭<br/>성공?}
    MatchCheck1 -->|No| Waiting
    MatchCheck1 -->|Yes| ReqLocation[도우미 실시간<br/>위치 확인]
    
    %% 도우미 플로우
    HelperMain --> HelperMap[지도에서<br/>주변 요청 확인]
    HelperMap --> Notification[푸시 알림<br/>수신]
    Notification --> ViewReq[요청 상세<br/>확인]
    ViewReq --> AcceptCheck{수락<br/>여부}
    AcceptCheck -->|No| HelperMap
    AcceptCheck -->|Yes| Accept[요청 수락]
    Accept --> HelperLocation[요청자 실시간<br/>위치 확인]
    
    %% 공통 동행 플로우
    ReqLocation --> Meet[만남]
    HelperLocation --> Meet
    Meet --> QRStart[QR 인증<br/>동행 시작]
    QRStart --> Companion[동행 진행<br/>- 실시간 위치 공유<br/>- 채팅<br/>- SOS 버튼]
    Companion --> Arrive[목적지 도착]
    Arrive --> QREnd[QR 인증<br/>동행 완료]
    QREnd --> Review[상호 평가<br/>- 별점<br/>- 후기]
    Review --> Reward[보상 지급<br/>- 동행 지수 ↑<br/>- 배지 획득<br/>- 포인트 적립]
    Reward --> End([종료])
    
    style Start fill:#4CAF50
    style End fill:#4CAF50
    style SignUp fill:#2196F3
    style Verify fill:#2196F3
    style Profile fill:#2196F3
    style Companion fill:#FF9800
    style Review fill:#9C27B0
    style Reward fill:#FFD700
```

### 주요 단계 설명

**1단계: 가입 및 인증**
- 소셜 로그인 또는 일반 회원가입
- PASS를 통한 본인 인증
- 프로필 설정 (사진, 자기소개, 사용자 유형)

**2단계: 역할 선택**
- 요청자 (휠체어 사용자): 도움 요청
- 도우미 (이웃): 도움 제공

**3단계: 매칭**
- 요청자: 동행 요청 생성 → 대기
- 도우미: 주변 요청 확인 → 수락

**4단계: 동행**
- 실시간 위치 공유로 만남
- QR 인증으로 시작 확인
- 목적지까지 함께 이동

**5단계: 완료 및 평가**
- QR 인증으로 완료 확인
- 상호 별점 및 후기 작성
- 동행 지수 상승 및 보상 획득

---

## 🛠️ Tech Stack

<div align="center">

### Backend

![Node.js](https://img.shields.io/badge/Node.js_20_LTS-339933?style=for-the-badge&logo=node.js&logoColor=white)
![Express](https://img.shields.io/badge/Express_4.18-000000?style=for-the-badge&logo=express&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript_5.x-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Socket.io](https://img.shields.io/badge/Socket.io_4.x-010101?style=for-the-badge&logo=socket.io&logoColor=white)

### Database & Cache

![MySQL](https://img.shields.io/badge/MySQL_8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Prisma](https://img.shields.io/badge/Prisma_5.x-2D3748?style=for-the-badge&logo=prisma&logoColor=white)
![Redis](https://img.shields.io/badge/Redis_7.0-DC382D?style=for-the-badge&logo=redis&logoColor=white)

### Infrastructure (GCP)

![Google Cloud](https://img.shields.io/badge/Google_Cloud-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white)
![Compute Engine](https://img.shields.io/badge/Compute_Engine-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white)
![Cloud SQL](https://img.shields.io/badge/Cloud_SQL-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white)

### External Services

![Firebase](https://img.shields.io/badge/Firebase_FCM-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![Kakao](https://img.shields.io/badge/카카오맵_API-FFCD00?style=for-the-badge&logo=kakao&logoColor=black)

### Frontend

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-000000?style=for-the-badge&logo=apple&logoColor=white)
![Swift](https://img.shields.io/badge/Swift-FA7343?style=for-the-badge&logo=swift&logoColor=white)

### DevOps

![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)

</div>

### 기술 스택 상세

<details>
<summary><strong>Backend 상세</strong></summary>

- **Node.js 20 LTS**: JavaScript 런타임, 비동기 I/O, 실시간 처리
- **Express.js 4.18**: 웹 프레임워크, RESTful API
- **TypeScript 5.x**: 타입 안전성, 버그 방지
- **Socket.io 4.x**: 실시간 양방향 통신 (위치 공유, 알림)
- **Prisma ORM 5.x**: 타입 안전 ORM, 자동 마이그레이션
- **JWT**: Access Token + Refresh Token 인증

</details>

<details>
<summary><strong>Database & Cache 상세</strong></summary>

- **Cloud SQL (MySQL 8.0)**: 관계형 데이터베이스, 트랜잭션 지원
- **Memorystore (Redis 7.0)**: 
  - JWT 토큰 저장
  - 실시간 위치 캐싱
  - Redis Geo로 주변 검색
  - 세션 관리

</details>

<details>
<summary><strong>Infrastructure 상세</strong></summary>

- **Compute Engine**: Node.js 서버 호스팅
- **Cloud SQL**: 관리형 MySQL (자동 백업, 고가용성)
- **Memorystore**: 관리형 Redis (자동 페일오버)
- **Cloud Storage**: 이미지 파일 저장 (프로필, 인증 사진)
- **Cloud Load Balancing**: 트래픽 분산, SSL/TLS
- **Cloud Monitoring**: 로그, 메트릭, 알림

</details>

---

## ✨ Features

### 1️⃣ 실시간 위치 기반 매칭

**GPS 기반 주변 매칭**
- 사용자의 현재 위치를 중심으로 주변 도우미 실시간 표시
- Redis Geo를 활용한 고속 거리 계산
- 거리 기반 알림 우선순위 설정
- 지도 위에서 직관적인 위치 확인

**즉시 동행 요청**
- 목적지와 예상 소요 시간 입력
- 주변 도우미에게 푸시 알림 전송 (Firebase FCM)
- Socket.io를 통한 실시간 매칭 상태 업데이트
- 평균 3분 이내 빠른 매칭 완료

### 2️⃣ 신뢰 기반 커뮤니티

**본인 인증 시스템**
- PASS 앱을 통한 휴대폰 본인 인증
- 신분증 OCR 인증 (선택)
- 프로필 사진 등록 의무화
- 인증된 사용자만 서비스 이용 가능

**동행 지수 (Trust Score)**
- 동행 횟수와 후기 기반 신뢰도 점수
- 상호 별점 평가 시스템 (1-5점)
- 활동 배지 획득 (예: '첫걸음 천사', '동네 지킴이')
- 프로필에 신뢰도 시각적 표시

**안전 장치**
- 실시간 동행 경로 공유 ('안심 트래킹')
- 가족/친구에게 경로 공유 기능
- SOS 긴급 신고 버튼
- 사용자 신고 및 차단 기능
- QR 코드 기반 동행 시작/종료 인증

### 3️⃣ 보상 및 게이미피케이션

**공식 봉사 시간 인정**
- QR 인증을 통한 정확한 동행 시간 기록
- 1365 자원봉사포털 연동 (향후)
- 학생 및 직장인 봉사 시간 활용
- 자동 봉사 확인서 발급

**지역 파트너십**
- '착한 가게' 포인트 적립
- 지역 상점에서 할인 혜택
- 커뮤니티 기여 보상
- 지역 경제 활성화

**재미 요소**
- 동행 랭킹 시스템 (주간, 월간)
- 월별 배지 수집 (50+ 종류)
- 이달의 동행왕 선정 및 특별 보상
- 레벨 시스템 (초보 동행자 → 마스터 동행자)

### 4️⃣ 커뮤니티 및 소통

**1:1 실시간 채팅**
- Socket.io 기반 실시간 채팅
- 위치 공유 및 만남 조율
- 감사 메시지 및 피드백 교환
- 이미지 전송 지원

**동행 기록**
- 나의 동행 히스토리 (요청자/도우미)
- 받은 감사 카드 모음
- 동행 사진 및 추억 공유
- 월별/연도별 통계

---

## 🗄️ Database Schema

### ERD (Entity Relationship Diagram)

```mermaid
erDiagram
    USERS ||--o{ COMPANION_REQUESTS : creates
    USERS ||--o{ COMPANION_SESSIONS : "participates as requester"
    USERS ||--o{ COMPANION_SESSIONS : "participates as helper"
    USERS ||--o{ REVIEWS : "writes as reviewer"
    USERS ||--o{ REVIEWS : "receives as reviewee"
    USERS ||--o{ BADGES : earns
    USERS ||--o{ USER_LOCATIONS : has
    USERS ||--o{ REFRESH_TOKENS : has
    
    COMPANION_REQUESTS ||--|| COMPANION_SESSIONS : "results in"
    COMPANION_SESSIONS ||--o{ REVIEWS : receives
    COMPANION_SESSIONS ||--o{ CHAT_MESSAGES : contains
    
    USERS {
        bigint id PK
        string email UK
        string username
        string phone UK
        string password_hash
        string profile_image_url
        enum user_type "REQUESTER, HELPER, BOTH"
        int trust_score
        boolean is_verified
        boolean is_active
        timestamp created_at
        timestamp updated_at
    }
    
    USER_LOCATIONS {
        bigint id PK
        bigint user_id FK
        decimal latitude
        decimal longitude
        timestamp updated_at
    }
    
    COMPANION_REQUESTS {
        bigint id PK
        bigint requester_id FK
        decimal start_latitude
        decimal start_longitude
        string start_address
        decimal dest_latitude
        decimal dest_longitude
        string dest_address
        int estimated_minutes
        enum status "PENDING, MATCHED, IN_PROGRESS, COMPLETED, CANCELLED"
        timestamp created_at
        timestamp updated_at
    }
    
    COMPANION_SESSIONS {
        bigint id PK
        bigint request_id FK
        bigint requester_id FK
        bigint helper_id FK
        timestamp start_time
        timestamp end_time
        int actual_minutes
        json route_data
        enum status "PENDING, STARTED, COMPLETED, CANCELLED"
        timestamp created_at
    }
    
    REVIEWS {
        bigint id PK
        bigint session_id FK
        bigint reviewer_id FK
        bigint reviewee_id FK
        int rating "1-5"
        text comment
        timestamp created_at
    }
    
    BADGES {
        bigint id PK
        bigint user_id FK
        string badge_type
        string badge_name
        string description
        timestamp earned_at
    }
    
    CHAT_MESSAGES {
        bigint id PK
        bigint session_id FK
        bigint sender_id FK
        text message
        enum message_type "TEXT, IMAGE, LOCATION"
        timestamp created_at
    }
    
    REFRESH_TOKENS {
        bigint id PK
        bigint user_id FK
        string token UK
        timestamp expires_at
        timestamp created_at
    }
```

### 주요 테이블 설명

<details>
<summary><strong>users (사용자)</strong></summary>

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(100) NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    profile_image_url VARCHAR(500),
    user_type ENUM('REQUESTER', 'HELPER', 'BOTH') NOT NULL,
    trust_score INT DEFAULT 100,
    is_verified BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

**주요 필드:**
- `user_type`: 요청자, 도우미, 또는 둘 다 가능
- `trust_score`: 동행 지수 (0-200)
- `is_verified`: PASS 본인인증 여부

</details>

<details>
<summary><strong>companion_requests (동행 요청)</strong></summary>

```sql
CREATE TABLE companion_requests (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    requester_id BIGINT NOT NULL,
    start_latitude DECIMAL(10, 8) NOT NULL,
    start_longitude DECIMAL(11, 8) NOT NULL,
    start_address VARCHAR(255),
    dest_latitude DECIMAL(10, 8) NOT NULL,
    dest_longitude DECIMAL(11, 8) NOT NULL,
    dest_address VARCHAR(255),
    estimated_minutes INT NOT NULL,
    status ENUM('PENDING', 'MATCHED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (requester_id) REFERENCES users(id)
);
```

**주요 필드:**
- 출발지/목적지 좌표 및 주소
- `estimated_minutes`: 예상 소요 시간
- `status`: 요청 상태 추적

</details>

<details>
<summary><strong>companion_sessions (동행 세션)</strong></summary>

```sql
CREATE TABLE companion_sessions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    request_id BIGINT NOT NULL,
    requester_id BIGINT NOT NULL,
    helper_id BIGINT NOT NULL,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    actual_minutes INT,
    route_data JSON,
    status ENUM('PENDING', 'STARTED', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (request_id) REFERENCES companion_requests(id),
    FOREIGN KEY (requester_id) REFERENCES users(id),
    FOREIGN KEY (helper_id) REFERENCES users(id)
);
```

**주요 필드:**
- QR 인증으로 `start_time`, `end_time` 기록
- `actual_minutes`: 실제 소요 시간 (봉사 시간 계산)
- `route_data`: 이동 경로 JSON

</details>

---

## 📡 API Documentation

### API 개요

모든 API는 RESTful 설계 원칙을 따르며, JSON 형식으로 데이터를 주고받습니다.

**Base URL:** `https://api.companion-app.com/api/v1`

**인증 방식:** JWT (JSON Web Token)
- Access Token: Authorization 헤더 (`Bearer {token}`)
- Refresh Token: HTTP-Only 쿠키

### 주요 엔드포인트

#### 🔐 인증 API

<details>
<summary><strong>POST /auth/signup</strong> - 회원가입</summary>

```http
POST /api/v1/auth/signup
Content-Type: application/json

{
  "email": "user@example.com",
  "username": "홍길동",
  "password": "securePassword123!",
  "phone": "01012345678",
  "user_type": "BOTH"
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "data": {
    "user": {
      "id": 1,
      "email": "user@example.com",
      "username": "홍길동",
      "user_type": "BOTH"
    },
    "tokens": {
      "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
      "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
  }
}
```

</details>

<details>
<summary><strong>POST /auth/login</strong> - 로그인</summary>

```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123!"
}
```

</details>

<details>
<summary><strong>POST /auth/verify</strong> - 본인인증 (PASS)</summary>

```http
POST /api/v1/auth/verify
Authorization: Bearer {access_token}
Content-Type: application/json

{
  "verification_code": "ABC123XYZ",
  "phone": "01012345678"
}
```

</details>

#### 🚶 동행 요청 API

<details>
<summary><strong>POST /companion/request</strong> - 동행 요청 생성</summary>

```http
POST /api/v1/companion/request
Authorization: Bearer {access_token}
Content-Type: application/json

{
  "start_latitude": 35.1595,
  "start_longitude": 126.8526,
  "start_address": "광주광역시 동구 금남로 245",
  "dest_latitude": 35.1601,
  "dest_longitude": 126.8538,
  "dest_address": "광주광역시 동구 충장로 47",
  "estimated_minutes": 15
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "data": {
    "request_id": 123,
    "status": "PENDING",
    "estimated_minutes": 15,
    "created_at": "2025-11-04T10:30:00Z"
  }
}
```

</details>

<details>
<summary><strong>GET /companion/nearby</strong> - 주변 요청 조회</summary>

```http
GET /api/v1/companion/nearby?latitude=35.1595&longitude=126.8526&radius=1000
Authorization: Bearer {access_token}
```

**Query Parameters:**
- `latitude`: 현재 위도
- `longitude`: 현재 경도
- `radius`: 검색 반경 (미터, 기본값: 1000)

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "requests": [
      {
        "id": 123,
        "requester": {
          "id": 45,
          "username": "김철수",
          "profile_image_url": "https://...",
          "trust_score": 150
        },
        "start_address": "광주광역시 동구 금남로 245",
        "dest_address": "광주광역시 동구 충장로 47",
        "distance": 250,
        "estimated_minutes": 15,
        "created_at": "2025-11-04T10:30:00Z"
      }
    ]
  }
}
```

</details>

<details>
<summary><strong>POST /companion/accept</strong> - 동행 요청 수락</summary>

```http
POST /api/v1/companion/accept
Authorization: Bearer {access_token}
Content-Type: application/json

{
  "request_id": 123
}
```

</details>

<details>
<summary><strong>POST /companion/start</strong> - 동행 시작 (QR 인증)</summary>

```http
POST /api/v1/companion/start
Authorization: Bearer {access_token}
Content-Type: application/json

{
  "session_id": 456,
  "qr_code": "SESSION_456_START_ABC123"
}
```

</details>

<details>
<summary><strong>POST /companion/complete</strong> - 동행 완료 (QR 인증)</summary>

```http
POST /api/v1/companion/complete
Authorization: Bearer {access_token}
Content-Type: application/json

{
  "session_id": 456,
  "qr_code": "SESSION_456_END_XYZ789"
}
```

</details>

#### 👤 사용자 API

<details>
<summary><strong>GET /users/profile</strong> - 프로필 조회</summary>

```http
GET /api/v1/users/profile
Authorization: Bearer {access_token}
```

</details>

<details>
<summary><strong>PUT /users/profile</strong> - 프로필 수정</summary>

```http
PUT /api/v1/users/profile
Authorization: Bearer {access_token}
Content-Type: multipart/form-data

{
  "username": "홍길동",
  "profile_image": [File],
  "bio": "안녕하세요! 동행을 좋아하는 홍길동입니다."
}
```

</details>

<details>
<summary><strong>GET /users/history</strong> - 동행 기록 조회</summary>

```http
GET /api/v1/users/history?page=1&limit=20&role=helper
Authorization: Bearer {access_token}
```

**Query Parameters:**
- `page`: 페이지 번호 (기본값: 1)
- `limit`: 페이지당 개수 (기본값: 20)
- `role`: 역할 필터 (`requester`, `helper`, 기본값: all)

</details>

#### ⭐ 평가 API

<details>
<summary><strong>POST /reviews</strong> - 평가 작성</summary>

```http
POST /api/v1/reviews
Authorization: Bearer {access_token}
Content-Type: application/json

{
  "session_id": 456,
  "rating": 5,
  "comment": "정말 친절하게 도와주셔서 감사합니다!"
}
```

</details>

<details>
<summary><strong>GET /reviews/:userId</strong> - 사용자 평가 조회</summary>

```http
GET /api/v1/reviews/45?page=1&limit=10
Authorization: Bearer {access_token}
```

</details>

### WebSocket (Socket.io) 이벤트

#### 실시간 위치 공유

```javascript
// 위치 전송
socket.emit('location:update', {
  latitude: 35.1595,
  longitude: 126.8526
});

// 위치 수신
socket.on('location:updated', (data) => {
  console.log('상대방 위치:', data);
});
```

#### 실시간 채팅

```javascript
// 메시지 전송
socket.emit('chat:message', {
  session_id: 456,
  message: '5분 후에 도착해요!'
});

// 메시지 수신
socket.on('chat:received', (data) => {
  console.log('새 메시지:', data);
});
```

#### 매칭 알림

```javascript
// 매칭 완료 알림 수신
socket.on('match:completed', (data) => {
  console.log('매칭 성공!', data);
});
```

> 📚 **전체 API 문서:** [Swagger UI](https://api.companion-app.com/docs)

---

## 🚀 How to Start

### 사전 요구사항

- Node.js 20 LTS 이상
- MySQL 8.0 이상
- Redis 7.0 이상
- Docker & Docker Compose (선택)
- Google Cloud Platform 계정

### 환경 설정

#### 1. 프로젝트 클론

```bash
git clone https://github.com/your-org/companion-backend.git
cd companion-backend
```

#### 2. 환경 변수 설정

```bash
# .env.example 파일을 .env로 복사
cp .env.example .env

# .env 파일 수정
vi .env
```

**.env 파일 예시:**

```env
# 서버 설정
NODE_ENV=development
PORT=3000
API_VERSION=v1

# 데이터베이스 (Cloud SQL)
DATABASE_URL="mysql://user:password@localhost:3306/companion"

# Redis (Memorystore)
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=

# JWT
JWT_SECRET=your-super-secret-jwt-key-change-this
JWT_ACCESS_EXPIRE=15m
JWT_REFRESH_EXPIRE=7d

# Firebase FCM
FIREBASE_PROJECT_ID=companion-app-12345
FIREBASE_PRIVATE_KEY="-----BEGIN PRIVATE KEY-----\n...\n-----END PRIVATE KEY-----\n"
FIREBASE_CLIENT_EMAIL=firebase-adminsdk@companion-app-12345.iam.gserviceaccount.com

# Google Cloud Storage
GCS_BUCKET_NAME=companion-images
GCS_PROJECT_ID=companion-app-12345

# 외부 API
PASS_API_KEY=your-pass-api-key
PASS_API_SECRET=your-pass-api-secret

# 프론트엔드 URL (CORS)
FRONTEND_URL=http://localhost:3001

# 로그
LOG_LEVEL=debug
```

#### 3. 의존성 설치

```bash
# npm 사용
npm install

# 또는 yarn 사용
yarn install
```

#### 4. 데이터베이스 설정

**방법 1: Docker Compose로 로컬 환경 구축 (추천)**

```bash
# MySQL + Redis 컨테이너 실행
docker-compose up -d

# 데이터베이스 마이그레이션
npx prisma migrate dev

# 초기 데이터 시딩 (선택)
npx prisma db seed
```

**방법 2: 직접 MySQL 설치**

```bash
# MySQL 8.0 설치 및 데이터베이스 생성
mysql -u root -p
CREATE DATABASE companion CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# Prisma 마이그레이션
npx prisma migrate dev
```

#### 5. 서버 실행

**개발 환경:**

```bash
# nodemon으로 자동 재시작
npm run dev

# 또는 ts-node로 직접 실행
npm run start:dev
```

**프로덕션 환경:**

```bash
# TypeScript 빌드
npm run build

# 빌드된 코드 실행
npm start

# 또는 PM2로 실행 (프로세스 관리)
pm2 start dist/index.js --name companion-api
```

서버가 실행되면 다음 URL에서 확인할 수 있습니다:
- API: `http://localhost:3000/api/v1`
- Health Check: `http://localhost:3000/health`
- API Docs: `http://localhost:3000/docs`

### Docker를 사용한 전체 스택 실행

```bash
# 전체 스택 실행 (MySQL + Redis + API Server)
docker-compose up -d

# 로그 확인
docker-compose logs -f api

# 중지
docker-compose down
```

### GCP 배포

#### 1. GCP 프로젝트 설정

```bash
# GCP CLI 설치
# https://cloud.google.com/sdk/docs/install

# GCP 로그인
gcloud auth login

# 프로젝트 생성
gcloud projects create companion-app-12345

# 프로젝트 설정
gcloud config set project companion-app-12345

# API 활성화
gcloud services enable compute.googleapis.com
gcloud services enable sqladmin.googleapis.com
gcloud services enable redis.googleapis.com
```

#### 2. Cloud SQL 인스턴스 생성

```bash
# MySQL 인스턴스 생성
gcloud sql instances create companion-db \
  --database-version=MYSQL_8_0 \
  --tier=db-f1-micro \
  --region=asia-northeast3

# 데이터베이스 생성
gcloud sql databases create companion \
  --instance=companion-db

# 사용자 생성
gcloud sql users create companion-user \
  --instance=companion-db \
  --password=your-secure-password
```

#### 3. Compute Engine VM 생성 및 배포

```bash
# VM 인스턴스 생성
gcloud compute instances create companion-api \
  --zone=asia-northeast3-a \
  --machine-type=e2-micro \
  --image-family=ubuntu-2204-lts \
  --image-project=ubuntu-os-cloud

# SSH 접속
gcloud compute ssh companion-api --zone=asia-northeast3-a

# VM 내에서 Node.js 설치 및 앱 배포
# (위의 환경 설정 단계 반복)
---

## 🤝 Contributing

### 참여 방법

'동행' 프로젝트에 기여하고 싶으신가요? 다양한 방법으로 참여하실 수 있습니다!

#### 개발자 👨‍💻👩‍💻
- 코드 기여 (Pull Request)
- 버그 리포트 (Issue)
- 문서 개선
- 테스트 코드 작성

#### 디자이너 🎨
- UI/UX 개선 제안
- 아이콘 및 일러스트 제작
- 브랜딩 작업

#### 휠체어 사용자 ♿
- 사용성 피드백
- 기능 제안
- 베타 테스트 참여
- 사용 후기 공유

#### 지역 상점 🏪
- 파트너십 문의
- 지역 커뮤니티 연결
- 할인 혜택 제공

### 개발 가이드라인

#### 1. 이슈 생성

먼저 [GitHub Issues](https://github.com/your-org/companion-backend/issues)에서 논의를 시작하세요.

```
제목: [기능] 실시간 채팅 이미지 전송 기능 추가
내용:
- 현재 상황: 텍스트만 전송 가능
- 제안 사항: 이미지 파일 전송 기능 추가
- 기대 효과: 사용자 간 소통 향상
```

#### 2. 브랜치 전략

```bash
# develop 브랜치에서 작업 시작
git checkout develop
git pull origin develop

# feature 브랜치 생성
git checkout -b feature/chat-image-upload

# 작업 후 커밋
git add .
git commit -m "feat: 채팅 이미지 업로드 기능 추가"

# 원격 저장소에 푸시
git push origin feature/chat-image-upload
```

#### 3. 커밋 메시지 규칙

[Conventional Commits](https://www.conventionalcommits.org/) 규칙을 따릅니다.

```
feat: 새로운 기능 추가
fix: 버그 수정
docs: 문서 수정
style: 코드 포맷팅 (기능 변경 없음)
refactor: 코드 리팩토링
test: 테스트 코드 추가/수정
chore: 빌드, 패키지 등 기타 작업

예시:
feat: 실시간 채팅 이미지 전송 기능 추가
fix: 동행 매칭 시 위치 오류 수정
docs: README에 설치 가이드 추가
```

#### 4. Pull Request

- PR 템플릿을 작성합니다
- 관련 이슈를 연결합니다 (`Closes #123`)
- 테스트 코드를 포함합니다
- 스크린샷이나 데모 영상을 첨부합니다 (UI 관련)

#### 5. 코드 리뷰

- 최소 1명 이상의 승인 필요
- CI/CD 체크 통과 필수
- 코드 스타일 준수 (ESLint, Prettier)

### 로컬 개발 환경 설정

```bash
# 저장소 포크 후 클론
git clone https://github.com/your-username/companion-backend.git
cd companion-backend

# 의존성 설치
npm install

# pre-commit hook 설정 (Husky)
npm run prepare

# 개발 서버 실행
npm run dev
```

### 테스트

```bash
# 전체 테스트 실행
npm test

# 특정 파일 테스트
npm test -- auth.test.ts

# 커버리지 확인
npm run test:coverage

# E2E 테스트
npm run test:e2e
```

---

## 📄 License

이 프로젝트는 **MIT 라이선스**를 따릅니다.

```
MIT License

Copyright (c) 2025 Companion Team

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

자세한 내용은 [LICENSE](LICENSE) 파일을 참고하세요.

---

<div align="center">

## ⭐ 함께 만드는 배리어프리 세상

**"기술이 만드는 따뜻한 연결, 동행"**

*Made with ❤️ for a barrier-free world*

<br/>

[![GitHub stars](https://img.shields.io/github/stars/your-org/companion-backend?style=social)](https://github.com/your-org/companion-backend)
[![GitHub forks](https://img.shields.io/github/forks/your-org/companion-backend?style=social)](https://github.com/your-org/companion-backend/fork)
[![GitHub issues](https://img.shields.io/github/issues/your-org/companion-backend)](https://github.com/your-org/companion-backend/issues)
[![GitHub license](https://img.shields.io/github/license/your-org/companion-backend)](LICENSE)

<br/>

**이 프로젝트가 마음에 드셨다면 ⭐️ Star를 눌러주세요!**

<<<<<<< HEAD
</div>
=======
</div>
>>>>>>> upstream/main
