package com.example.firstproject.repository;

import com.example.firstproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //이메일로 회원 정보 조회 (select * from member_table where member_email = ?)

    @Query(value = "SELECT * FROM member_table WHERE member_email = ?1", nativeQuery = true)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);

}
