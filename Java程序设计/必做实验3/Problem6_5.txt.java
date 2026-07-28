
import java.util.*;

public class Problem6_5 {
    public static void main(String[] args) {//主函数
        //创建文章字符串str
        String str="SZU Shenzhen University (SZU) is committed to excellence in teaching, research and social service. Sticking to the motto of “self-reliance, self-discipline, self-improvement”, the University is dedicated to serving the Shenzhen Special Economic Zone (SEZ), demonstrating China’s reform and opening up and pioneering change in higher education.\n" +
                "\n" +
                "SZU, which is based in Shenzhen, China’s first Special Economic Zone and a key city in the Guangdong-Hong Kong-Macau Greater Bay Area, is distinctively known as an Experimental University in higher education with its reforms in the sector acknowledged in Mainland China.\n" +
                "\n" +
                "Established in 1983, SZU received support from top Chinese universities including Peking University, Tsinghua University and Renmin University of China in the founding of new schools. In the past decades, the University has undergone rapid growth and has become a comprehensive university with complete disciplines, top-ranked academic and research institutes and awe-inspiring faculty. SZU faculty members are engaged with teaching and research for the betterment of society. They are devoted to seeking solutions to pressing global challenges and promoting innovation.\n" +
                "\n" +
                "SZU offers a wide array of undergraduate and graduate programs and provides students with an interdisciplinary and inclusive multicultural learning environment. Students in SZU enjoy the plenty resources and facilities of both the SEZ and the University, pursue academic excellence and discover new interests and opportunities in a fast-changing era.\n" +
                "\n" +
                "SZU is an integral part of the SEZ, a thriving technology and innovation hub. With four campuses in Yuehai, Canghai, Lihu and Luohu, the University vigorously conducts leading researches in various fields and collaborates with high-tech enterprises in the community for technology transfer. SZU strives to provide a high-quality and effective education and develop in each SZU member the ability and passion to innovate and contribute to social progress and development, and encourages talented young people to start entrepreneurship in SZU. Our alumni including Tencent have founded dozens of innovative companies with significant influence.\n" +
                "\n" +
                "SZU is accelerating its pace toward internationalization, providing a variety of global learning opportunities. The University has established partnerships with numbers of overseas universities to offer exceptional exchange programs, joint degree programs, research collaborations, and a variety of other forms of collaborations with international partners. Students from all over the world are welcomed in SZU. In the city noted for its urban vitality and natural beauty, students can explore the most attractive parts of China, pursue their passion and develop their interests, perspectives and abilities.";
        String regex = "[\\s\\d\\p{Punct}]+";//使用参数指定的正则表达式regex作为分隔标记分解出其中的单词
        String[] ssr = str.split(regex);//将分解出的单词存放在字符串数组中
        Map<String,Integer> map = new HashMap<String,Integer>();//创建map类对象
        // 先进行统计
        for (int i = 0; i < ssr.length; i++) {
            if(map.get(ssr[i])==null){
                map.put(ssr[i], 1);
            }else{
                map.put(ssr[i], map.get(ssr[i])+1);
            }
        }
        //进行排序
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator(){
            public int compare(Object o1, Object o2){
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        Map sortedMap = new LinkedHashMap();
        //进行每个英文单词统计出现次数的输出
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
            System.out.println(sortedMap);
            sortedMap.clear();
        }
        //进行出现次数最多的50个单词进行输出
        int i=1;
        for(Iterator it = list.iterator();it.hasNext()&&i<=50;i++)
        {
            Map.Entry<String,Integer>entry=(Map.Entry<String,Integer>)it.next();
            System.out.print(entry.getKey()+' ');
            if(i%10==0){
                System.out.println();
            }
        }


    }
}
