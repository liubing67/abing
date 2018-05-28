package abing.liu.com.abing;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2018-5-26 19:18
 * 修改人：Administrator
 * 修改时间：2018-5-26 19:18
 * 修改备注：
 */
public class SingleCaseDemo {

    //枚举创建单例
    private SingleCaseDemo() {
    }
    public static SingleCaseDemo getInstance() {
        return SingleDemoEnum.INSTANCE.getInstance();
    }
    //枚举本身就是属于单例
    enum SingleDemoEnum {
        INSTANCE;
        private SingleCaseDemo singleCaseDemo;
        SingleDemoEnum() {
            singleCaseDemo = new SingleCaseDemo();
        }
        public SingleCaseDemo getInstance() {
            return this.singleCaseDemo;
        }
    }
    public static void main(String arg[]) {
        SingleCaseDemo s1 = SingleCaseDemo.getInstance();
        SingleCaseDemo s2 = SingleCaseDemo.getInstance();
        System.out.println(s1 == s2);
    }
}




//    //懒汉式
//    //类初始化时不会创建该对象，真正需要的时候才会加载（创建），天生线程不安全，需要解决线程安全问题，所以效率比较低
//
//    private static SingleCaseDemo singleCaseDemo;
//
//    //1.将构造函数私有化
//    private SingleCaseDemo() {
//    }
//
//    public static synchronized SingleCaseDemo getInstance() {
//        if (singleCaseDemo == null) {
//            singleCaseDemo = new SingleCaseDemo();
//        }
//        return singleCaseDemo;
//    }
//
//    public static void main(String arg[]) {
//        SingleCaseDemo s1 = SingleCaseDemo.getInstance();
//        SingleCaseDemo s2 = SingleCaseDemo.getInstance();
//        System.out.println(s1 == s2);
//    }
//}


//    //饿汉式
//    //类初始化的时候，就会创建对象，天生线程安全，调用效率比较高，如果不使用对象的时候，会浪费内存
//
//    //new 出来的对象放进永久区，不会被垃圾回收器回收掉
//    private static final SingleCaseDemo singleCaseDemo=new SingleCaseDemo();
//    //1.将构造函数私有化
//    private SingleCaseDemo(){
//    }
//    //不会产生线程安全问题 因为加了  static final
//    public static SingleCaseDemo getInstance(){
//        return singleCaseDemo;
//    }

//}
