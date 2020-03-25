package com.jeecg.p3.weixin.web;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.weixin.util.FastdfsUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Auther: Mr.Kong
 * @Date: 2020/3/24 15:27
 * @Description:
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @PostMapping("image")
    public Map<String, Object> upload(@RequestParam("storePath") int storePath, @RequestParam("file") MultipartFile multipartFile) throws Exception {
        String result = FastdfsUtils.httpClientUploadFile(multipartFile);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getInnerMap();
    }
}
