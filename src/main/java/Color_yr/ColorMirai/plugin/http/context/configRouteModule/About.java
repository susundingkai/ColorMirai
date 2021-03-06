package Color_yr.ColorMirai.plugin.http.context.configRouteModule;

import Color_yr.ColorMirai.plugin.http.Utils;
import Color_yr.ColorMirai.plugin.http.obj.result.StringMapRestfulResult;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;

public class About implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
        String response = JSONObject.toJSONString(new StringMapRestfulResult() {{
            this.data = new HashMap<>();
            data.put("version", "ColorMirai-3.5.0");
        }});
        Utils.send(t, response);
    }
}