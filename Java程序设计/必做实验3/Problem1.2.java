public class test {
    public static void main(String[] args) {
        String s = "Hi, Good Morning";//初始化字符串
        System.out.println(m(s));//输出m(s)
    }

    public static int m(String s) {
        int count = 0;//计数器
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                //调用Character的isUpperCase方法，判断是否为大写字母
                //charAt()方法返回指定索引位置的char值
                count++;//发现一个大写字母就让count加一
            }
        }
        return count;//返回字符串s中的大写字母数量
    }
}
