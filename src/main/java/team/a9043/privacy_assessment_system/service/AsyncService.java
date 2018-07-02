package team.a9043.privacy_assessment_system.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AsyncService {
    @Async
    public void a1(InputStream errInputStream) throws IOException {
        ByteArrayOutputStream outErrStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = errInputStream.read(buffer)) != -1) {
            outErrStream.write(buffer, 0, len);
        }
    }

    @Async
    public Future<String> getJSONRes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        Pattern pattern = Pattern.compile("<\\$(\\d+)\\$>");
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1) {
            String rawOut = new String(buffer);
            Matcher matcher = pattern.matcher(rawOut);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
            outStream.write(buffer, 0, len);
        }
        // 打印流信息
        String rawOut = outStream.toString();
        String jsonStr = "";
        if (rawOut.contains("<%") && rawOut.contains("%>")) {
            jsonStr = rawOut.substring(rawOut.indexOf("<%") + 2, rawOut.indexOf("%>"));
        }
        return new AsyncResult<>(jsonStr);
    }
}
