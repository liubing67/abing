package abing.liu.com.abing;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.text.DecimalFormat;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2018-3-5 9:38
 * 修改人：Administrator
 * 修改时间：2018-3-5 9:38
 * 修改备注：
 */
public class TestJavaDemo01 {

    public static  void main(String[] args)throws InterruptedException{
        byte[] bytes01=new byte[1*1024*1024];
        System.out.println("分配了1M内存");
       Logger.d("分配了1M内存");
        jvmInfo();
        Thread.sleep(3000);
        byte[] bytes02=new byte[4*1024*1024];
        System.out.println("分配了4M内存");
        jvmInfo();
    }
    public static void jvmInfo(){
        //最大内存配置信息;
        long maxMemory=Runtime.getRuntime().maxMemory();
        System.out.println("maxMemory"+maxMemory+","+toM(maxMemory)+"M");
        //当前空闲内存配置信息;
        long freeMemory=Runtime.getRuntime().freeMemory();
        System.out.println("freeMemory"+freeMemory+","+toM(freeMemory)+"M");
        //已使用内存配置信息;
        long totalMemory=Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory"+totalMemory+","+toM(totalMemory)+"M");
    }
    private static String toM(long memory){
        float num=(float) memory/(1024*1024);
        DecimalFormat df=new DecimalFormat("0.00");
        String s=df.format(df);

        return s;
    }


    //GC垃圾回收之前会调用
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
