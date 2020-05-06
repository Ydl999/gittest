package main;

import org.omg.Messaging.SyncScopeHelper;
import sun.rmi.log.LogInputStream;

import java.beans.DesignMode;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class demo {
    //生成所有类型组合的集合
    public Collection<List<Integer>> getlist(int[] array) {
        Collection<List<Integer>> values = Arrays.stream(array)
                .boxed()
                .collect(Collectors.groupingBy(i -> i))
                .values();
        return values;
    }
    //生成所以牌的组合牌
    public List<comms> getCard(Collection<List<Integer>> list){
        List<comms> coo = new ArrayList<>();
        //生成炸弹
        Collection<List<Integer>> val= list.stream().filter(a -> a.size()>3).collect(Collectors.toList());
        if (!val.isEmpty()) {
            Object[] value = val.toArray();
            List<Object> arr =  Arrays.stream(value).collect(Collectors.toList());
            arr.stream().forEach(a->{coo.add(new comms("炸弹",a.toString().substring(1,11)));});
        }
        //生成顺子
        if(list.size()>=5){
            List<Integer> abc = new ArrayList<>();
            HashSet<Integer> co2 = new HashSet<>();
            list.stream().forEach(a->{abc.add(a.get(0));});
            for (int i=0;i<abc.size()-1;i++){
                for (int j=i;j<abc.size()-1;j++){
                    if (abc.get(j)+1 == abc.get(j+1)) {
                        co2.add(abc.get(j));co2.add(abc.get(j+1));
                        if(co2.size()>=5){
                            coo.add(new comms("顺子",co2.toString()));
                        }
                    }else {
                        co2.clear();
                    }
                }co2.clear();

            }
        }
        //三带对
        Collection<List<Integer>> vals= list.stream().filter(a -> a.size()==3||a.size()==2).collect(Collectors.toList());
        if(vals.size()>=2){
            List<List<Integer>> ssstt = new ArrayList<>();
            vals.stream().forEach(a->{ssstt.add(a);});
            List<List<Integer>> sssTT = ssstt.stream().sorted((x, y)->{return y.size()-x.size();}).collect(Collectors.toList());
            for (int i=0;i<sssTT.size()-1;i++){
                for(int j=i;j<sssTT.size()-1;j++){
                    if (sssTT.get(i).size()+sssTT.get(j+1).size()==5) {
//                        System.out.println(j);

                        coo.add(new comms("三带对",sssTT.get(i).toString()+","+sssTT.get(j+1).toString()));
                    }
                }
            }
        }
        //三带一
        Collection<List<Integer>> vvva= list.stream().filter(a -> a.size()==3||a.size()==1).collect(Collectors.toList());
        if(vvva.size()>=2){
            List<List<Integer>> ssst = new ArrayList<>();
            vvva.stream().forEach(a->{ssst.add(a);});
            List<List<Integer>> sssT = ssst.stream().sorted((x, y)->{return y.size()-x.size();}).collect(Collectors.toList());
            for (int i=0;i<sssT.size()-1;i++){
                for(int j=i;j<sssT.size()-1;j++){
                    if (sssT.get(i).size()+sssT.get(j+1).size()==4) {
                        coo.add(new comms("三带一",sssT.get(i).toString()+","+sssT.get(j+1).toString()));
                    }
                }
            }
        }
        //对子
        Collection<List<Integer>> vv= list.stream().filter(a -> a.size()==2).collect(Collectors.toList());
        if(!vv.isEmpty()){
            vv.stream().forEach(a->{coo.add(new comms("对子",a.toString())); });
        }
        return coo;
    }
    //判断牌大小并输出结果
    public void card(String str1,String str2){
        //我的牌
        String[] st1 = str1.split("");
        int[] array = Arrays.stream(st1).mapToInt(Integer::parseInt).toArray();
        Collection<List<Integer>> ii1= this.getlist(array);
        List<comms> listii = this.getCard(ii1);
        Collections.sort(listii);
        //对手牌
        String[] st2 = str2.split("");
        int[] array2 = Arrays.stream(st2).mapToInt(Integer::parseInt).toArray();
        Collection<List<Integer>> ii2 = this.getlist(array2);
        List<comms> listii2 = this.getCard(ii2);
        Collections.sort(listii2);
        listii.forEach(a->{if(a.compareTo(listii2.get(listii2.size()-1))>0){
            System.out.println(a);
        }});
    }
    public static void main(String[] args) {
        demo demo = new demo();
        Scanner input=new Scanner(System.in);
        System.out.println("我的牌:");
       String str = input.next();
        System.out.println("你的牌:");
        String str1 = input.next();
        demo.card(str,str1);



//
    }
}

