package com.bluemsun.BBS.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONUtil {

    public static String getJson(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = null;
        String jsonString = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        stringBuilder = new StringBuilder();
        String str = "";
        while ((str = reader.readLine()) != null) {
            stringBuilder.append(str);
        }
        //获取到了json
        jsonString = stringBuilder.toString();
        reader.close();
        return jsonString;

    }
}
