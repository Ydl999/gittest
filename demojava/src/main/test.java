package main;


import javax.print.DocFlavor;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {
    //判断数组对应类型
    public String compel(int[] db){
        int a = db[0]; int b = db[1]; int c = db[2];

        if (a==b&&b==c)
            return "豹子";
        if((a-1)==b&&b==(c+1)||(a+1)==b&&b==(c-1))
            return "顺子";
        if (a==b||b==c||c==a)
            return "对子";
        if((a-b)==1||(b-a)==1||(b-c)==1||(c-b)==1)
            return "半顺";
        return "杂六";
    }
    //拿到装有3个数组的list集合
    public List<int[]> getArr(String[] str){
        List<int[]> list = new ArrayList();
        //将String[]转换成int[]
        int[] array = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
        list.add(Arrays.copyOfRange(array,0,3));
        list.add(Arrays.copyOfRange(array,1,4));
        list.add(Arrays.copyOfRange(array,2,5));
        return list;
    }

    public static void main(String[] args) {
        test tst = new test();
        Scanner input=new Scanner(System.in);
        String st=input.next();
        //转换成字符串数组
        String[] str = st.split("");
        //System.out.println(Arrays.toString(str));
        List<int[]> arrs = tst.getArr(str);
        System.out.println("前三,"+tst.compel(arrs.get(0)));
        System.out.println("中三,"+tst.compel(arrs.get(1)));
        System.out.println("后三,"+tst.compel(arrs.get(2)));
    }
}
