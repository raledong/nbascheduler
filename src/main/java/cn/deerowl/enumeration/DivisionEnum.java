package cn.deerowl.enumeration;

public enum DivisionEnum {

    //西部赛区
    SOUTH_WEST("西南赛区", 1),
    PACIFIC_DIVISION("太平洋赛区", 2),
    NORTH_WEST("西北赛区", 3),

    //东部赛区
    ATLANTIC("大西洋赛区", 4),
    SOUTH_EAST("东南赛区", 5),
    MID("中部赛区", 6);

    public static final DivisionEnum[] EAST_DIVISION_ENUMS = new DivisionEnum[]{
            ATLANTIC,
            SOUTH_EAST,
            MID
    };

    public static final DivisionEnum[] WEST_DIVISION_ENUMS = new DivisionEnum[]{
            SOUTH_WEST,
            PACIFIC_DIVISION,
            NORTH_WEST
    };

    private String desc;
    private int divisionCode;

    DivisionEnum(String desc, int divisionCode) {
        this.desc = desc;
        this.divisionCode = divisionCode;
    }

    public String getDesc() {
        return desc;
    }


    public int getDivisionCode() {
        return divisionCode;
    }

    public static DivisionEnum getDivisionByCode(int divisionCode){
        switch (divisionCode) {
            case 1: return DivisionEnum.SOUTH_WEST;
            case 2: return DivisionEnum.PACIFIC_DIVISION;
            case 3: return DivisionEnum.NORTH_WEST;
            case 4: return DivisionEnum.ATLANTIC;
            case 5: return DivisionEnum.SOUTH_EAST;
            case 6: return DivisionEnum.MID;
            default: return null;
        }
    }

    public static DivisionEnum getDivisionByDesc(String desc) {
        for (DivisionEnum divisionEnum : EAST_DIVISION_ENUMS) {
            if (divisionEnum.getDesc().startsWith(desc)) {
                return divisionEnum;
            }
        }

        for (DivisionEnum divisionEnum : WEST_DIVISION_ENUMS) {
            if (divisionEnum.getDesc().startsWith(desc)) {
                return divisionEnum;
            }
        }
        return null;
    }

}
