package GUI;

import java.awt.*;


import javax.swing.JPanel;

import POJO.JsonInfo;
import POJO.JsonObj;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class AnalyzeData extends ApplicationFrame
{
    public static DefaultCategoryDataset  dataset =null;
    private  static  AnalyzeData analyzeData = null;
    private  AnalyzeData(String title)
    {
        super(title);
        this.setContentPane(createPanel()); //构造函数中自动创建Java的panel面板
    }

    public static CategoryDataset createDataset() //创建柱状图数据集
    {
         dataset=new DefaultCategoryDataset();
        dataset.setValue(10,"a","周期编号");
        dataset.setValue(20,"b","车速");
        dataset.setValue(40,"c","舵机控制量");
        dataset.setValue(15,"d","a电机控制量");
        dataset.setValue(15,"e","b电机控制量");
        dataset.setValue(15,"f","中值");

        return dataset;
    }

    public static JFreeChart createChart(CategoryDataset dataset) //用数据集创建一个图表
    {
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("宋书", Font.BOLD, 26));

        standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 16));

        standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 16));
        ChartFactory.setChartTheme(standardChartTheme);

        JFreeChart chart= ChartFactory.createStackedBarChart3D("智能小车控制数据分析", "控制参数", "控制量", dataset, PlotOrientation.VERTICAL, true, true, false);
        chart.setTitle(new TextTitle("智能小车控制数据分析",new Font("宋体",Font.BOLD+Font.ITALIC,20)));

             // 2 ．2.1:设置中文 // x,y轴坐标字体
        Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
            // 2 ．3 Plot 对象 Plot 对象是图形的绘制结构对象
        CategoryPlot plot = (CategoryPlot)chart.getPlot();

        ValueAxis rangeAxis = plot.getRangeAxis();
            //设置最高的一个 Item 与图片顶端的距离
        rangeAxis.setUpperMargin(0.15);
            //设置最低的一个 Item 与图片底端的距离
        rangeAxis.setLowerMargin(0.15);
        plot.setRangeAxis(rangeAxis);

        StackedBarRenderer3D renderer=new StackedBarRenderer3D();


//设置每种柱的颜色
        renderer.setSeriesPaint(0, new Color(153, 153, 255));
        renderer.setSeriesPaint(1, new Color(204, 255, 255));
        renderer.setSeriesPaint(2, Color.GREEN);

//显示每个柱的数值，并修改该数值的字体属性
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setItemLabelFont(new Font("黑体",Font.PLAIN,9));
        renderer.setItemLabelsVisible(true);
        plot.setRenderer(renderer);


        return chart;
    }

    public static JPanel createPanel()
    {
        JFreeChart chart =createChart(createDataset());
        return new ChartPanel(chart); //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
    }
    public static void getData(JsonObj jsonInfo){

            if(analyzeData==null) {
                analyzeData = new AnalyzeData("智能小车数据分析");
                analyzeData.setSize(400,600);
                analyzeData.setLocation(550,100);
                analyzeData.pack();//以合适的大小显示
                analyzeData.setVisible(true);

            }
        dataset.setValue(jsonInfo.getConId(),"a","周期编号");
        dataset.setValue(jsonInfo.getSpeed(),"b","车速");
        dataset.setValue(jsonInfo.getPid(),"c","舵机控制量");
        dataset.setValue(jsonInfo.getPwmA(),"d","a电机控制量");
        dataset.setValue(jsonInfo.getPwmB(),"e","b电机控制量");
        dataset.setValue(jsonInfo.getZhongZhi(),"f","中值");
    }
    public static void main(String[] args)
    {
        AnalyzeData chart=new AnalyzeData("智能小车数据分析");
        chart.pack();//以合适的大小显示
        chart.setVisible(true);
        for(int i=0;i<100;i++){
            dataset.setValue(i+10,"a","周期编号");
            dataset.setValue(i+15,"b","车速");
            dataset.setValue(i+20,"c","舵机控制量");
            dataset.setValue(i+18,"d","电机控制量");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}