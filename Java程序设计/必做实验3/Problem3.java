import java.util.HashMap;
import java.util.Map;

public class Problem6_3 {
    public static void main(String[] args) {
        //创建Map集合
        Map<String, Double> menu = new HashMap<String, Double>();
        //Map是接口，具体的实现类是HashMap
        //菜单包含菜名和价格这两个变量
        //依次插入菜
        menu.put("北京烤鸭", 199.1);
        menu.put("西芹炒肉", 12.9);
        menu.put("酸菜鱼", 59.0);
        menu.put("铁板牛柳", 32.1);

        // 计算总价格
        Double sum = 0.0;
        System.out.println("菜单：");
        for (Map.Entry<String, Double> entry : menu.entrySet()) {//for-each循环遍历
            System.out.println(entry.getKey() + " " + entry.getValue());
            sum += entry.getValue();//每遍历一个菜，就加入到总价格
        }
        System.out.println("总价格为：" + sum + "元。");
    }
}
