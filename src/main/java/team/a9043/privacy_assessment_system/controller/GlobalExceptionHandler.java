package team.a9043.privacy_assessment_system.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import team.a9043.privacy_assessment_system.exception.NetWorkException;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(NetWorkException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JSONObject handleParameterNotFoundException(Exception e, HttpServletResponse response) {
        return badRequest(e, response);
    }
    @ExceptionHandler(JSONException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JSONObject handleJSONException(Exception e, HttpServletResponse response) {
        return badRequest(e, response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JSONObject handleNoHandlerFoundException(Exception e, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errMsg", e.getMessage());
        jsonObject.put("err", HttpStatus.NOT_FOUND);
        return jsonObject;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject handleOtherException(Exception e, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errMsg", e.getMessage());
        jsonObject.put("err", HttpStatus.INTERNAL_SERVER_ERROR);
        e.printStackTrace();
        return jsonObject;
    }

    private JSONObject badRequest(Exception e, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errMsg", e.getMessage());
        jsonObject.put("err", HttpStatus.BAD_REQUEST);
        return jsonObject;
    }
}
