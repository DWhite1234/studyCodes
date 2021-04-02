package day18.codes;

import java.util.Arrays;

public class ArrayListTool<E> implements List<E>{
    private int size;

    private Object[] arr;


    public ArrayListTool() {
        this(5);
    }

    public ArrayListTool(int size) {
        this.arr = new Object[size];
    }

    @Override
    public void add(E e) {
        if (size >= arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[size++]=e;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder("[ ");
        int i=0;
        return str(i,builder).append("]").toString();
    }

    public StringBuilder str(int i,StringBuilder builder) {
        if (i ==size) {
            return builder;
        }
        builder.append(arr[i]);
        if (i<size-1) {
            builder.append(", ");
        }

        return str(++i, builder);
    }
}
