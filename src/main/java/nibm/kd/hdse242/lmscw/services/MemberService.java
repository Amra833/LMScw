package nibm.kd.hdse242.lmscw.services;

import nibm.kd.hdse242.lmscw.dto.BookDTO;
import nibm.kd.hdse242.lmscw.dto.MemberDTO;
import nibm.kd.hdse242.lmscw.entities.Book;
import nibm.kd.hdse242.lmscw.entities.Member;
import nibm.kd.hdse242.lmscw.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //Create Members
    public MemberDTO addMember(MemberDTO memberDTO) {
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPhone(memberDTO.getPhone());
        memberRepository.save(member);
        return memberDTO;
    }

    //Retrieve Members
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (Member member : members) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setId(member.getId());
            memberDTO.setName(member.getName());
            memberDTO.setEmail(member.getEmail());
            memberDTOs.add(memberDTO);
        }
        return memberDTOs;
    }

    //Retrieve a Member
    public MemberDTO getMember(long Id) {
        Member member = memberRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Member Not Found"));
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setName(member.getName());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPhone(member.getPhone());
        return memberDTO;
    }

    //Update a Book
    public MemberDTO updateMember(long Id, MemberDTO memberDTO) {
        Member member = memberRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Member Not Found"));
        //member.setId(memberDTO.getId());
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        memberRepository.save(member);
        return memberDTO;
    }

    //Delete a Book
    public MemberDTO deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member Not Found"));
        memberRepository.deleteById(id);

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setName(member.getName());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPhone(member.getPhone());

        return memberDTO;
    }

}
