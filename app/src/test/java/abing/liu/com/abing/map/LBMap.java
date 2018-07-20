package abing.liu.com.abing.map;

public interface LBMap<K,V> {

    //向集合中插入数据
    public V put(K k, V v);

    //根据K从map里边获取数据
    public V get(K k);

    //获取集合的个数
    public int size();

    //Entry相当于node
    interface Entry<K,V>{

        K getKey();
        V getValue();
        V setValue(V value);
    }
}
