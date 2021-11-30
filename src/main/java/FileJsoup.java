import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileJsoup {
    static String cPointer = "唐家17号";
    public static void main(String[] args) throws IOException {
        //读取含有参与人员的html文本
        Document doc = Jsoup.parse(new File("lib/list.html"), "utf-8");

        //筛选出名单保存
        String element = doc.select("a.name ").text();

        //按空格分割element字符串，放入新的字符串数组
        String[] Strings = element.split(" ");

        //字符串数组查重
        Set<String> set = new TreeSet<String>(Arrays.asList(Strings));
        Strings = set.toArray(new String[0]);

        //删除字符串里up主本人的名字
        List<String> list = Arrays.asList(Strings);
        if (list.contains(cPointer)) {
            List<String> arrayList = new ArrayList<String>(list);
            arrayList.remove(cPointer);
            Strings = arrayList.toArray(new String[arrayList.size()]);
        }

        //设置名单输出格式
        System.out.println("参与抽奖的人员有：");
        for (int i = 0; i < Strings.length; i++) {
            int kong = 25 - String_length(Strings[i]);
            System.out.print(Strings[i]);
            for (int j = 0; j < kong; j++) {
                System.out.print(" ");
            }
            if ((i + 1) % 5 == 0) {
                System.out.println("");
            }
        }
        System.out.println();
        System.out.println("共" + Strings.length + "人参与抽奖");

        //设置奖项总数
        int total = 10;

        //抽取中奖人员
        String[] luckyList = new String[total];
        for (int i = 0; i < total; i++) {
            int num = new Random().nextInt(Strings.length);
            if (Arrays.asList(luckyList).contains(Strings[num])) {
                i -= 1;
            } else luckyList[i] = Strings[num];
        }

        //输出中奖名单
        for (int i = 0; i < total; i++) {
            System.out.println("第" + (i + 1) + "位中奖的是：" + "@" + luckyList[i]);
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
