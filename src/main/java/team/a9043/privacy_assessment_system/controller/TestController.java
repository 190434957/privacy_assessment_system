package team.a9043.privacy_assessment_system.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.a9043.privacy_assessment_system.service.TestService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class TestController {
    private final TestService testService;
    private Map<String, Future<String>> TaskMap = new HashMap<>();

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/analysis")
    public String analysis(@RequestParam("uid") String uid) throws IOException, InterruptedException {
        //1645171780 张亚勤
        //1197161814 李开复
        String id = UUID.randomUUID().toString();
        String cmdStr_linux = String.format("python3 /home/weibo/Spider/all_jsondata.py %s", uid);
        Process proc = Runtime.getRuntime().exec(cmdStr_linux);
        InputStream errStream = proc.getErrorStream();
        InputStream stream = proc.getInputStream();

        testService.getJsonResErr(errStream);
        TaskMap.put(id, testService.getJsonRes(stream));

        return "{\"success\": true, \"id\": \"" + id + "\"}";
    }

    @GetMapping("/getResult")
    public String getResult(@RequestParam("id") String id, @RequestParam("uid") String uid) throws ExecutionException, InterruptedException, IOException {
        JSONObject jsonObject = new JSONObject();
        if (TaskMap.get(id) == null) {
            jsonObject.put("err", true);
            jsonObject.put("success", false);
            jsonObject.put("done", true);
            return jsonObject.toString();
        }
        if (!TaskMap.get(id).isDone()) {
            jsonObject.put("process", 10);
            char[] chars = new char[3];
            File file = new File(String.format("/home/weibo/Spider/%s", uid));
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                if (fileReader.read(chars) != -1) {
                    jsonObject.put("process", Integer.valueOf(String.valueOf(chars).trim()));
                }
            }
            jsonObject.put("err", false);
            jsonObject.put("success", true);
            jsonObject.put("done", false);
            return jsonObject.toString();
        }
        jsonObject = new JSONObject(TaskMap.get(id).get());
        jsonObject.put("success", true);
        jsonObject.put("err", false);
        jsonObject.put("done", true);
        return jsonObject.toString();
    }

    @GetMapping("/getInfo")
    public String getInfo(@RequestParam("uid") String uid) {
        return testService.getUserInfo(uid);
    }

}
