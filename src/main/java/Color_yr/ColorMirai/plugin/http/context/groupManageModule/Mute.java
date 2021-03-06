package Color_yr.ColorMirai.plugin.http.context.groupManageModule;

import Color_yr.ColorMirai.plugin.http.Authed;
import Color_yr.ColorMirai.plugin.http.obj.StateCode;
import Color_yr.ColorMirai.plugin.http.obj.group.MuteDTO;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.contact.NormalMember;

public class Mute extends BaseMute {
    @Override
    public Object toDo(Authed authed, MuteDTO parameters) {
        Group group = authed.bot.getGroup(parameters.target);
        if (group == null) {
            return StateCode.NoElement;
        }
        if (group.getBotPermission() == MemberPermission.MEMBER) {
            return StateCode.PermissionDenied;
        }
        NormalMember member = group.get(parameters.memberId);
        if (member == null) {
            return StateCode.NoElement;
        }
        member.mute(parameters.time);
        return StateCode.Success;
    }
}
