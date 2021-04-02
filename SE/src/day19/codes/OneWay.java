package day19.codes;

public class OneWay<E> {

    //定义头结点
    private Node<E> head;

    //定义元素数量
    private int size;

    public void add(E e) {
        //添加时需要创建一个新的节点
        Node<E> newNode = new Node<>(e, null);
        //是否有头结点
        if (head.next==null) {
            head=newNode;
        }else{
            //如果有头结点,找到最后一个节点,添加在最后一个节点的后面
        }
    }

    class Node<E>{
        private E data;

        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
