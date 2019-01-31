package cn.deerowl.schedule;

import cn.deerowl.service.web.PlayerWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PlayerScheduleTask {

    @Autowired
    private PlayerWebService playerWebService;

    public void getPlayer() {
        playerWebService.getPlayerAndSave("https://nba.hupu.com/players/tysonchandler-326.html");
    }
}
