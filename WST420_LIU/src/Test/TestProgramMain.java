package Test;

import com.base.LabData;

import gxinterface.GxLinearService;
import gxinterface.GxPrecisionService;
import gxinterface.GxTrueenessPatientSample;
import gxinterface.GxTrueenessRefMaterial;

/**
 * ����
 * @author sungiant
 *
 */
public class TestProgramMain 
{
    public static void main(String[] args)
    {			
        System.out.println("***************************���ܶ�GeneXus�ӿ�  start ***************************");
        System.out.println("");
        GxPrecisionService gxPrecision = new GxPrecisionService();
        gxPrecision.CreatePrecisionObj("2.0", "2");
        gxPrecision.LoadLabData("140 140 140");
        gxPrecision.LoadLabData("138 139 138");
        gxPrecision.LoadLabData("143 144 144");
        gxPrecision.LoadLabData("143 143 142");
        gxPrecision.LoadLabData("142 143 141");
        
        int batchIndex = 2;
        
        System.out.println("------���������------");
        System.out.println("------�������������                        ��"+gxPrecision.GetLabDataSum(batchIndex,0));
        System.out.println("------�������������ֵ                     ��"+gxPrecision.GetLabDataAverage(batchIndex,2));
        System.out.println("------�����������1��ֵ֮��            ��"+gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex,0,2));
        System.out.println("------�����������1��ֵ֮���ƽ��  ��"+gxPrecision.GetMatchPow2(gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex,0,2), 4));
        System.out.println("------�����������2��ֵ֮��            ��"+gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex, 1,2));
        System.out.println("------�����������2��ֵ֮���ƽ�� ��"+gxPrecision.GetMatchPow2(gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex, 1,2),4));
        System.out.println("------�����������3��ֵ֮��            ��"+gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex, 2,2));
        System.out.println("------�����������3��ֵ֮���ƽ��  ��"+gxPrecision.GetMatchPow2(gxPrecision.GetLabDataEveryResultAverageDiff(batchIndex, 2,2),4));
        System.out.println("------�������������ֵ֮���ƽ���ͣ�"+gxPrecision.GetLabDataBatchAverageDiffPow2Sum(batchIndex,4));
        System.out.println("------��������������ڷ���              ��"+gxPrecision.GetLabDataIntraGroupVariance(batchIndex,4));
        System.out.println("------������������ܾ�ֵ֮��           ��"+gxPrecision.GetLabDataAverageGroupAverageDiff(batchIndex,2));
        System.out.println("------������������ܾ�ֵ֮��ƽ��    ��"+gxPrecision.GetMatchPow2(gxPrecision.GetLabDataAverageGroupAverageDiff(batchIndex,2),4));
        System.out.println("------�ظ���׼��                               ��"+gxPrecision.RepeatedStandardDeviation());
        System.out.println("------�ܾ�ֵ                                     ��"+gxPrecision.GroupAverage(2));
        System.out.println("------���䷽��                                  ��"+gxPrecision.BetweenGroupVariance(4));
        System.out.println("------�ڼ��׼��                              ��"+gxPrecision.GroupStandardDeviation());
        System.out.println("------���ɶ�                                     ��"+gxPrecision.Freedom());
        System.out.println("------��ֵ֤                                     ��"+gxPrecision.PrecisionValue());
        System.out.println("");
        System.out.println("***************************���ܶ�GeneXus�ӿ�  end ***************************");
        System.out.println("");
        System.out.println("***************************��ȷ��GeneXus�ӿ�  start ***************************");
        System.out.println("");
        System.out.println("**** �������� ****");
        GxTrueenessPatientSample gxPatientSample = new GxTrueenessPatientSample();
        gxPatientSample.CreateTrueenessPatientSample("2.0", "2");
        gxPatientSample.LoadLabData("76 127 256 303 29 345 42 154 398 93 240 72 312 99 375 168 59 183 213 436");
        gxPatientSample.LoadLabData("77 121 262 294 25 348 41 154 388 92 239 69 308 101 375 162 54 185 204 431");
        
        int dataIndex = 2;
        System.out.println("------����������ľ���ƫ��                                         ��"+gxPatientSample.AbsoluteOffset(2));
        System.out.println("------�����������������������������ľ���ƫ��      ��"+gxPatientSample.GetLabDataBi(dataIndex, 0));
        System.out.println("------����������������������ľ���ƫ��֮��             ��"+gxPatientSample.GetLabDataAbsoluteOffsetDiff(dataIndex, 1));
        System.out.println("------����������������������ľ���ƫ��֮���ƽ��   ��"+gxPatientSample.GetMatchPow2(gxPatientSample.GetLabDataAbsoluteOffsetDiff(dataIndex, 1),2));
        System.out.println("------����ƫ�Ʊ�׼��                                                   ��"+gxPatientSample.StandardDeviation(2));
        System.out.println("------��֤��������                                                       ��"+gxPatientSample.MinOfInterval(2));
        System.out.println("------��֤��������                                                       ��"+gxPatientSample.MaxOfInterval(2));
        
        System.out.println("");
        System.out.println("**** �ο����� ****");
        GxTrueenessRefMaterial gxRefMaterial = new GxTrueenessRefMaterial();
        String xfromRef = "40";//�ο����ʸ�ֵ ��λmg/dL,Ĭ��ֵ40
        String sprogram = "1.73";//�Ҽ����������׼�Ĭ��ֵ1.73
        String labsCount = "135";//�μ��Ҽ�������ʵ����������Ĭ��ֵ135
        String falseRejectionRate = "0.01";//���ų��ʣ�Ĭ��ֵ0.01
        gxRefMaterial.CreateTrueenessRefMaterial(xfromRef, sprogram, labsCount, falseRejectionRate);
        
        gxRefMaterial.LoadLabData("37 38");
        gxRefMaterial.LoadLabData("39 37");
        gxRefMaterial.LoadLabData("38 36");
        gxRefMaterial.LoadLabData("39 38");
        gxRefMaterial.LoadLabData("38 37");
        
        batchIndex = 2;
        dataIndex = 0;
        System.out.println("------�������������һ��X5֮��                ��"+gxRefMaterial.GetLabDataEveryResultGroupAverageDiff(batchIndex, dataIndex, 1));
        System.out.println("------�������������һ��X5֮��ƽ��         ��"+gxRefMaterial.GetMatchPow2(gxRefMaterial.GetLabDataEveryResultGroupAverageDiff(batchIndex, dataIndex, 1),2));
        System.out.println("------�ܾ�ֵ                                               ��"+gxRefMaterial.GroupAverage(1));
        System.out.println("------�ο����ʲ���ƫ��ֵ                           ��"+gxRefMaterial.Deviant(1));
        System.out.println("------��׼��                                               ��"+gxRefMaterial.StandardDeviation(3));
        System.out.println("------�ο����ʲ�����ȷ����                        ��"+gxRefMaterial.Uncertainty(3));
        System.out.println("------��֤��������                                      ��"+gxRefMaterial.MinOfInterval(2));
        System.out.println("------��֤��������                                      ��"+gxRefMaterial.MaxOfInterval(2));
        
        System.out.println("");
        System.out.println("***************************��ȷ��GeneXus�ӿ�  end ***************************");
        System.out.println("");
        System.out.println("***************************����GeneXus�ӿ�  start ***************************");
        System.out.println("");
        GxLinearService gxLinear = new GxLinearService();
        gxLinear.CreateLinearRegression();
        gxLinear.LoadLabData("4.7 7.8 10.4 13.0 15.5");
        gxLinear.LoadLabData("4.6 7.6 10.2 13.1 15.3");
        gxLinear.LoadX("1 2 3 4 5");
        System.out.println("y="+gxLinear.Slope(3)+"x+ "+gxLinear.Origin(3)+" and R*R is "+gxLinear.GetMatchPow2(gxLinear.Relation(3),4)+" .");
        System.out.println("------ϡ�Ͷ�3����ֵ                ��"+gxLinear.GetLabDataTwoResultAverage(2,2));
        System.out.println("");
        System.out.println("***************************����GeneXus�ӿ�  end ***************************");
    }
}
