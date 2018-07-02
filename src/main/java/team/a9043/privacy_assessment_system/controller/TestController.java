package team.a9043.privacy_assessment_system.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.a9043.privacy_assessment_system.service.AsyncService;

import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class TestController {
    private final AsyncService asyncService;
    private Future<String> getJSONTask;

    @Autowired
    public TestController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/analysis")
    public String analysis() throws IOException, InterruptedException {
        String cmdStr_linux = "python3 /home/a9043/Spider/all_jsondata.py 1645171780";
        File file = new File("/home/a9043/spider/" + 1645171780);
        file.delete();
        Process proc = Runtime.getRuntime().exec(cmdStr_linux);
        InputStream errStream = proc.getErrorStream();
        InputStream stream = proc.getInputStream();

        asyncService.a1(errStream);
        getJSONTask = asyncService.getJSONRes(stream);

        return "{\"success\": true}";
    }

    @GetMapping("/getResult")
    public String getResult() throws ExecutionException, InterruptedException, IOException {
        JSONObject jsonObject = new JSONObject();
        if (getJSONTask == null) {
            jsonObject.put("err", true);
            jsonObject.put("success", false);
            jsonObject.put("done", true);
            return jsonObject.toString();
        }
        if (!getJSONTask.isDone()) {
            jsonObject.put("process", 10);
            char[] chars = new char[3];
            File file = new File("/home/a9043/spider/" + 1645171780);
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                if(fileReader.read(chars) != -1) {
                    jsonObject.put("process", Integer.valueOf(String.valueOf(chars).trim()));
                }
            }
            jsonObject.put("err", false);
            jsonObject.put("success", true);
            jsonObject.put("done", false);
            return jsonObject.toString();
        }
        jsonObject = new JSONObject(getJSONTask.get());
        jsonObject.put("success", true);
        jsonObject.put("err", false);
        jsonObject.put("done", true);
        return jsonObject.toString();
    }

    @GetMapping("/getInfo")
    public String getInfo() {
        return "";
    }

}
