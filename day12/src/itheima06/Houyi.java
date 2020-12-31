package itheima06;

class Houyi extends Hero {

    public Houyi(String name, int hp, int mp, int attack, int defenses) {
        super("后羿", 3500, 2600, 160, 210);
    }

    @Override
    public void say() {
        System.out.println(this.getName() + "说：周日，被我射熄火了，所以今天是周一！");
    }

    @Override
    //表示后羿发动一技能
    public void startFirstAbility() {
        startAnility(new Ability("炙热之风", 1.5, 10));
    }

    @Override
    //表示后羿发动二技能
    public void startSecondAbility() {
        startAnility(new Ability("燎原箭雨", 2, 20));
    }

    @Override
    //表示后羿发动三技能
    public void startThirdAbility() {
        startAnility(new Ability("惩戒射击", 3, 30));
    }
}
