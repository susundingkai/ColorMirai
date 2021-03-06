package Color_yr.ColorMirai.plugin.http.context.authModule;

import Color_yr.ColorMirai.plugin.http.Authed;
import Color_yr.ColorMirai.plugin.http.SessionManager;
import Color_yr.ColorMirai.plugin.http.Utils;
import Color_yr.ColorMirai.plugin.http.obj.StateCode;
import Color_yr.ColorMirai.plugin.http.obj.auth.BindDTO;
import Color_yr.ColorMirai.robot.BotStart;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public class Release implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
        InputStream inputStream = t.getRequestBody();
        BindDTO obj = JSONObject.parseObject(inputStream, BindDTO.class);
        String response;
        Authed authed = SessionManager.get(obj.sessionKey);
        if (authed == null) {
            response = JSONObject.toJSONString(StateCode.IllegalSession);
        } else if (!BotStart.getBots().containsKey(obj.qq)) {
            response = JSONObject.toJSONString(StateCode.NoBot);
        } else {
            SessionManager.close(obj.sessionKey);
            response = JSONObject.toJSONString(StateCode.Success);
        }
        Utils.send(t, response);
    }
}