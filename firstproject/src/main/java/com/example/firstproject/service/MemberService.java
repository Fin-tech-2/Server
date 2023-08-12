package com.example.firstproject.service;

import com.example.firstproject.dto.MemberDTO;
import com.example.firstproject.entity.MemberEntity;
import com.example.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환하는 작업
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        System.out.println("request memberDTO : " + memberDTO.getMemberEmail() + ", "+ memberDTO.getMemberName() + ", "+ memberDTO.getMemberPassword() + ", ");

        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

        System.out.println("DB memberDTO : " + byMemberEmail.get().getMemberEmail() + ", "+ byMemberEmail.get().getMemberName() + ", "+ byMemberEmail.get().getMemberPassword() + ", ");
        if(byMemberEmail.get().getMemberEmail().equals(memberDTO.getMemberEmail()) && byMemberEmail.get().getMemberPassword().equals(memberDTO.getMemberPassword())) {
            MemberDTO result = new MemberDTO();
            result.setMemberName(byMemberEmail.get().getMemberName());
            result.setMemberEmail(byMemberEmail.get().getMemberEmail());
            System.out.println("Service 단에서 로그인 성공");
            return result;
        } else {
            System.out.println("Service 단에서 로그인 실패");
            return null;
        }
//        if (byMemberEmail.isPresent()) {
//            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
//            MemberEntity memberEntity = byMemberEmail.get();
//            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
//                //비밀번호 일치
//                System.out.println("비밀번호 ㅣㅇㄹ치");
//                // entity -> dto 변환 후 리턴
//                //MemberDTO dto= MemberDTO.toMemberDTO(memberEntity);
//                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
//                return dto;
//            } else {
//                //비밀번호 불일치(로그인 실패)
//                System.out.println("로그인 실패");
//                return null;
//            }
//        } else {
//            //조회 결과가 업다.(해당 이메일을 가진 회원이 없다.)
//            System.out.println("//조회 결과가 업다.(해당 이메일을 가진 회원이 없다.)");
//            return null;
//        }

    }
}
