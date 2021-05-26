package Color_yr.ColorMirai.plugin.http.obj.message;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(typeName = "Face")
public class FaceDTO extends MessageDTO {
    public int faceId;
    public String name;

    public FaceDTO(int faceId, String name) {
        this.faceId = faceId;
        this.name = name;
    }
}
