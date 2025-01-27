package nibm.kd.hdse242.lmscw.controllers;

import nibm.kd.hdse242.lmscw.dto.BookDTO;
import nibm.kd.hdse242.lmscw.dto.MemberDTO;
import nibm.kd.hdse242.lmscw.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lms/v1/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<MemberDTO> getAllMembers() {
        try{
            return memberService.getAllMembers();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/{Id}")
    public MemberDTO getBook(@PathVariable long Id) {
        try {
            return memberService.getMember(Id);
        }catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping
    public Map<String, Object> addMember(@RequestBody MemberDTO memberDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            MemberDTO addedMember = memberService.addMember(memberDTO);

            response.put("message", "Member added complete");
            response.put("member", addedMember);

        }catch(Exception e) {
            System.out.println(e);
            response.put("message", "Member not added");

        }
        return response;
    }

    @PutMapping("/{Id}")
    public Map<String, Object> updateMember(@PathVariable long Id, @RequestBody MemberDTO memberDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            MemberDTO updatedMember = memberService.updateMember(Id, memberDTO);
            response.put("message", "Member updated complete");
            response.put("member", updatedMember);
        }catch(Exception e) {
            System.out.println(e);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{Id}")
    public String deleteMember(@PathVariable long Id) {
        try {
            memberService.deleteMember(Id);
            return "Member deleted";
        }catch(Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }
}
