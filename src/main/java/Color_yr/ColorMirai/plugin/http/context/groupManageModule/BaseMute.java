package Color_yr.ColorMirai.plugin.http.context.groupManageModule;

import Color_yr.ColorMirai.plugin.http.Authed;
import Color_yr.ColorMirai.plugin.http.SessionManager;
import Color_yr.ColorMirai.plugin.http.Utils;
import Color_yr.ColorMirai.plugin.http.obj.StateCode;
import Color_yr.ColorMirai.plugin.http.obj.group.MuteDTO;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public abstract class BaseMute implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
        InputStream inputStream = t.getRequestBody();
        MuteDTO obj = JSONObject.parseObject(inputStream, MuteDTO.class);
        String response;
        if (!SessionManager.haveKey(obj.sessionKey)) {
            response = JSONObject.toJSONString(StateCode.IllegalSession);
        } else if (SessionManager.get(obj.sessionKey) == null) {
            response = JSONObject.toJSONString(StateCode.NotVerifySession);
        } else {
            Authed authed = SessionManager.get(obj.sessionKey);
            response = JSONObject.toJSONString(toDo(authed, obj));
        }
        Utils.send(t, response);
    }

    abstract public Object toDo(Authed authed, MuteDTO parameters);
}