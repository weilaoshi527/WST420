package Test;

import com.base.LabData;

import gxinterface.GxLinearService;
import gxinterface.GxPrecisionService;
import gxinterface.GxTrueenessPatientSample;
import gxinterface.GxTrueenessRefMaterial;

/**
 * 测试
 * @author sungiant
 *
 */
public class TestProgramMain 
{
    public static void main(String[] args)
    {			
        System.out.println("***************************精密度GeneXus接口  start ***************************");
        System.out.println("");
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
        System.out.println("");
        System.out.println("***************************精密度GeneXus接口  end ***************************");
        System.out.println("");
        System.out.println("***************************正确度GeneXus接口  start ***************************");
        System.out.println("");
        System.out.println("**** 患者样本 ****");
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
        dataIndex = 0;
        System.out.println("------第三批输出：第一次X5之差                ："+gxRefMaterial.GetLabDataEveryResultGroupAverageDiff(batchIndex, dataIndex, 1));
        System.out.println("------第三批输出：第一次X5之差平方         ："+gxRefMaterial.GetMatchPow2(gxRefMaterial.GetLabDataEveryResultGroupAverageDiff(batchIndex, dataIndex, 1),2));
        System.out.println("------总均值                                               ："+gxRefMaterial.GroupAverage(1));
        System.out.println("------参考物质测量偏移值                           ："+gxRefMaterial.Deviant(1));
        System.out.println("------标准差                                               ："+gxRefMaterial.StandardDeviation(3));
        System.out.println("------参考物质测量不确定度                        ："+gxRefMaterial.Uncertainty(3));
        System.out.println("------验证区间下限                                      ："+gxRefMaterial.MinOfInterval(2));
        System.out.println("------验证区间上限                                      ："+gxRefMaterial.MaxOfInterval(2));
        
        System.out.println("");
        System.out.println("***************************正确度GeneXus接口  end ***************************");
        System.out.println("");
        System.out.println("***************************线性GeneXus接口  start ***************************");
        System.out.println("");
        GxLinearService gxLinear = new GxLinearService();
        gxLinear.CreateLinearRegression();
        gxLinear.LoadLabData("4.7 7.8 10.4 13.0 15.5");
        gxLinear.LoadLabData("4.6 7.6 10.2 13.1 15.3");
        gxLinear.LoadX("1 2 3 4 5");
        System.out.println("y="+gxLinear.Slope(3)+"x+ "+gxLinear.Origin(3)+" and R*R is "+gxLinear.GetMatchPow2(gxLinear.Relation(3),4)+" .");
        System.out.println("------稀释度3：均值                ："+gxLinear.GetLabDataTwoResultAverage(2,2));
        System.out.println("");
        System.out.println("***************************线性GeneXus接口  end ***************************");
    }
}
