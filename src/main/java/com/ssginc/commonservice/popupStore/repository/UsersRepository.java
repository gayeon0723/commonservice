package com.ssginc.commonservice.popupStore.repository;

import com.ssginc.commonservice.popupStore.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 사용자 엔티티에 대한 데이터 액세스 계층 (Repository)
 * Spring Data JPA를 사용하여 데이터베이스와 상호작용하는 인터페이스
 */
@Repository // 해당 인터페이스가 Spring의 Repository 컴포넌트임을 명시
public interface UsersRepository extends JpaRepository<Users, Integer> {
    // JpaRepository를 상속받아 기본적인 CRUD 기능을 자동으로 제공
    // JpaRepository<엔티티 타입, 기본 키 타입>
    // 별도의 메서드를 정의하지 않아도 findAll(), findById(), save(), delete() 등의 기능을 사용할 수 있음

    /**
     * 사용자 이메일을 기준으로 해당 사용자 정보를 조회하는 메서드
     * @param userEmail 사용자의 이메일
     * @return 이메일에 해당하는 Users 엔티티 객체 (없으면 null 반환)
     */
    @Query("""
        SELECT u FROM Users u
        WHERE u.userEmail = :userEmail
    """)
    Users login(String userEmail); // 특정 이메일을 가진 사용자 조회



    // 회원가입 메서드: JpaRepository에서 제공하는 save() 메서드를 활용하여 별도 정의 필요 없음.
    // - save(entity): 사용자 정보 저장 (회원가입 또는 업데이트)
    //Users createUser(Users users); --> save() 메서드 사용
}
