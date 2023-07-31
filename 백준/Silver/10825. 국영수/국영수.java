import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); //학생 수

        List<Student> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            list.add(new Student(name, korean, english, math));
        }

        Collections.sort(list);
        for (Student student : list) {
            System.out.println(student.name);
        }

    }

    public static class Student implements Comparable<Student> {
        String name;
        int korean;
        int english;
        int math;

        public Student(String name, int korean, int english, int math) {
            super();
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {
            //세 과목 모두 같을 경우 이름 오름차순
            if (o.korean == this.korean
                    && o.english == this.english
                    && o.math == this.math)
            return  this.name.compareTo(o.name);
            //국어와 영어만 같을 경우 수학 내림차순
            else if (o.korean == this.korean && o.english == this.english) {
                return o.math-this.math;
            }
            //국어만 같으면 영어 오름차순
            else if (o.korean == this.korean) {
                return this.english-o.english;
            }

            //디폴트는 국어 내림차순
            return o.korean-this.korean;
        }

    }

}
