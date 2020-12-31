package itheima06;

class LuBan extends Hero {//表示鲁班七号

    public LuBan() {
        super("鲁班七号", 3000, 2000, 150, 200);
    }

    @Override
    public void say() {
        System.out.println(this.getName() + "说：借你们肉体实验新发明的威力！");
    }

    @Override
    //表示鲁班发动一技能
    public void startFirstAbility() {
        startAnility(new Ability("河豚手雷", 1.5, 10));
    }

    @Override
    //表示鲁班发动二技能
    public void startSecondAbility() {
        startAnility(new Ability("无敌鲨鱼炮", 2.5, 25));
    }

    @Override
    //表示鲁班发动三技能
    public void startThirdAbility() {
        startAnility(new Ability("空中支援", 3, 40));
    }
}
