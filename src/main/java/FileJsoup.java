import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class FileJsoup {
    public static void main(String[] args) throws IOException {
        //读取含有参与人员的html文本
        Document doc = Jsoup.parse(new File("lib/list.html"),"utf-8");

        //筛选出名单保存
        String element=doc.select("a.name ").text();

        //按空格分割element字符串，放入新的字符串数组
        String[] strings=element.split(" ");

        //字符串数组查重
        Set set=new TreeSet();
        for (int i = 0; i < strings.length; i++) {
            set.add(strings[i]);
        }
        strings = (String[]) set.toArray(new String[0]);

        //设置名单输出格式
        System.out.println("参与抽奖的人员有：");
        for (int i = 0; i < strings.length; i++) {
            int kong=25-String_length(strings[i]);
            System.out.print(strings[i]);
            for (int j=0;j<kong;j++){
                System.out.print(" ");
            }
            if ((i+1)%5==0){
                System.out.println("");
            }
        }

        System.out.println();
        System.out.println("共"+strings.length+"人参与抽奖");

        int total=2;
        for(int i = 0; i < total; i++){
            Random rand=new Random();
            int num=rand.nextInt(strings.length);
            System.out.println("第"+(i+1)+"位中奖的是："+"@"+strings[num]);
        }
    }


    public static int String_length(String value){
        //判断字符串真实长度
        int  valueLength = 0;
        String chinese ="[\\u4e00-\\u9fa5]";
        for (int i=0;i<value.length();i++){
            String temp = value.substring(i,i+1);
            if (temp.matches(chinese)){
                valueLength+=2;
            }else {
                valueLength+=1;
            }
        }
        return valueLength;
    }
}
