import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Logger;

public class videoDate {

    public static JSONObject httpRequest(String requestUrl, String requestMethod){

        Logger logger = Logger.getLogger("getVideoData-logger");
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();

        try {
            URL url = new URL(requestUrl);
            // http协议传输
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);

            // 设置请求方式（GET/POST）
            httpURLConnection.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) httpURLConnection.connect();

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            httpURLConnection.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static void main(String[] args) {
        String BVID="BV1WK4y1L7ot";
        String url = "http://api.bilibili.com/x/web-interface/view?bvid="+BVID;
        String HR = httpRequest(url,"GET").getString("data");
        //String stat = HR.
        System.out.println(HR);

    }
}
