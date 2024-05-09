package com.example.board.repository;

import com.example.board.domain.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    //지연 로딩에 관해서 더 찾아보기
    //@EntityGraph는 Data JPA에서 fect 조인을 어노테이션으로 사용할 수 있도록 만들어 준 기능이다
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.social = false")
    Optional<Member> getWithRoles(String mid);


    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);


    @Modifying
    @Transactional
    @Query("update Member m set m.mpw =:mpw where m.mid = :mid ")
    void updatePassword(@Param("mpw") String password, @Param("mid") String mid);



}