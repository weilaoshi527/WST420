package Test;

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
		System.out.println("����֮��:\t\t"+Print4.Print(pv.GetSums(),0));
        System.out.println("���ݾ�ֵ:\t\t"+Print4.Print(pv.GetMeans(),3));
        System.out.println("���ݱ�׼��:\t\t"+Print4.Print(pv.GetStandardDeviations(),4));
        System.out.println("���ڷ���:\t\t"+Print4.Print(pv.GetIntraGroupVars()));
        System.out.println(("����ϵ��:\t\t" + Print4.PrintPercentage(pv.GetCVs(),1))); 
        System.out.println("���ƽ����:\t\t"+Print4.Print(pv.GetSumOfSquares()));
        for(int i=0;i<pv.MeasuringTimes();i++)
        {
        	System.out.println("��"+String.valueOf(i)+"�����ֵ֮��:\t\t"+Print4.Print(pv.SubtractMeans(i),2));
        }
        for(int i=0;i<pv.MeasuringTimes();i++)
        {
        	System.out.println("��"+String.valueOf(i)+"�����ֵ֮���ƽ��:\t\t"+Print4.Print(pv.SubtractMeanSquares(i),4));
        }        
        System.out.println("���ܾ�ֵ֮��:\t\t"+Print4.Print(pv.SubtractOverallMeans(),2));
        System.out.println("���ܾ�ֵ֮���ƽ��:\t\t"+Print4.Print(pv.SubtractOverallMeanSquares(),4));
	}
    public static void main(String[] args)
    {			
        System.out.println("***************************���ܶ�GeneXus�ӿ�  start ***************************");
        System.out.println("");
        
        
        
        
        Precision pv=new Precision();
        pv.SetCheckedValue(2.0);	//���ó������Ƶ�У��ֵ
        pv.DecimalBits=3;	//����С��λ��
        pv.setFalseRejectionRate(0.05, 2);	//���ü��ų��ʺͼ��ˮƽ
        pv.FalseRejectionRate=0.05;
        pv.DetectionLevel=2;
        pv.SetLabData("140 140 140;138 139 138;143 144 144;143 143 142;142 143 141"); //�����������
       
        //pv.SetLabData("140 139 138 138 140;140 143 141 143 137;140 138 136 141 136;141 144 142 143 144;139 140 141 138 141"); //Sample A.pdf
        //pv.SetLabData("606 627 621 606 620;612 610 611 595 630;649 626 636 639 648;615 633 605 616 625;622 632 646 619 623");	//Sample B.pdf
        System.out.println("------���ܶ�ʵ��------");
        
        PrintBase(pv);
        
        System.out.println(("���䷽��:\t" + pv.BetweenGroupVariance2()));
        System.out.println(("�����׼��:\t" + Print4.Print(pv.BetweenGroupSD(),4)));
        System.out.println(("�ܾ�ֵ:\t" + Print4.Print(pv.OverallMean(),2)));
        System.out.println(("�ظ����ɶ�:\t" + Print4.Print(pv.DegreeOfFreedom(0),2)));
        System.out.println(("�ظ���׼��:\t" + Print4.Print(pv.StandardDeviation(0),4)));
        System.out.println(("�ظ���ֵ֤:\t" + Print4.Print(pv.MeasurementValue(0),2)));
        System.out.println(("�ڼ����ɶ�:\t" + Print4.Print(pv.DegreeOfFreedom(1),2)));
        System.out.println(("�ڼ��׼��:\t" + Print4.Print(pv.StandardDeviation(1),4)));
        System.out.println(("�ڼ���ֵ֤:\t" + Print4.Print(pv.MeasurementValue(1),2)));    
        String sdiStr="";
        for(int i=0;i<pv.BatchCount();i++)
        	sdiStr+=Print4.Print(pv.GetSDIs(i))+"\n";
        System.out.println(("SDI����:\t\n" + sdiStr));   
        System.out.println("");
        
        System.out.println("");
        System.out.println("***************************���ܶ�GeneXus�ӿ�  end ***************************");
        System.out.println("");
        System.out.println("***************************��ȷ��GeneXus�ӿ�  start ***************************");
        System.out.println("");
        
        System.out.println("**** �������� ****");
        AccuracyFromPatientSample p2=new AccuracyFromPatientSample();
        p2.SetCheckedValue(2.0);
        p2.FalseRejectionRate=0.01;
        p2.DecimalBits=2;
       
        p2.SetRi(LabData.CreateByStr("77 121 262 294 25 348 41 154 388 92 239 69 308 101 375 162 54 185 204 431"));
        p2.SetRc(LabData.CreateByStr("76 127 256 303 29 345 42 154 398 93 240 72 312 99 375 168 59 183 213 436"));
        
        
        System.out.println("����ƫ��:\t"+Print4.Print(p2.AbsoluteOffset()));
        System.out.println("����ƫ������:\t"+Print4.Print(p2.AbsoluteOffsetArray(),0));
        System.out.println("����ƫ�Ʊ�׼��:\t"+Print4.Print(p2.StandardDeviation(),2));
        System.out.println("����ƫ������:\t["+Print4.Print(p2.MinOfInterval())+"-->"+Print4.Print(p2.MaxOfInterval())+"]");
        System.out.println("���ƫ��:\t"+Print4.Print(p2.RelativeOffset()));
        System.out.println("���ƫ������:\t"+Print4.Print(p2.RelativeOffsetArray()));
        System.out.println("���ƫ�Ʊ�׼��:\t"+Print4.Print(p2.RelativeStandardDeviation(),2));
        System.out.println("���ƫ������:\t["+Print4.Print(p2.RelativeMinOfInterval())+"-->"+Print4.Print(p2.RelativeMaxOfInterval())+"]");
        System.out.println("Error Indexes:\t"+Print4.Print(p2.ErrorIndexes(),2));
        LinearRegression line=p2.GetLine();
        System.out.println("����:\ty="+Print4.Print(line.Slope(),4)+"x+"+Print4.Print(line.Origin(),4));
        System.out.println("���ϵ��:\t"+Print4.Print(line.Relation()*line.Relation(),5));
        System.out.println("����ָ����ֵ:\t"+Print4.Print(p2.AverageErrorIndex()));
        System.out.println("����ָ����Χ:\t"+Print4.Print(p2.MinEI())+" to "+Print4.Print(p2.MaxEI()));
        
        System.out.println("**** �ο����� ****");
        AccuracyFromRefMaterial p3=new AccuracyFromRefMaterial();
        p3.FalseRejectionRate=0.01;
        p3.XfromRef=40;
        p3.Sprogram=1.73;
        p3.LabsCount=135;
        p3.SetLabData("37 38;39 37;38 36;39 38;38 37");
        System.out.println("����ƫ��ֵ:\t"+Print4.Print(p3.Deviant()));
        System.out.println("���ɶ�:\t"+Print4.Print(p3.Freedom()));
        System.out.println("�ܾ�ֵ:\t"+Print4.Print(p3.OverallMean()));
        System.out.println("��׼��:\t"+Print4.Print(p3.StandardDeviation(),4));
        System.out.println("��ȷ����:\t"+Print4.Print(p3.Uncertainty(),4));
        System.out.println("��֤����:\t["+Print4.Print(p3.MinOfInterval())+"-->"+Print4.Print(p3.MaxOfInterval())+"]");
        sdiStr="";
        for(int i=0;i<pv.BatchCount();i++)
        	sdiStr+=Print4.Print(p3.GetSDIs(i),2)+"\n";
        System.out.println(("SDI����:\t\n" + sdiStr)); 
        
        System.out.println("");
        System.out.println("***************************��ȷ��GeneXus�ӿ�  end ***************************");
        System.out.println("");
        System.out.println("***************************����GeneXus�ӿ�  start ***************************");
        System.out.println("");
        
        LinearRegression l=new LinearRegression();
        l.DecimalBits=3;        
        l.SetLabData("4.7 7.8 10.4 13.0 15.5;4.6 7.6 10.2 13.1 15.3");
        l.SetX(LabData.CreateByStr("1 2 3 4 5"));        
        System.out.println("����:\ty="+Print4.Print(l.Slope(),3)+"x+"+Print4.Print(l.Origin(),3));
        System.out.println("���ϵ��:\t"+Print4.Print(l.Relation()*l.Relation(),4));
        
        p2=new AccuracyFromPatientSample();
        p2.SetCheckedValue(2.0);
        p2.FalseRejectionRate=0.01;
        p2.DecimalBits=2;
        p2.SetRi(l.GetLabData(0));
        p2.SetRc(l.GetLabData(1));
        System.out.println("����ʵ���EIָ��:\t"+Print4.Print(p2.ErrorIndexes(),2));
        
        System.out.println("");
        System.out.println("***************************����GeneXus�ӿ�  end ***************************");
    }
}
