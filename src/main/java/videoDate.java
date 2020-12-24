import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
// logging
import java.util.logging.Level;
import java.util.logging.Logger;


class videoDate {
    private static final String TAG = "videoDate";
    private static final FormatVideoData VideoData = new FormatVideoData();
    private static final FormatVideoData.owner ownerData= new FormatVideoData.owner();
    private static final FormatVideoData.stat statData=new FormatVideoData.stat();

    public static JSONObject httpRequest(String requestUrl, String requestMethod){
        Logger logger = Logger.getGlobal();

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
            logger.warning("HttpURLConnection");
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();

        String BVID="BV1WK4y1L7ot";
        jsonStrToJava(BVID);
        logger.info(VideoData.toString());


    }


    public static void jsonStrToJava(String BVID){
        Logger logger = Logger.getGlobal();
        String url = "http://api.bilibili.com/x/web-interface/view?bvid="+BVID;
        JSONObject jsonObjectData = httpRequest(url,"GET").getJSONObject("data");

        //logger.info(jsonObjectData+" ");
        VideoData.setBvid((String) jsonObjectData.get("bvid"));
        VideoData.setAvid((Integer) jsonObjectData.get("aid"));
        VideoData.setVideos((Integer) jsonObjectData.get("videos"));
        VideoData.setPic((String) jsonObjectData.get("pic"));
        VideoData.setTitle((String) jsonObjectData.get("title"));
        VideoData.setPubdate((String) jsonObjectData.get("desc"));

        JSONObject jsonObjectOwner = jsonObjectData.getJSONObject("owner");
        ownerData.setMid((Integer) jsonObjectOwner.get("mid"));
        ownerData.setName((String) jsonObjectOwner.get("name"));
        ownerData.setFace((String) jsonObjectOwner.get("face"));

        JSONObject jsonObjectStat = jsonObjectData.getJSONObject("stat");
        statData.setView(jsonObjectStat.getInt("view"));
        statData.setDanmaku(jsonObjectStat.getInt("danmaku"));
        statData.setReply(jsonObjectStat.getInt("reply"));
        statData.setLike(jsonObjectStat.getInt("like"));
        statData.setCoin(jsonObjectStat.getInt("coin"));
        statData.setFavorite(jsonObjectStat.getInt("favorite"));
        statData.setShare(jsonObjectStat.getInt("share"));

    }
}
