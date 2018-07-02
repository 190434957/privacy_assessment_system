package team.a9043.privacy_assessment_system.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.a9043.privacy_assessment_system.exception.NetWorkException;
import team.a9043.privacy_assessment_system.pojo.SysUser;
import team.a9043.privacy_assessment_system.service.UserService;

import java.io.*;
import java.net.URI;
import java.util.stream.Collectors;

@RestController
public class UserController {
/*
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/app_id")
    public JSONObject getAppId() {
        return new JSONObject().put("app_id", "1938859812");
    }

    @PostMapping("/user")
    public JSONObject doLogin(@RequestBody JSONObject tokenObj) throws IOException {
        JSONObject jsonObject = new JSONObject();
        OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory = new OkHttp3ClientHttpRequestFactory();
        ClientHttpRequest clientHttpRequest = okHttp3ClientHttpRequestFactory.createRequest(URI.create("https://api.weibo.com/2/account/get_uid.json"), HttpMethod.GET);
        ClientHttpResponse clientHttpResponse = clientHttpRequest.execute();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody()));
        JSONObject uidObj = new JSONObject(bufferedReader.lines().collect(Collectors.joining()));
        String uid = uidObj.getString("uid");
        if (uid == null) {
            uidObj.put("success", false);
            throw new NetWorkException(uidObj.toString());
        }
        SysUser sysUser = userService.doLogin(uid);
        if (sysUser == null) {
            jsonObject.put("success", true);
            jsonObject.put("is_new", true);
            return jsonObject;
        }
        jsonObject.put("success", true);
        jsonObject.put("is_new", false);
        jsonObject.put("user", sysUser);
        return jsonObject;
    }
*/

}
