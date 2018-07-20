package abing.liu.com.abing.map;


import abing.liu.com.abing.SingleCaseDemo;
//纯手写HashMap集合 基于单向链表+数组实现
public class LBHashMap<K,V> implements LBMap<K,V> {

    //1.定义table存放HashMap数组元素，默认是没有初始化容器的
    private Node<K,V>[] table=null;
    //2.实际用到table存储容量的大小
    private int size;
    //3.HashMap默认负载因子，负载因子越小，hash冲突几率越低
    private float DEFAULT_LOAD_FACTOR=0.75f;
    //4.table默认初始大小16
    private static int DEFAULT_INITIAL_CAPACITY=16;
    @Override
    public V put(K key, V value) {
        //1判断table的数组大小是否为空（如果为空的情况下，做初始化操作）
        if (table==null){
            table=new Node[DEFAULT_INITIAL_CAPACITY];
        }
        //2.hashMap扩容机制为什么要扩容？扩容数组之后，有什么影响？
        //实际存储大小=负载因子*初始容量=DEFAULT_LOAD_FACTOR(0.75)*DEFAULT_INITIAL_CAPACITY(16)=12
        //如果size>12的时候就需要开启扩容数组，扩容数组大小之前两陪
        if (size>(DEFAULT_INITIAL_CAPACITY*DEFAULT_LOAD_FACTOR)){
            //需要开始对table进行数组扩容
            resize();
        }
        //3.计算hash值指定下标位置
        int index=getIndex(key,DEFAULT_INITIAL_CAPACITY);
        Node<K,V> node=table[index];
        if (node==null){
            //没有发生hash冲突问题  index冲突
            node=new Node<K, V>(key,value,null);
            size++;
        }else {
            Node<K,V> newNode=node;
            while (newNode!=null){
                ////已经发生hash冲突问的key,,直接添加（冲突node）到前面了 不是往后面加
                if (newNode.getKey().equals(key)||newNode.getKey()==value){
                    //hashCode相同，而且equals相等情况 说明是同一个对象 修改值
                    return newNode.setValue(value);
                }else {
                    //继续添加，排在前面hashcode  取模余数相同 index存放在链表或者hashcode相同，但是对象不同
                    //新的node的next 是原来的node
                    if (newNode.next==null){
                        //说明遍历到最后一个node，添加node
                        node=new Node<>(key,value,node);
                        size++;
                    }
                }
            newNode=newNode.next;
            }
        }
        table[index]=node;
        return null;
    }
    //对table进行扩容
    private void resize(){
        //1.生成新的table式之前的两倍的大小 DEFAULT_INITIAL_CAPACITY*2
        Node<K,V>[] newTable=new Node[DEFAULT_INITIAL_CAPACITY << 1];
        //重新计算index索引，存放在新的table里面
        for (int i=0;i<table.length;i++){
            //存放在之前的table原来的node
            Node<K,V> oldNode=table[i];
            while (oldNode!=null){
                //赋值为空   为了垃圾回收机制能够回收  将之前的node删除
                table[i]=null;
                //存放在之前的table 原来的key
                K oldK=oldNode.key;
                //重新计算index
                int index=getIndex(oldK,newTable.length);
                //存放在之前的table原来的node next
                Node<K,V> oldNext=oldNode.next;
                //如果index下标在新newTable发生相同的index时候，以链表进行存储
                //原来的node的下一个是最新的，（原来的node存放在新的node下一个）
                oldNode.next=newTable[index];
                //将之前的node赋值给newTable[index]
                newTable[index]=oldNode;
                //判断是否继续循环遍历
                oldNode=oldNext;
            }
        }
        //3.将newTable赋值给老的table
        table=newTable;
        DEFAULT_INITIAL_CAPACITY=newTable.length;
        newTable=null;//赋值为空 为了垃圾回收机制能够回收
    }
    public int getIndex(K k,int length){
        int hashCode=k.hashCode();
        int index=hashCode%length;
        return index;
    }
    @Override
    public V get(K k) {
        Node<K,V> node=getNode(table[getIndex(k,DEFAULT_INITIAL_CAPACITY)],k);
        return node==null?null:node.value;
    }
    public Node<K,V> getNode(Node<K,V> node,K k){
        while (node!=null){
            if (node.getKey().equals(k)){
                return node;
            }
            node=node.next;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    //定义节点
    class Node<K,V> implements Entry<K,V>{
        //存放Map集合Key
        private K key;
        //存放Map集合Value
        private V value;
        //下一个节点Node
        private Node<K,V> next;
        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            //设置新值的返回老的值
            V oldValue=this.value;
            this.value=value;
            return oldValue;
        }
        public Node(K key,V value,Node<K,V> next){
            super();
            this.key=key;
            this.value=value;
            this.next=next;
        }
    }

    //测试方法 ，打印所有的链表元素
    void print(){
        for (int i=0;i<table.length;i++){
            Node<K,V> node=table[i];
            System.out.print("下标位置[" + i + "]");
            while (node!=null){
                System.out.print("[ key:" + node.getKey() + ",value:" + node.getValue() + "]");
                node=node.next;

            }
            System.out.println();
        }
    }

//        lbHashMap.put("4号", "1号");// 3
//        lbHashMap.put("6号", "1号");// 4
//        lbHashMap.put("7号", "1号");
//        lbHashMap.put("14号", "1号");
//        System.out.println("扩容前数据....");
//        lbHashMap.print();
//        System.out.println("扩容后数据....");
//        lbHashMap.put("31号", "1号");
//        lbHashMap.put("66号", "123466666");
//        lbHashMap.print();
//    }

}
