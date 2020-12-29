package itheima02;

import java.util.ArrayList;

/*
 * 分析以下需求，并用代码实现
		定义MyArrays类，该类中有以下方法，方法描述如下：
		1.public static void reverse(ArrayList<Integer> list);
			参数ArrayList<Integer> list:要进行操作的集合对象
			要求：对list集合对象中的元素进行反转(第一个和最后一个交换，第二个和倒数第二个交换，第三个和倒数第三个交换...)
		2.public static Integer max(ArrayList<Integer> list);
			参数ArrayList<Integer> list:要进行操作的集合对象
			要求：求出list集合对象中的最大值并返回
		3.public static Integer min(ArrayList<Integer> list);
			参数ArrayList<Integer> list:要进行操作的集合对象
			要求：求出list集合对象中的最小值并返回

		4.public static int indexOf(ArrayList<Integer> list,Integer i);
			参数ArrayList<Integer> list:要进行操作的集合对象
			参数Integer i:需要在集合中查找的元素
			要求：求出元素i在list集合中第一次出现的索引，如果没有返回-1
		5.public static void replaceAll(ArrayList<Integer> list,Integer oldValue,Integer newValue);
			参数ArrayList<Integer> list:要进行操作的集合对象
			参数Integer oldValue:需要被替换掉的原值
			参数Integer newValue：替换后的新值
			要求：将list集合中的所有值为oldValue的元素替换为newValue
 */
public class text02 {
    /*
     * 1.public static void reverse(ArrayList<Integer> list);
            参数ArrayList<Integer> list:要进行操作的集合对象
            要求：对list集合对象中的元素进行反转(第一个和最后一个交换，第二个和倒数第二个交换，第三个和倒数第三个交换...)
     */
    public static void reverse(ArrayList<Integer> list){
        for (int i = 0, j=list.size()-1; i < j; i++,j--) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    /*
     *	2.public static Integer max(ArrayList<Integer> list);
        参数ArrayList<Integer> list:要进行操作的集合对象
        要求：求出list集合对象中的最大值并返回
     */
    public static Integer max(ArrayList<Integer> list){
        int max = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        return max;
    }

    /*
     *	3.public static Integer min(ArrayList<Integer> list);
            参数ArrayList<Integer> list:要进行操作的集合对象
            要求：求出list集合对象中的最小值并返回
     */
    public static Integer min(ArrayList<Integer> list){
        int min = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
            }
        }
        return min;
    }

    /*
     * 	4.public static int indexOf(ArrayList<Integer> list,Integer i);
        参数ArrayList<Integer> list:要进行操作的集合对象
        参数Integer i:需要在集合中查找的元素
        要求：求出元素i在list集合中第一次出现的索引，如果没有返回-1
     */
    public static int indexOf(ArrayList<Integer> list,Integer i){
        int index = -1;
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) == i) {
                index = j;
                break;
            }
        }
        return index;
    }

    /*
     * 5.public static void replaceAll(ArrayList<Integer> list,Integer oldValue,Integer newValue);
            参数ArrayList<Integer> list:要进行操作的集合对象
            参数Integer oldValue:需要被替换掉的原值
            参数Integer newValue：替换后的新值
            要求：将list集合中的所有值为oldValue的元素替换为newValue
     */
    public static void replaceAll(ArrayList<Integer> list,Integer oldValue,Integer newValue){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == oldValue) {
                list.set(i, newValue);
            }
        }
    }
}

