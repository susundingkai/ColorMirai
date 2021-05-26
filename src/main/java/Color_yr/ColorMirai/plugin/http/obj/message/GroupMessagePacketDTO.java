package Color_yr.ColorMirai.plugin.http.obj.message;

import Color_yr.ColorMirai.plugin.http.obj.contact.MemberDTO;

public class GroupMessagePacketDTO extends MessagePacketDTO {
    public MemberDTO sender;
    public String type;

    public GroupMessagePacketDTO(MemberDTO sender) {
        this.sender = sender;
        this.type = "GroupMessage";
    }
}
