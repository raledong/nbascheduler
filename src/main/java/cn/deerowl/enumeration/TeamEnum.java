package cn.deerowl.enumeration;

public enum TeamEnum {
    SPURS("spurs", "马刺", "圣安东尼奥马刺"),
    GRIZZLIES("grizzlies", "灰熊", "孟菲斯灰熊"),
    PELICANS("pelicans", "鹈鹕", "新奥尔良鹈鹕"),
    ROCKETS("rockets", "火箭", "休斯顿火箭"),
    MAVERICKS("mavericks", "独行侠", "达拉斯独行侠"),

    WARRIORS("warriors", "勇士", "金州勇士"),
    CLIPPERS("clippers", "快船", "洛杉矶快船"),
    KINGS("kings", "国王", "萨克拉门托国王"),
    LAKERS("lakers", "湖人", "洛杉矶湖人"),
    SUNS("suns", "太阳", "菲尼克斯太阳"),

    BLAZERS("blazers", "开拓者", "波特兰开拓者"),
    NUGGETS("nuggets", "掘金", "丹佛掘金"),
    THUNDER("thunder", "雷霆", "俄克拉荷马城雷霆"),
    JAZZ("jazz", "爵士", "犹他爵士"),
    TIMBERWOLVES("timberwolves", "森林狼", "明尼苏达森林狼"),

    RAPTORS("raptors", "猛龙", "多伦多猛龙"),
    CELTICS("celtics", "凯尔特人", "波士顿凯尔特人"),
    SEVENTYSIXERS("76ers", "76人", "费城76人"),
    NETS("nets", "篮网", "布鲁克林篮网"),
    KNICKS("knicks", "尼克斯", "纽约尼克斯"),

    HORNETS("hornets", "黄蜂", "夏洛特黄蜂"),
    MAGIC("magic", "魔术", "奥兰多魔术"),
    HEAT("heat", "热火", "迈阿密热火"),
    HAWKS("hawks", "老鹰", "亚特兰大老鹰"),
    WIZARDS("wizards", "奇才", "华盛顿奇才"),

    BUCKS("bucks", "雄鹿", "密尔沃基雄鹿"),
    PACERS("pacers", "步行者", "印第安纳步行者"),
    PISTONS("pistons", "活塞", "底特律活塞"),
    BULLS("bulls", "公牛", "芝加哥公牛"),
    CAVALIERS("cavaliers", "骑士", "克利夫兰骑士");

    /**
     * 英文名
     */
    private String ename;

    /**
     * 中文名
     */
    private String cname;

    /**
     * 中文名全称
     */
    private String fullCname;

    TeamEnum(String ename, String cname, String fullCname) {
        this.ename = ename;
        this.cname = cname;
        this.fullCname = fullCname;
    }

    public String getEname() {
        return ename;
    }

    public String getCname() {
        return cname;
    }

    public String getFullCname() {
        return fullCname;
    }

    public static TeamEnum[] getAllTeams() {
        return new TeamEnum[]{
                SPURS,
                GRIZZLIES,
                PELICANS,
                ROCKETS,
                MAVERICKS,

                WARRIORS,
                CLIPPERS,
                KINGS,
                LAKERS,
                SUNS,

                BLAZERS,
                NUGGETS,
                THUNDER,
                JAZZ,
                TIMBERWOLVES,

                RAPTORS,
                CELTICS,
                SEVENTYSIXERS,
                NETS,
                KNICKS,

                HORNETS,
                MAGIC,
                HEAT,
                HAWKS,
                WIZARDS,

                BUCKS,
                PACERS,
                PISTONS,
                BULLS,
                CAVALIERS
        };
    }

    public static TeamEnum getByName(String name) {
        for (TeamEnum teamEnum : getAllTeams()) {
            if (teamEnum.getCname().equals(name)
                    || teamEnum.getEname().equals(name)
                    || teamEnum.getFullCname().equals(name)) return teamEnum;
        }
        return null;
    }

}
