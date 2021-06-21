package hello.core.singleton;

public class StatefulService {

    public int order(String name, int price) {
        System.out.print("name = " + name);
        System.out.println(" price = " + price);
//        this.price = price; // 여기서 문제 발생
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
