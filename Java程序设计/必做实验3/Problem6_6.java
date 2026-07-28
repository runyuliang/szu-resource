import java.util.ArrayList;
import java.util.Objects;

public class Problem6_6 {
    public static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        //重写toString方法
        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(name, student.name);
        }
    }

    public static void main(String[] args) {
        ArrayList<Student> clubA = new ArrayList<Student>(), clubB = new ArrayList<Student>();
        //建立A俱乐部和B俱乐部
        //依次添加人
        clubA.add(new Student("张三"));
        clubA.add(new Student("李四"));

        clubB.add(new Student("李四"));
        clubB.add(new Student("王五"));

        System.out.println("A社团名单:");
        for (Student stu : clubA)
            System.out.println(stu);

        System.out.println("\nB社团名单:");
        for (Student stu : clubB)
            System.out.println(stu);

        System.out.println("\n同时参加A，B社团名单:");
        for (Student stu : clubA)
            if (clubB.contains(stu))//如果他也在B社团
                System.out.println(stu);
    }
}
