package springCore.basic.singleton;

public class StatefulService {

    private int price; // 상태를 유지하는 공유필드 (10000 -> 20000)

    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price; // 여기가 문제!
    }

    // 무상태 설계 : 필드의 값을 변경하는게 아닌 price 자체를 리턴
//    public int order(String name, int price) {
//        System.out.println("name = " + name + ", price = " + price);
//        return price;
//    }

    public int getPrice() {
        return price;
    }
}
