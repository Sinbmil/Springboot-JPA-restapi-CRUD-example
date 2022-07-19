package wlh.wickies.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wlh.wickies.restapi.model.Member;
import wlh.wickies.restapi.repository.MemberRepository;

import java.util.*;

@RequestMapping("/member")
@RestController
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/insert") // CREATE
    public Member insert(@RequestBody Map<String, String> map){
        return memberRepository.save(
                new Member(map.get("id"), map.get("pw"),  map.get("name"), map.get("address"), map.get("phone"))
        );
    }

    @GetMapping("/select") // READ
    public List<Member> selectAll(){
        return memberRepository.findAll();
    }

    @GetMapping("/select/{id}") // READ
    public Member selectOne(@PathVariable("id") String id){
        return memberRepository.findById(id).orElse(null);
    }

    @GetMapping("/select/{name}/{phone}") // READ
    public Member find_id(@PathVariable("name") String name, @PathVariable("phone") String phone){
        return memberRepository.findById(phone).orElse(null);
    }

    @PostMapping("/update/{id}") // UPDATE
    public Member updateOne(@PathVariable("id") String id, @RequestBody Map<String, String> map){
        System.out.println(id);
        System.out.println(map);
        Member member = memberRepository.findById(id).orElse(null);
        member.setId(map.get("id"));
        member.setPw(map.get("pw"));
        member.setName(map.get("name"));
        member.setAddress(map.get("address"));
        member.setPhone(map.get("phone"));
        return memberRepository.save(member);
    }

    @PostMapping("/delete/{id}") // DELETE
    public String deleteOne(@PathVariable("id") String id){
        memberRepository.deleteById(id);
        return "삭제 완료";
    }

}
