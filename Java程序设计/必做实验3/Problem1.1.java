public class test {
    public static void main(String[] args) {
        System.out.println("Hi, ABC, good".matches("ABC "));
        //"Hi, ABC, good"与"ABC "不等，返回值为false
        System.out.println("Hi, ABC, good".matches(".*ABC.*"));
        //.*ABC.*表示含有"ABC"的字符串，返回值为true
        System.out.println("A,B;C".replaceAll(",;", "#"));
        //把",;"全部替换为"#"，仍然输出"A,B;C"
        System.out.println("A,B;C".replaceAll("[,;]", "#"));
        //把"," 和";"都全部替换为"#"，输出"A#B#C"
        String[] tokens = "A,B;C".split("[,;]");
        //以","和";"作为分隔符，将分割出的字符串存储在字符串数组中
        for (int i = 0; i < tokens.length; i++) {
            System.out.print(tokens[i] + " ");//输出分割后的字符串
        }
        //输出"A B C"
    }
}
