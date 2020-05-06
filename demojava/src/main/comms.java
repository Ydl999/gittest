package main;

import sun.rmi.log.LogInputStream;

import java.util.*;
import java.util.stream.Collectors;

public class comms implements Comparable{
    private String Cname;
    private String Crad;

    public comms() {
    }

    public comms(String cname, String crad) {
        Cname = cname;
        Crad = crad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        comms comms = (comms) o;
        return Objects.equals(Cname, comms.Cname) &&
                Objects.equals(Crad, comms.Crad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Cname, Crad);
    }

    @Override
    public String toString() {
        String regEX = "[\n`~!@#$%^&*()+=|{}':;'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
        return Cname +"," +Crad.replaceAll(regEX,"");

    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCrad() {
        String regEX = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
        return Crad.replaceAll(regEX,"");
    }

    public void setCrad(String crad) {
        Crad = crad;
    }

    @Override
    public int compareTo(Object o) {
        if ( o instanceof comms){
            comms b = (comms) o;
            if(compares(b.getCname())-compares(this.getCname())!=0){
                return compares(this.getCname())-compares(b.getCname());

            }
            //同一类牌
            //三代对排序
            else if (compares(b.getCname())==2||compares(b.getCname())==1){
                if(ste(b.getCrad())[0]==ste(this.getCrad())[0]){
                    return ste(this.getCrad())[3]-ste(b.getCrad())[3];
                }else {
                    return ste(this.getCrad())[0]-ste(b.getCrad())[0];
                }
            } else  {
                /*String[] arb = b.getCrad().split(", ");
                String[] arr = this.getCrad().split(", ");
                int[] iarb = Arrays.stream(arb).mapToInt(Integer::parseInt).toArray();
                int[] iarr =Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();*/
                return Arrays.stream(ste(this.getCrad())).sum()-Arrays.stream(ste(b.getCrad())).sum();
            }
        }
        return 0;
    }
    public int compares(String str){
        int m = 0;
        switch (str){
            case "炸弹" : m = 4; break;
            case "顺子" : m = 3; break;
            case "三带对": m = 2; break;
            case "三带一": m = 1; break;
            case "对子" : m = 0; break;
        }
        return m;
    }
    //字符串转int[]
    public int[] ste(String str){
        String[] arb = str.split(", ");
        int[] iarb = Arrays.stream(arb).mapToInt(Integer::parseInt).toArray();
        return iarb;
    }
}
