package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity //JPA가 사용하는 객체라는 의미
//@Table(name = "item") //객체명과 테이블명이 같으면 생략가능
public class Item {

    //@Id: 테이블의 PK와 해당 필드를 매핑한다.
    //@GeneratedValue: PK 생성 값을 DB에서 생성하는 IDENTITY 방식을 사용한다. ex) MySQL auto increment
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //객체의 필드를 테이블의 컬럼과 매핑한다.
    //필드의 이름과 컬럼 이름이 같을 경우 생략 가능하다.
    @Column(name = "item_name", length = 10)
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
