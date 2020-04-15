package com.jeecg.p3.rebot;

import com.eastrobot.ask.sdk.*;
import com.eastrobot.ask.utils.Constant;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.jeecgframework.p3.core.utils.common.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.UUID;

/**
 * @Auther: Mr.Kong
 * @Date: 2020/4/1 14:45
 * @Description:
 */
public class Rebot {

    public static void main(String[] args) throws MalformedURLException,
            IOException {

        System.out.println(UUID.randomUUID().toString().replace("-", ""));
//        String appKey = "open1_lgOVwsSKq4uU";
//        String appSecret = "QJDMaeYPKEhTd7StLwxG";
//        String question = "你好";
//        String exampleFile = "请填写";
//
//        //智能问答
//        AskRequest askRequest = new AskRequest(appKey, appSecret, question,
//                Constant.SENIOR_TYPE, "sam", Constant.WEB_PLATFORM);
//        AskService askService = CloudServiceFactory.getInstance()
//                .createAskService();
//        askService.init(null);
//        AskResponse askResponse = null;
//        try {
//            askResponse = askService.ask(askRequest);
//            System.out.println("----------->回复内容为：" + askResponse.getContent() + "相似度为：" + askResponse.getSimilarity());
//        } catch (CloudNotInitializedException e) {
//            e.printStackTrace();
//        }

        //语音合成
//        SynthRequest synthRequest = new SynthRequest(appKey, appSecret, null,
//                question);
//        SynthService synthService = CloudServiceFactory.getInstance()
//                .createSynthService();
//        synthService.init(null);
//        SynthResponse synthResponse = null;
//        try {
//            synthResponse = synthService.synth(synthRequest);
//        } catch (CloudNotInitializedException e) {
//            e.printStackTrace();
//        }

        //语音识别
//        File file = new File(exampleFile);
////        byte[] data = FileUtils.readFileToByteArray(file);
////        RecogRequest recogRequest = new RecogRequest(appKey, appSecret, null,
////                data);
////        RecogService recogService = CloudServiceFactory.getInstance()
////                .createRecogService();
////        recogService.init(null);
////        RecogResponse recogResponse = null;
////        try {
////            recogResponse = recogService.recog(recogRequest);
////        } catch (CloudNotInitializedException e) {
////            e.printStackTrace();
////        }
    }

}
