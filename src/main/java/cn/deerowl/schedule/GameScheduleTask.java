package cn.deerowl.schedule;

import cn.deerowl.common.webparser.player.PlayerWebParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GameScheduleTask {

    @Autowired
    private PlayerWebParser playerWebParser;

//    @Scheduled(cron="*/5 * * * * *" )
    public void updatePlayerInfo() {
        playerWebParser.getPlayer("https://nba.hupu.com/players/tysonchandler-326.html");
    }
}
