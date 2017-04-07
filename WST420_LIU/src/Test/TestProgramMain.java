package Test;

import gxinterface.GxLinearService;
import gxinterface.GxPrecisionService;
import gxinterface.GxTrueenessPatientSample;
import gxinterface.GxTrueenessRefMaterial;
import com.precision.*;
import com.base.*;
import com.trueness.*;
import com.linear.*;


/**
 * 测试
 * @author sungiant
 *
 */
public class TestProgramMain 
{
	static void PrintBase(BaseVerification pv)
	{
		System.out.println("数据之和:\t"+Print4.Print(pv.GetSums(),0));
        System.out.println("数据均值:\t"+Print4.Print(pv.GetAverages(),3));
        System.out.println("数据标准差:\t"+Print4.Print(pv.GetStandardDeviations()));
        System.out.println("批内方差:\t"+Print4.Print(pv.GetSampleStandardDeviations()));
        System.out.println(("变异系数:\t" + Print4.PrintPercentage(pv.GetCV(),1))); 
        System.out.println("离差平方和:\t"+Print4.Print(pv.GetSumOfSquares()));
	}
    public static void main(String[] args)
    {			
        System.out.println("***************************精密度GeneXus接口  start ***************************");
        System.out.println("");
        
        
        
        //PrecisionVerification pv=new PrecisionVerification(2.0);
        Precision pv=new Precision(2.0);
        pv.DecimalBits=3;	//设置小数位数
        pv.setFalseRejectionRate(0.05, 2);	//设置假排除率和检测水平
        pv.FalseRejectionRate=0.05;
        pv.DetectionLevel=2;
        //pv.SetLabData("140 140 140;138 139 138;143 144 144;143 143 142;142 143 141"); //添加样本数据
        //
        //pv.SetLabData("140 139 138 138 140;140 143 141 143 137;140 138 136 141 136;141 144 142 143 144;139 140 141 138 141"); //Sample A.pdf
        pv.SetLabData("606 627 621 606 620;612 610 611 595 630;649 626 636 639 648;615 633 605 616 625;622 632 646 619 623");	//Sample B.pdf
        System.out.println("------精密度实验------");
        
        PrintBase(pv);
        
        System.out.println(("批间方差:\t" + Print4.Print(pv.BetweenGroupVariance(),4)));
        System.out.println(("批间标准差:\t" + Print4.Print(pv.BetweenGroupSD(),4)));
        System.out.println(("总均值:\t" + Print4.Print(pv.OverallMean(),2)));
        System.out.println(("重复自由度:\t" + Print4.Print(pv.DegreeOfFreedom(0),2)));
        System.out.println(("重复标准差:\t" + Print4.Print(pv.StandardDeviation(0),4)));
        System.out.println(("重复验证值:\t" + Print4.Print(pv.MeasurementValue(0),2)));
        System.out.println(("期间自由度:\t" + Print4.Print(pv.DegreeOfFreedom(1),2)));
        System.out.println(("期间标准差:\t" + Print4.Print(pv.StandardDeviation(1),4)));
        System.out.println(("期间验证值:\t" + Print4.Print(pv.MeasurementValue(1),2)));    
        
        System.out.println(("SDI参数:\t" + Print4.Print(pv.getSDI(),2)));   
        System.out.println("");
        //System.out.println(pv.GroupStandardDeviation());       
        
        /*
        GxPrecisionService gxPrecision = new GxPrecisionService();
        gxPrecision.CreatePrecisionObj("2.0", "2");
        gxPrecision.LoadLabData("140 140 140");
        gxPrecision.LoadLabData("138 139 138");
        gxPrecision.LoadLabData("143 144 144");
        gxPrecision.LoadLabData("143 143 142");
        gxPrecision.LoadLabData("142 143 141");
        
        int batchIndex = 2;
        
        System.out.println("------第三批输出------");
        System.out.println("------第三批输出：和                        ："+gxPrecision.GetLabDataSum(batchIndex,0));
        System.out.println("------第三批输出：均值                     ："+gxPrecision.GetLabDataAverage(batchIndex,2));
        System.out.println("------第三批输出：1均值之差            ："+gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex,0,2));
        System.out.println("------第三批输出：1均值之差的平方  ："+gxPrecision.GetMatchPow2(gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex,0,2), 4));
        System.out.println("------第三批输出：2均值之差            ："+gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex, 1,2));
        System.out.println("------第三批输出：2均值之差的平方 ："+gxPrecision.GetMatchPow2(gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex, 1,2),4));
        System.out.println("------第三批输出：3均值之差            ："+gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex, 2,2));
        System.out.println("------第三批输出：3均值之差的平方  ："+gxPrecision.GetMatchPow2(gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex, 2,2),4));
        System.out.println("------第三批输出：均值之差的平方和："+gxPrecision.GetLabDataBatchAverageDiffPow2Sum(batchIndex,4));
        System.out.println("------第三批输出：批内方差              ："+gxPrecision.GetLabDataIntraGroupVariance(batchIndex,4));
        System.out.println("------第三批输出：总均值之差           ："+gxPrecision.GetLabDataAverageGroupAverageDiff(batchIndex,2));
        System.out.println("------第三批输出：总均值之差平方    ："+gxPrecision.GetMatchPow2(gxPrecision.GetLabDataAverageGroupAverageDiff(batchIndex,2),4));
        System.out.println("------重复标准差                               ："+gxPrecision.RepeatedStandardDeviation());
        System.out.println("------总均值                                     ："+gxPrecision.GroupAverage(2));
        System.out.println("------批间方差                                  ："+gxPrecision.BetweenGroupVariance(4));
        System.out.println("------期间标准差                              ："+gxPrecision.GroupStandardDeviation());
        System.out.println("------自由度                                     ："+gxPrecision.Freedom());
        System.out.println("------验证值                                     ："+gxPrecision.PrecisionValue());
        */
        System.out.println("");
        System.out.println("***************************精密度GeneXus接口  end ***************************");
        System.out.println("");
        System.out.println("***************************正确度GeneXus接口  start ***************************");
        System.out.println("");
        
        System.out.println("**** 患者样本 ****");
        AccuracyFromPatientSample p2=new AccuracyFromPatientSample(2.0);
        p2.FalseRejectionRate=0.01;
        p2.Add(new LabData("76 127 256 303 29 345 42 154 398 93 240 72 312 99 375 168 59 183 213 436"));
        p2.Add(new LabData("77 121 262 294 25 348 41 154 388 92 239 69 308 101 375 162 54 185 204 431"));
        //PrintBase(p2);
        System.out.println("绝对偏移:\t"+Print4.Print(p2.AbsoluteOffset()));
        System.out.println("绝对偏移数组:\t"+Print4.Print(p2.AbsoluteOffsetArray(),0));
        System.out.println("绝对偏移标准差:\t"+Print4.Print(p2.StandardDeviation(),4));
        System.out.println("绝对偏移区间:\t["+Print4.Print(p2.MinOfInterval())+"-->"+Print4.Print(p2.MaxOfInterval())+"]");
        System.out.println("相对偏移:\t"+Print4.Print(p2.RelativeOffset()));
        System.out.println("相对偏移数组:\t"+Print4.Print(p2.RelativeOffsetArray()));
        System.out.println("相对偏移标准差:\t"+Print4.Print(p2.RelativeStandardDeviation(),4));
        System.out.println("相对偏移区间:\t["+Print4.Print(p2.RelativeMinOfInterval())+"-->"+Print4.Print(p2.RelativeMaxOfInterval())+"]");
        
        GxTrueenessPatientSample gxPatientSample = new GxTrueenessPatientSample();
        gxPatientSample.CreateTrueenessPatientSample("2.0", "2");
        gxPatientSample.LoadLabData("76 127 256 303 29 345 42 154 398 93 240 72 312 99 375 168 59 183 213 436");
        gxPatientSample.LoadLabData("77 121 262 294 25 348 41 154 388 92 239 69 308 101 375 162 54 185 204 431");
        
        int dataIndex = 2;
        System.out.println("------两个方法间的绝对偏移                                         ："+gxPatientSample.AbsoluteOffset(2));
        System.out.println("------第三个样本测量结果在两个方法间的绝对偏移      ："+gxPatientSample.GetLabDataBi(dataIndex, 0));
        System.out.println("------第三个样本与两个方法间的绝对偏移之差             ："+gxPatientSample.GetLabDataAbsoluteOffsetDiff(dataIndex, 1));
        System.out.println("------第三个样本与两个方法间的绝对偏移之差的平方   ："+gxPatientSample.GetMatchPow2(gxPatientSample.GetLabDataAbsoluteOffsetDiff(dataIndex, 1),2));
        System.out.println("------绝对偏移标准差                                                   ："+gxPatientSample.StandardDeviation(2));
        System.out.println("------验证区间下限                                                       ："+gxPatientSample.MinOfInterval(2));
        System.out.println("------验证区间上限                                                       ："+gxPatientSample.MaxOfInterval(2));
        
        System.out.println("");
        
        System.out.println("**** 参考物质 ****");
        AccuracyFromRefMaterial p3=new AccuracyFromRefMaterial();
        p3.FalseRejectionRate=0.01;
        p3.XfromRef=40;
        p3.Sprogram=1.73;
        p3.LabsCount=135;
        p3.SetLabData("37 38;39 37;38 36;39 38;38 37");
        System.out.println("测量偏移值:\t"+Print4.Print(p3.Deviant()));
        System.out.println("自由度:\t"+Print4.Print(p3.Freedom()));
        System.out.println("总均值:\t"+Print4.Print(p3.GroupAverage()));
        System.out.println("标准差:\t"+Print4.Print(p3.StandardDeviation(),4));
        System.out.println("不确定度:\t"+Print4.Print(p3.Uncertainty(),4));
        System.out.println("验证区间:\t["+Print4.Print(p3.MinOfInterval())+"-->"+Print4.Print(p3.MaxOfInterval())+"]");
        /*
        GxTrueenessRefMaterial gxRefMaterial = new GxTrueenessRefMaterial();
        String xfromRef = "40";//参考物质赋值 单位mg/dL,默认值40
        String sprogram = "1.73";//室间质评结果标准差，默认值1.73
        String labsCount = "135";//参加室间质评的实验室数量，默认值135
        String falseRejectionRate = "0.01";//假排除率，默认值0.01
        gxRefMaterial.CreateTrueenessRefMaterial(xfromRef, sprogram, labsCount, falseRejectionRate);
        
        gxRefMaterial.LoadLabData("37 38");
        gxRefMaterial.LoadLabData("39 37");
        gxRefMaterial.LoadLabData("38 36");
        gxRefMaterial.LoadLabData("39 38");
        gxRefMaterial.LoadLabData("38 37");
        
        batchIndex = 2;
        int dataIndex = 0;
        System.out.println("------第三批输出：第一次X5之差                ："+gxRefMaterial.GetLabDataEveryResultGroupAverageDiff(batchIndex, dataIndex, 1));
        System.out.println("------第三批输出：第一次X5之差平方         ："+gxRefMaterial.GetMatchPow2(gxRefMaterial.GetLabDataEveryResultGroupAverageDiff(batchIndex, dataIndex, 1),2));
        System.out.println("------总均值                                               ："+gxRefMaterial.GroupAverage(1));
        System.out.println("------参考物质测量偏移值                           ："+gxRefMaterial.Deviant(1));
        System.out.println("------标准差                                               ："+gxRefMaterial.StandardDeviation(3));
        System.out.println("------参考物质测量不确定度                        ："+gxRefMaterial.Uncertainty(3));
        System.out.println("------验证区间下限                                      ："+gxRefMaterial.MinOfInterval(2));
        System.out.println("------验证区间上限                                      ："+gxRefMaterial.MaxOfInterval(2));
        */
        
        System.out.println("");
        System.out.println("***************************正确度GeneXus接口  end ***************************");
        System.out.println("");
        System.out.println("***************************线性GeneXus接口  start ***************************");
        System.out.println("");
        
        LinearRegression l=new LinearRegression();
        l.DecimalBits=3;        
        l.SetLabData("4.7 7.8 10.4 13.0 15.5;4.6 7.6 10.2 13.1 15.3");
        l.SetX(new LabData("1 2 3 4 5"));        
        System.out.println("方程:\ty="+Print4.Print(l.Slope(),3)+"x+"+Print4.Print(l.Origin(),3));
        System.out.println("相关系数:\t"+Print4.Print(l.Relation()*l.Relation(),4));
        
        /*
        GxLinearService gxLinear = new GxLinearService();
        gxLinear.CreateLinearRegression();
        gxLinear.LoadLabData("4.7 7.8 10.4 13.0 15.5");
        gxLinear.LoadLabData("4.6 7.6 10.2 13.1 15.3");
        gxLinear.LoadX("1 2 3 4 5");
        System.out.println("y="+gxLinear.Slope(3)+"x+ "+gxLinear.Origin(3)+" and R*R is "+gxLinear.GetMatchPow2(gxLinear.Relation(3),4)+" .");
        System.out.println("------稀释度3：均值                ："+gxLinear.GetLabDataTwoResultAverage(2,2));
        */
        
        System.out.println("");
        System.out.println("***************************线性GeneXus接口  end ***************************");
    }
}
