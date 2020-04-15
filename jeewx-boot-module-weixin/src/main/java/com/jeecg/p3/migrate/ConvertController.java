package com.jeecg.p3.migrate;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import com.jeecg.p3.redis.JedisPoolUtil;
import com.jeecg.p3.weixin.util.HttpClientUtil;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: Mr.Kong
 * @Date: 2020/4/15 14:36
 * @Description: 迁移后新老openid对比
 */
@RestController
@SkipAuth(auth = SkipPerm.SKIP_ALL)
public class ConvertController {

    private final static String NEW_APPID = "wx8bdda991b9465bce";

    private final static String OLD_APPID = "wxc06c34aea4f24f82";

    private final static String APPSECRET = "f2f399a87563978a474cf47bbdc1923e";

    private static String AUTHURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";

    private static String CHANGEOPENIDURL = "http://api.weixin.qq.com/cgi-bin/changeopenid?access_token=ACCESS_TOKE";

    @PostMapping("/convert")
    public String convert(@RequestParam("file") MultipartFile file) throws IOException {
        Jedis jedis = JedisPoolUtil.getJedis();
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile(UUID.randomUUID().toString(), prefix);
        // MultipartFile to File
        file.transferTo(excelFile);

        String access_token = null;
        if (jedis.get("access_token") != null) {
            access_token = jedis.get("access_token");
        } else {
            AUTHURL = AUTHURL.replace("APPID", "wx8bdda991b9465bce").replace("SECRET", "f2f399a87563978a474cf47bbdc1923e");
            JSONObject jsonObject = WeiXinHttpUtil.sendGet(AUTHURL);
            access_token = (String) jsonObject.get("access_token");
            jedis.set("access_token", access_token);
            jedis.expire("access_token", (int) (60 * 60 * 1.5));
        }
        CHANGEOPENIDURL = CHANGEOPENIDURL.replace("ACCESS_TOKE", access_token);
        ArrayList<String> arrayList = new ArrayList<>();
        readTxt(arrayList, excelFile);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid_list", arrayList.toArray());
        jsonObject.put("from_appid", OLD_APPID);
        String result = HttpClientUtil.sendJsonStr(CHANGEOPENIDURL, jsonObject.toString());
        // 写入结果集
        writeTxt(result);
        //程序结束时，删除临时文件
        deleteFile(excelFile);
        return result;
    }

    private void readTxt(ArrayList<String> arrayList, File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                arrayList.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeTxt(String result) {
        JSONObject resultJson = JSONObject.parseObject(result);
        if (resultJson.get("errmsg").equals("ok")) {
            try {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D:/result_print.txt")),
                        "UTF-8"));
                List<JSONObject> resultList = (List) resultJson.get("result_list");
                for (JSONObject resultListJson : resultList) {
                    if (resultListJson.get("err_msg").equals("ok")) {
                        String new_openid = (String) resultListJson.get("new_openid");
                        bw.write(new_openid);
                        bw.newLine();
                    } else {
                        bw.write("---------------------------");
                        bw.newLine();
                    }
                }
                bw.close();
            } catch (Exception e) {
                System.err.println("write errors :" + e);
            }
        }
    }

    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
