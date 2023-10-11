package springCore.basic.singleton;

public class SingletonServiceTest {

    private static final SingletonServiceTest instance = new SingletonServiceTest();

    // 외부에서 객체를 생성하지 못 하도록 private 으로 막는다.
    private SingletonServiceTest() {
    }

    public static SingletonServiceTest getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
