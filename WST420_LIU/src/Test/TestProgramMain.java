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
 * ����
 * @author sungiant
 *
 */
public class TestProgramMain 
{
	static void PrintBase(BaseVerification pv)
	{
		System.out.println("����֮��:\t"+Print4.Print(pv.GetSums(),0));
        System.out.println("���ݾ�ֵ:\t"+Print4.Print(pv.GetAverages(),3));
        System.out.println("���ݱ�׼��:\t"+Print4.Print(pv.GetStandardDeviations()));
        System.out.println("���ڷ���:\t"+Print4.Print(pv.GetSampleStandardDeviations()));
        System.out.println(("����ϵ��:\t" + Print4.PrintPercentage(pv.GetCV(),1))); 
        System.out.println("���ƽ����:\t"+Print4.Print(pv.GetSumOfSquares()));
	}
    public static void main(String[] args)
    {			
        System.out.println("***************************���ܶ�GeneXus�ӿ�  start ***************************");
        System.out.println("");
        
        
        
        //PrecisionVerification pv=new PrecisionVerification(2.0);
        Precision pv=new Precision(2.0);
        pv.DecimalBits=3;	//����С��λ��
        pv.setFalseRejectionRate(0.05, 2);	//���ü��ų��ʺͼ��ˮƽ
        pv.FalseRejectionRate=0.05;
        pv.DetectionLevel=2;
        //pv.SetLabData("140 140 140;138 139 138;143 144 144;143 143 142;142 143 141"); //�����������
        //
        //pv.SetLabData("140 139 138 138 140;140 143 141 143 137;140 138 136 141 136;141 144 142 143 144;139 140 141 138 141"); //Sample A.pdf
        pv.SetLabData("606 627 621 606 620;612 610 611 595 630;649 626 636 639 648;615 633 605 616 625;622 632 646 619 623");	//Sample B.pdf
        System.out.println("------���ܶ�ʵ��------");
        
        PrintBase(pv);
        
        System.out.println(("���䷽��:\t" + Print4.Print(pv.BetweenGroupVariance(),4)));
        System.out.println(("�����׼��:\t" + Print4.Print(pv.BetweenGroupSD(),4)));
        System.out.println(("�ܾ�ֵ:\t" + Print4.Print(pv.OverallMean(),2)));
        System.out.println(("�ظ����ɶ�:\t" + Print4.Print(pv.DegreeOfFreedom(0),2)));
        System.out.println(("�ظ���׼��:\t" + Print4.Print(pv.StandardDeviation(0),4)));
        System.out.println(("�ظ���ֵ֤:\t" + Print4.Print(pv.MeasurementValue(0),2)));
        System.out.println(("�ڼ����ɶ�:\t" + Print4.Print(pv.DegreeOfFreedom(1),2)));
        System.out.println(("�ڼ��׼��:\t" + Print4.Print(pv.StandardDeviation(1),4)));
        System.out.println(("�ڼ���ֵ֤:\t" + Print4.Print(pv.MeasurementValue(1),2)));    
        
        System.out.println(("SDI����:\t" + Print4.Print(pv.getSDI(),2)));   
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
        */
        System.out.println("");
        System.out.println("***************************���ܶ�GeneXus�ӿ�  end ***************************");
        System.out.println("");
        System.out.println("***************************��ȷ��GeneXus�ӿ�  start ***************************");
        System.out.println("");
        
        System.out.println("**** �������� ****");
        AccuracyFromPatientSample p2=new AccuracyFromPatientSample(2.0);
        p2.FalseRejectionRate=0.01;
        p2.Add(new LabData("76 127 256 303 29 345 42 154 398 93 240 72 312 99 375 168 59 183 213 436"));
        p2.Add(new LabData("77 121 262 294 25 348 41 154 388 92 239 69 308 101 375 162 54 185 204 431"));
        //PrintBase(p2);
        System.out.println("����ƫ��:\t"+Print4.Print(p2.AbsoluteOffset()));
        System.out.println("����ƫ������:\t"+Print4.Print(p2.AbsoluteOffsetArray(),0));
        System.out.println("����ƫ�Ʊ�׼��:\t"+Print4.Print(p2.StandardDeviation(),4));
        System.out.println("����ƫ������:\t["+Print4.Print(p2.MinOfInterval())+"-->"+Print4.Print(p2.MaxOfInterval())+"]");
        System.out.println("���ƫ��:\t"+Print4.Print(p2.RelativeOffset()));
        System.out.println("���ƫ������:\t"+Print4.Print(p2.RelativeOffsetArray()));
        System.out.println("���ƫ�Ʊ�׼��:\t"+Print4.Print(p2.RelativeStandardDeviation(),4));
        System.out.println("���ƫ������:\t["+Print4.Print(p2.RelativeMinOfInterval())+"-->"+Print4.Print(p2.RelativeMaxOfInterval())+"]");
        
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
        AccuracyFromRefMaterial p3=new AccuracyFromRefMaterial();
        p3.FalseRejectionRate=0.01;
        p3.XfromRef=40;
        p3.Sprogram=1.73;
        p3.LabsCount=135;
        p3.SetLabData("37 38;39 37;38 36;39 38;38 37");
        System.out.println("����ƫ��ֵ:\t"+Print4.Print(p3.Deviant()));
        System.out.println("���ɶ�:\t"+Print4.Print(p3.Freedom()));
        System.out.println("�ܾ�ֵ:\t"+Print4.Print(p3.GroupAverage()));
        System.out.println("��׼��:\t"+Print4.Print(p3.StandardDeviation(),4));
        System.out.println("��ȷ����:\t"+Print4.Print(p3.Uncertainty(),4));
        System.out.println("��֤����:\t["+Print4.Print(p3.MinOfInterval())+"-->"+Print4.Print(p3.MaxOfInterval())+"]");
        /*
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
        int dataIndex = 0;
        System.out.println("------�������������һ��X5֮��                ��"+gxRefMaterial.GetLabDataEveryResultGroupAverageDiff(batchIndex, dataIndex, 1));
        System.out.println("------�������������һ��X5֮��ƽ��         ��"+gxRefMaterial.GetMatchPow2(gxRefMaterial.GetLabDataEveryResultGroupAverageDiff(batchIndex, dataIndex, 1),2));
        System.out.println("------�ܾ�ֵ                                               ��"+gxRefMaterial.GroupAverage(1));
        System.out.println("------�ο����ʲ���ƫ��ֵ                           ��"+gxRefMaterial.Deviant(1));
        System.out.println("------��׼��                                               ��"+gxRefMaterial.StandardDeviation(3));
        System.out.println("------�ο����ʲ�����ȷ����                        ��"+gxRefMaterial.Uncertainty(3));
        System.out.println("------��֤��������                                      ��"+gxRefMaterial.MinOfInterval(2));
        System.out.println("------��֤��������                                      ��"+gxRefMaterial.MaxOfInterval(2));
        */
        
        System.out.println("");
        System.out.println("***************************��ȷ��GeneXus�ӿ�  end ***************************");
        System.out.println("");
        System.out.println("***************************����GeneXus�ӿ�  start ***************************");
        System.out.println("");
        
        LinearRegression l=new LinearRegression();
        l.DecimalBits=3;        
        l.SetLabData("4.7 7.8 10.4 13.0 15.5;4.6 7.6 10.2 13.1 15.3");
        l.SetX(new LabData("1 2 3 4 5"));        
        System.out.println("����:\ty="+Print4.Print(l.Slope(),3)+"x+"+Print4.Print(l.Origin(),3));
        System.out.println("���ϵ��:\t"+Print4.Print(l.Relation()*l.Relation(),4));
        
        /*
        GxLinearService gxLinear = new GxLinearService();
        gxLinear.CreateLinearRegression();
        gxLinear.LoadLabData("4.7 7.8 10.4 13.0 15.5");
        gxLinear.LoadLabData("4.6 7.6 10.2 13.1 15.3");
        gxLinear.LoadX("1 2 3 4 5");
        System.out.println("y="+gxLinear.Slope(3)+"x+ "+gxLinear.Origin(3)+" and R*R is "+gxLinear.GetMatchPow2(gxLinear.Relation(3),4)+" .");
        System.out.println("------ϡ�Ͷ�3����ֵ                ��"+gxLinear.GetLabDataTwoResultAverage(2,2));
        */
        
        System.out.println("");
        System.out.println("***************************����GeneXus�ӿ�  end ***************************");
    }
}
