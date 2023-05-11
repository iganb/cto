package com.example.cto;

import cn.hutool.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.platform.commons.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 张驰
 * @Date 2022/12/12 16:40
 * @Version 1.0
 */


public class Test {

    /**
     * @param addressName
     * @Description 获取坐标
     * @Throws
     * @Return java.util.Map<java.lang.String, java.math.BigDecimal>
     * @Date 2021-03-19 13:53:22
     * @Author WangKun
     **/
    public static Map<String, BigDecimal> getLatAndLngByAddressName(String addressName) {
        Map<String, BigDecimal> map = new HashMap<>();
        try {
            String address = URLEncoder.encode(addressName, "UTF-8");
            String urlAddress = "http://api.map.baidu.com/place/v2/search?query="+address+"&region=泰兴市&city_limit=true&output=json&ak=SfrwGH7INvjPq7BwCrYrioBQZm9XXxrR";
            StringBuilder sb = new StringBuilder();
            URL url = new URL(urlAddress);
            URLConnection connection = url.openConnection();
            if (connection != null) {
                InputStreamReader insr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(insr);
                String data;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
                insr.close();
            }
            String str = sb.toString();
            if (StringUtils.isNotBlank(str)  && StringUtils.isNotBlank(str)) {
                JSONObject json = JSON.parseObject(str);
                if (json.get("message").toString().equals("ok")) {
                    JSONArray jsonArray = (JSONArray) json.get("results");
                    if (null != jsonArray && jsonArray.size() > 0) {
                        // 取最近一个点
                        JSONObject resultObj = (JSONObject) jsonArray.get(0);
                        JSONObject locationObj = (JSONObject) resultObj.get("location");
                        if (null != locationObj) {
                            map.put("lng", (BigDecimal) locationObj.get("lng"));
                            map.put("lat", (BigDecimal) locationObj.get("lat"));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
