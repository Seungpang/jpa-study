

### JPA 서브 쿼리 한계
+ JPA는 WHERE, HAVING절에서만 서브 쿼리 사용 가능
+ SELECT절도 가능 (하이버네이트에서 지원)
+ **FROM절의 서브 쿼리는 현재 JPQL에서 불가능**
    + **조인으로 풀 수 있으면 풀어서 해결**
    + 다른 방법들 ... 네이티브sql, 애플리케이션으로 가져와서 조작, 쿼리를 분해해서 날리거나

### JPQL 타입 표현
+ 문자: 'HELLO', 'She''s'
+ 숫자: 10L(Long), 10D(Double), 10F(Float)
+ Boolean: TRUE, FALSE
+ ENUM: jpabook.MemberType.Admin
+ 엔티티 타입: TYPE(m) = Member(상속 관계에서 사용)

### JPQL 기타
+ SQL과 문법이 같은 식
+ EXISTS, IN
+ AND, OR, NOT
+ =, >, >=, <, <=, <>
+ BETWEEN, LIKE, IS NULL

### 조건식 - CASE 식

### JPQL 기본 함수
+ CONCAT
+ SUBSTRING
+ TRIM
+ LOWER, UPPER
+ LENGTH
+ LOCATE
+ ABS, SQRT, MOD
+ SIZE, INDEX(JPA 용도)
