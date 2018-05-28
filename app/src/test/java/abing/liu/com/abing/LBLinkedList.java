package abing.liu.com.abing;

public class LBLinkedList<E> {
    //集合的长度
    private int size;
    //第一个元素，方便查询
    public Node first;
    //最后一个元素，方便添加
    public Node last;
    public class Node{
        Object item;
        Node next;
        Node prev;

    }

    /**
     * 获取集合的长度
     * @return
     */
    public  int getSize(){
        return size;
    }

    /**
     * 添加数据
     * @param e
     */
    public void add(E e){
        //创建节点
        Node node=new Node();
        //给节点赋值
        node.item=e;
        if (first==null){
            //添加第一个元素
            //给第一个元素赋值node节点赋值
            first=node;
            //如果只有一个元素时，第一个元素头和尾都属于自己
        }else {
            //添加第二个或者以上数据
            node.prev=last;
            //将上一个元素的next赋值
            last.next=node;
        }
        last=node;
        size++;
    }
    public void add(int index,E e){
        //1.下标检测
        checkElementIndex(index);
//        /node3（c）节点 a b c d      在第二个位置添加f(2,f)
        LBLinkedList.Node oldNode=getNode(index);
        if(oldNode!=null){
            //获取node2（b）
            LBLinkedList.Node oldPreNode=oldNode.prev;
            Node newNode=new Node();
            newNode.item=e;
            //将node3（c）赋值给新节点的下一个节点
            newNode.next=oldNode;
            if (oldPreNode==null){
                first=newNode;
            }else {
                //将新节点的上一个节点变为node2（b）
                newNode.prev=oldPreNode;
                //将原节点的下一个节点变为新节点
                oldPreNode.next=newNode;
            }
            //将node3（c）的上一个节点变为 node3(f)
            oldNode.prev=newNode;
            size++;
        }

    }
    /**
     * 查询
     * @param index
     */
    public Object get(int index){
        checkElementIndex(index);
        return getNode(index).item;
    }
    public Node getNode(int index){
        checkElementIndex(index);

        if (index < (size >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * 指定下标删除
     */
    public void remove(int index){
        checkElementIndex(index);
        //1.先获取当前删除node2的节点
        LBLinkedList.Node oldNode=getNode(index);
        if (oldNode!=null){
            //2获取删除元素的上下节点
//            /node3
            LBLinkedList.Node oldNextNode=oldNode.next;
//            /node1
            LBLinkedList.Node oldPrevNode=oldNode.prev;
            //将node1的下个节点变为node3
            if (oldPrevNode==null){
                //删除一个元素
                first=oldNextNode;
            }else {
                oldPrevNode.next=oldNextNode;
                oldNode.prev=null;
            }
            //将node3的上一个节点变为node1
            if (oldNextNode==null){
                last=oldPrevNode;
            }else {
                oldNextNode.prev=oldPrevNode;
                oldNode.next=null;
            }
            oldNode.item=null;//让垃圾回收机制回收
            size--;
        }
    }




    /*
    检验下标
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("查询越界啦！");
    }
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public static void main(String[] args) {

        LBLinkedList<String> list=new LBLinkedList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        System.out.println(list.getSize());
//        System.out.println(list.first.next.item);
//        System.out.println(list.first.next.next.item);
//        System.out.println(list.first.next.next.next.item);
//        list.remove(2);
        list.add(2,"F");
        System.out.println(list.getSize());
        for (int i=0;i<list.getSize();i++){
            System.out.println(list.get(i));
        }
    }
//    （1）ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
//    （2）对于随机访问get和set，ArrayList要优于LinkedList，因为LinkedList要移动指针。
//     （3）对于新增和删除操作add和remove，LinkedList比较占优势，因为ArrayList要移动数据。

}
