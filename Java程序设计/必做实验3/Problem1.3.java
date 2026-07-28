public class test {
    public static void main(String[] args) {
        String s = "Java";//初始化字符串s
        StringBuilder builder = new StringBuilder(s);//使用s初始化builder
        change(s, builder);//调用change方法

        System.out.println(s);//输出s
        //s不变，仍为"Java"
        System.out.println(builder);//输出builder
        //builder变为"Java and HTML"
    }

    private static void change(String s, StringBuilder builder) {
        //Java在调用函数时将实际参数复制一份传递到函数中的形式参数中
        //其实就是副本
        //change中的变量s并不是main当中的s
        s = s + " and HTML";//把" and HTML"添加到s的尾端
        builder.append(" and HTML");//把" and HTML"添加到builder的尾端
        //append方法返回的是this，调用方法的对象是builder
    }
}
