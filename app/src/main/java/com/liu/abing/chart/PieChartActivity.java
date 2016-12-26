package com.liu.abing.chart;

import android.os.Bundle;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;

import java.util.ArrayList;

/**
 * 项目名称：abing
 * 类描述： 饼状图表
 * 创建人：liubing
 * 创建时间：2016/12/26 10:52
 * 修改人：Administrator
 * 修改时间：2016/12/26 10:52
 * 修改备注：
 */
public class PieChartActivity extends BaseActivity {

    private PieChart pieChart;
    protected String[] mParties = new String[]{"我", "是", "女", "神", "哈哈"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        initView();
    }
    private void initView()
    {
        pieChart = (PieChart) findViewById(R.id.chart1);

        pieChart.setHoleRadius(50f); //半径
        pieChart.setTransparentCircleRadius(54f); // 半透明圈
        //pieChart.setHoleRadius(0);  //实心圆
        pieChart.setDescription(""); //描述
        pieChart.setDrawYValues(true); //是否显示Y轴的数据
        pieChart.setDrawXValues(true); //是否显示x轴的数据
        pieChart.setDrawCenterText(true);//饼状图中间可以添加文字
        pieChart.setDrawHoleEnabled(true);//是否绘制饼状图中间的圆

        pieChart.setRotationEnabled(true);// 可以手动旋转
        pieChart.setUsePercentValues(true);//显示百分比
        pieChart.setCenterText("女神驾到");//饼图中心的文字
        setData(mParties.length - 1, 100);
        pieChart.animateXY(1500, 1500); //饼状图的动画

        Legend le = pieChart.getLegend();
        //图例显示的位置
        le.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        le.setXEntrySpace(5f);
        le.setYEntrySpace(7f);
    }

    private void setData(int count, float range) {
        float mult = range;
        ArrayList<Entry> ylist = new ArrayList<Entry>();
        //随机给饼状图添加数据
        for (int i = 0; i < count + 1; i++) {
            ylist.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
        }
        //给饼状图添加数据名称
        ArrayList<String> xlist = new ArrayList<String>();
        for (int i = 0; i < count + 1; i++) {
            xlist.add(mParties[i % mParties.length]);
        }


        //添加数据到y轴的集合
        PieDataSet set1 = new PieDataSet(ylist, "数据集");
        //数据模块之间的间隔
        set1.setSliceSpace(1f);


        //随机添加颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        set1.setColors(colors);

        PieData data = new PieData(xlist, set1);
        pieChart.setData(data);

        pieChart.highlightValues(null);
        //更新饼图的显示
        pieChart.invalidate();

    }
}
