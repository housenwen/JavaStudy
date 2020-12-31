package itheima06;

abstract class Hero{//抽象的英雄
    private String name;//名称
    private int hp;//血量
    private int mp;//魔法值
    private int attack;//攻击力
    private int defenses;//防御力
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getMp() {
        return mp;
    }
    public void setMp(int mp) {
        this.mp = mp;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefenses() {
        return defenses;
    }
    public void setDefenses(int defenses) {
        this.defenses = defenses;
    }
    public Hero(String name, int hp, int mp, int attack, int defenses) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.attack = attack;
        this.defenses = defenses;
    }
    //说台词的行为
    public abstract void say();
    //发动技能具有共同的操作，抽取公共操作到方法中
    public void startAnility(Ability ability){
        //判断MP是否足够；如果不足，则技能发动失败；否则，成功发动技能攻击到目标，并减去相应MP
        int consume = ability.getMpConsume();
        if(this.mp < consume){
            System.out.println("魔法不足，技能不能发动！");
        }else{
            this.mp = consume;
            //计算伤害，等于攻击力乘以技能加成的倍数
            double sh = ability.getMultiple()*this.attack;
            System.out.println(this.name+"发动技能"+ability.getName()+
                    "，耗费"+consume+"点魔法，对敌人造成"+sh+"点攻击！");
        }
    }

    //发动一技能
    public abstract void startFirstAbility();
    //发动二技能
    public abstract void startSecondAbility();
    //发动三技能
    public abstract void startThirdAbility();
}