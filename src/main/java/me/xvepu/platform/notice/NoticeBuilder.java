package me.xvepu.platform.notice;

import com.cryptomorin.xseries.messages.ActionBar;
import com.cryptomorin.xseries.messages.Titles;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.xvepu.platform.utilities.ChatUtil;
import me.xvepu.platform.utilities.StringReplacer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

@Getter
@Setter
public class NoticeBuilder {

    private final NoticeType noticeType;

    private final String message;

    public NoticeBuilder(NoticeType noticeType, String message) {
        this.noticeType = noticeType;
        this.message = message;
    }

    public NoticeBuilder send(@NonNull CommandSender commandSender) {
        this.sendMessage((Player) commandSender, this.message);
        return null;
    }

    public NoticeBuilder send(@NonNull CommandSender commandSender, @NonNull Map<String, Object> map) {
        this.sendMessage((Player) commandSender, StringReplacer.of(message)
                .with(map)
                .replace());
        return null;
    }

    private void sendMessage(@NonNull Player player, String message) {
        switch (noticeType) {
            case NOT_SEND -> {
                return;
            }
            case CHAT -> player.sendMessage(ChatUtil.fixColor(message));
            case ACTION_BAR -> ActionBar.sendActionBar(player, ChatUtil.fixColor(message));
            case SUBTITLE -> Titles.sendTitle(player, "", ChatUtil.fixColor(message));
            case TITLE -> Titles.sendTitle(player, ChatUtil.fixColor(message), "");
        }
    }
}

