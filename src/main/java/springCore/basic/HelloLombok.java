package springCore.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import springCore.basic.member.MemberRepository;
import springCore.basic.member.MemoryMemberRepository;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class HelloLombok {

    private final MemberRepository memberRepository;

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok(new MemoryMemberRepository());
        helloLombok.setName("yrcho");
        String name1 = helloLombok.getName();
        System.out.println("name1 = " + name1);
        System.out.println("helloLombok = " + helloLombok);
    }
}
