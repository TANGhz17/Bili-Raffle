import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class videoDate {

    public static void GetVideoDate(String bvid) throws IOException {

        Logger logger = Logger.getLogger("getVideoData-logger");
        //根据网址和参数实例化URL
        URL url = new URL("http://api.bilibili.com/x/web-interface/view?bvid="+bvid);
        //打开url的Connection连接
        URLConnection connection = url.openConnection();

        //获取连接的输入流
        InputStream inputStream=connection.getInputStream();                        //根据连接获取输入流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);   //将输入流转化为字符流
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);        //将输入字符流包装，以便使用
        Object rl = bufferedReader.readLine();
        System.out.println(rl);



    }
    public static void main(String[] args) throws IOException {
        String BVID="BV1WK4y1L7ot";
        GetVideoDate(BVID);
    }
}
