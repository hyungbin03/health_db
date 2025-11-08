-- 1️⃣ 스키마 선택
CREATE DATABASE IF NOT EXISTS healthapp DEFAULT CHARACTER SET utf8mb4;
USE healthapp;

-- 2️⃣ 기존 테이블 삭제 (중복 방지)
DROP TABLE IF EXISTS health_data;
DROP TABLE IF EXISTS diet;
DROP TABLE IF EXISTS exercise;
DROP TABLE IF EXISTS users;

-- 3️⃣ users 테이블 생성
CREATE TABLE users (
  user_id     INT AUTO_INCREMENT PRIMARY KEY,
  username    VARCHAR(50)  NOT NULL,
  email       VARCHAR(100) NOT NULL UNIQUE,
  password    VARCHAR(255) NOT NULL,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 4️⃣ exercise 테이블 생성
CREATE TABLE exercise (
  exercise_id     INT AUTO_INCREMENT PRIMARY KEY,
  user_id         INT NOT NULL,
  exercise_date   DATE NOT NULL,
  exercise_type   VARCHAR(50),
  duration        INT,
  calories_burned FLOAT,
  CONSTRAINT fk_exercise_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 5️⃣ diet 테이블 생성
CREATE TABLE diet (
  diet_id    INT AUTO_INCREMENT PRIMARY KEY,
  user_id    INT NOT NULL,
  meal_date  DATE NOT NULL,
  meal_type  ENUM('아침','점심','저녁','간식'),
  food_name  VARCHAR(100),
  calories   FLOAT,
  memo       TEXT,
  CONSTRAINT fk_diet_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 6️⃣ health_data 테이블 생성
CREATE TABLE health_data (
  health_id      INT AUTO_INCREMENT PRIMARY KEY,
  user_id        INT NOT NULL,
  record_date    DATE NOT NULL,
  weight         FLOAT,
  blood_pressure VARCHAR(20),
  heart_rate     INT,
  CONSTRAINT fk_health_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO users (username, email, password) VALUES
('형빈', 'hyungbin@test.com', '1234'),
('서아',  'seoa@test.com',     '1234'),
('영주',  'youngju@test.com',  '1234'),
('성환',  'sunghwan@test.com', '1234')
ON DUPLICATE KEY UPDATE

email = VALUES(email);

INSERT INTO exercise (user_id, exercise_date, exercise_type, duration, calories_burned) VALUES
(1,'2025-10-20','러닝',30,250),
(2,'2025-10-20','요가',45,180),
(3,'2025-10-20','웨이트',60,400),
(4,'2025-10-20','자전거',40,320)
ON DUPLICATE KEY UPDATE exercise_date = VALUES(exercise_date);

INSERT INTO diet (user_id, meal_date, meal_type, food_name, calories, memo) VALUES
(1,'2025-10-20','아침','계란후라이',180,'단백질 보충'),
(2,'2025-10-20','점심','샐러드',250,'저탄수화물 식단'),
(3,'2025-10-20','저녁','닭가슴살',300,'운동 후 단백질 보충'),
(4,'2025-10-20','간식','바나나',90,'운동 전 간식')
ON DUPLICATE KEY UPDATE meal_date = VALUES(meal_date);

INSERT INTO health_data (user_id, record_date, weight, blood_pressure, heart_rate) VALUES
(1,'2025-10-20',72.5,'120/80',72),
(2,'2025-10-20',58.2,'110/70',68),
(3,'2025-10-20',65.7,'125/82',75),
(4,'2025-10-20',80.1,'130/85',70)
ON DUPLICATE KEY UPDATE record_date = VALUES(record_date);

USE healthapp;

SHOW INDEX FROM diet;

ALTER TABLE diet DROP INDEX uq_diet_user_date;
ALTER TABLE diet DROP INDEX uq_diet_user_date_meal;

CREATE INDEX idx_diet_user_date        ON diet(user_id, meal_date);
CREATE INDEX idx_diet_user_date_meal   ON diet(user_id, meal_date, meal_type);

SELECT
    u.user_id,
    u.username,
    e.exercise_date,
    e.exercise_type,
    e.duration,
    e.calories_burned,
    h.record_date,
    h.weight,
    h.blood_pressure,
    h.heart_rate,
    d.meal_date,
    d.meal_type,
    d.food_name,
    d.calories AS meal_calories,
    d.memo
FROM users u
LEFT JOIN exercise    e ON u.user_id = e.user_id
LEFT JOIN health_data h ON u.user_id = h.user_id
LEFT JOIN diet        d ON u.user_id = d.user_id
ORDER BY u.user_id, e.exercise_date, d.meal_date;

SELECT * FROM users;

SELECT * FROM health_data;

SELECT * FROM diet;

SELECT * FROM exercise;






