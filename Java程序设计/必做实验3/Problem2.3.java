public class Test {
    public static void main(String[] args) {
        Human[] humans = new Human[3];
        humans[0] = new Chinese("李华");
        humans[1] = new Japanese("たかはし");
        humans[2] = new English("Alice");
        //向上转型
        //override的方法仍然可以使用
        for (int i = 0; i < 3; i++)
            humans[i].sayHi();
    }

}

abstract class Human {//抽象类Human
    String name;//成员变量name

    Human(String s) {//构造方法
        name = s;
    }

    abstract double sayHi();//抽象方法
}

class Chinese extends Human {

    Chinese(String s) {
        super(s);
    }

    @Override
    double sayHi() {
        System.out.println(name + ":你好");
        return 0;
    }
}

class Japanese extends Human {

    Japanese(String s) {
        super(s);
    }

    @Override
    double sayHi() {
        System.out.println(name + ":こんにちは");
        return 0;
    }
}

class English extends Human {

    English(String s) {
        super(s);
    }

    @Override
    double sayHi() {
        System.out.println(name + ":hello");
        return 0;
    }
}
