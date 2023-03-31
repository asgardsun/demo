package com.example.demo.sercvice;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberReopository;
import com.example.demo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberReopository memberRepository;


    public MemberService(MemberReopository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    회원가입
     */
    public Long join(Member member){

        validateDuplicateMember(member); //중복 회원 체크
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {//ifPresent : null이 아닌 값이 있으면 로직에 있는것이 동작하는것
               throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
