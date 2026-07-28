public class Test {
    public static void main(String[] args) {
        Chinese chinese = new Chinese("李华");
        Japanese japanese = new Japanese("たかはし");
        English english = new English("Alice");
        chinese.sayHi();
        japanese.sayHi();
        english.sayHi();
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
