package itheima06;

class Ability {
    private String name;//技能名字
    private double multiple;//技能对攻击增加的伤害倍数
    private int mpConsume;//技能消耗的mp

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMultiple() {
        return multiple;
    }

    public void setMultiple(double multiple) {
        this.multiple = multiple;
    }

    public int getMpConsume() {
        return mpConsume;
    }

    public void setMpConsume(int mpConsume) {
        this.mpConsume = mpConsume;
    }

    public Ability(String name, double multiple, int mpConsume) {
        this.name = name;
        this.multiple = multiple;
        this.mpConsume = mpConsume;
    }


}

