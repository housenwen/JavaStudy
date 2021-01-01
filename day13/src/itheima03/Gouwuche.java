package itheima03;

import java.util.ArrayList;

public class Gouwuche {

    ArrayList<Shangpin> list = new ArrayList<>();

    void tianjia(Shangpin shangpin) {
        System.out.println("加入 " + shangpin.getName() + " 成功");
        list.add(shangpin);
    }

    void dayin() {
        System.out.println("您选购的商品为:");
        for (int i = 0; i < list.size(); i++) {
            Shangpin goods = list.get(i);
            System.out.println("\t" + goods.getBianhao() + "," + goods.getName() + "," + goods.getPrice());
        }
    }

   public void Jisuan(){
            double off = 0; // 折扣价
            double sum = 0; // 原价
            for (int i = 0; i < list.size(); i++) {
                Shangpin goods = list.get(i);
                double price = goods.getPrice();
                sum += price;
                // 如果商品为电子产品,就打折计算
                if (goods instanceof Dianzi) {
                    price *= 0.88;
                }
                off += price;
            }
            System.out.println("------------------");
            System.out.println("原 价为:" + sum + " 元");
            System.out.println("折后价为:" + off + " 元");
        }
        }




