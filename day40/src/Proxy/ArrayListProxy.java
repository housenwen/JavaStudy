package Proxy;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListProxy implements List<String> {
    //被代理的List集合
    private List<String> list;

    //通过构造方法将被代理的集合传入
    public ArrayListProxy(List<String> list) {
        this.list = list;
    }
    /*
        以下方法都是List接口中的方法：
        	允许被使用的方法：调用被代理对象的原方法
        	不允许被使用的方法：抛出异常
     */

    @Override
    public int size() {
        return this.list.size();
    }
    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }
    @Override
    public Iterator<String> iterator() {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }
    @Override
    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }
    @Override
    public boolean add(String s) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean remove(Object o) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }
    @Override
    public boolean addAll(Collection<? extends String> c) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean addAll(int index, Collection<? extends String> c) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean removeAll(Collection<?> c) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean retainAll(Collection<?> c) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public void clear() {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public String get(int index) {
        return this.list.get(index);
    }
    @Override
    public String set(int index, String element) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public void add(int index, String element) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public String remove(int index) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }
    @Override
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }
    @Override
    public ListIterator<String> listIterator() {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public ListIterator<String> listIterator(int index) {//不允许使用
        throw new UnsupportedOperationException();
    }
    @Override
    public List<String> subList(int fromIndex, int toIndex) {//不允许使用
        throw new UnsupportedOperationException();
    }
}
