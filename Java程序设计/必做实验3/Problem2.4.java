public class HumanTest {
    public static void main(String[] args) {
        Human[] human = new Human[3];//创建一个包含3个Human对象的数组

        //把子类赋值给父类，向上转型
        human[0] = new Chinese("李华");
        human[1] = new Japanese("たかはし");
        human[2] = new English("Alice");
        //向上转型会丢失子类特有的方法,但是子类overriding 父类的方法仍然有效
        for (int i = 0; i < 3; i++)
            human[i].sayHi();
        //正常输出
    }
}

interface Human {//接口Human

    double sayHi();
}

class Chinese implements Human {
    String name;

    Chinese(String s) {
        name = s;
    }

    @Override
    public double sayHi() {
        System.out.println(name + ":你好");
        return 0;
    }
}

class Japanese implements Human {
    String name;

    Japanese(String s) {
        name = s;
    }

    @Override
    public double sayHi() {
        System.out.println(name + ":こんにちは");
        return 0;
    }
}

class English implements Human {
    String name;

    English(String s) {
        name = s;
    }

    @Override
    public double sayHi() {
        System.out.println(name + ":hello");
        return 0;
    }
}