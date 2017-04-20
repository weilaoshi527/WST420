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
	static void PrintBase(Precision pv)
	{
		String tempStr = "";
		//ʵ��GeneXus���ã�����֮��
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.PrintNoZero(pv.GetLabDataSum(i),0);
        }
		System.out.println("����֮��:\t\t"+tempStr);
		
		//ʵ��GeneXus���ã����ݾ�ֵ
		tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.PrintNoZero(pv.GetLabDataAverage(i),3);
        }
        System.out.println("���ݾ�ֵ:\t\t"+tempStr);
        
        //ʵ��GeneXus���ã�����׼��
        tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.PrintNoZero(pv.GetLabDataStandardDeviation(i),4);
        }
        System.out.println("����׼��:\t\t"+tempStr);
        
        //ʵ��GeneXus���ã����ڷ���
        tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.Print(pv.GetLabDataIntraGroupVariance(i));
        }
        System.out.println("���ڷ���:\t\t"+tempStr);
        
        //ʵ��GeneXus���ã�����ϵ��
        tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.PrintPercentage(pv.GetLabDataCV(i),1);
        }
        System.out.println("����ϵ��:\t\t" + tempStr); 
        
        //ʵ��GeneXus���ã����ƽ����
        tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.Print(pv.GetLabDataSumOfSquare(i));
        }
        System.out.println("���ƽ����:\t\t"+tempStr);
        
        //ʵ��GeneXus���ã���X�����ֵ֮��
        tempStr = "";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	tempStr = "";
        	for(int j=0;j<pv.MeasuringTimes();j++)
            {
        		tempStr += Print4.PrintNoZero(pv.GetLabDataSubtractMean(i,j),2)+"\t";
            }
        	System.out.println("��"+String.valueOf(i)+"�����ֵ֮��:\t\t"+tempStr);
        }
        
        //ʵ��GeneXus���ã���X�����ֵ֮��ƽ��
        tempStr = "";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	tempStr = "";
        	for(int j=0;j<pv.MeasuringTimes();j++)
            {
        		tempStr += Print4.PrintNoZero(pv.GetLabDataSubtractMeanSquare(i,j),4)+"\t";
            }
        	System.out.println("��"+String.valueOf(i)+"�����ֵ֮��ƽ��:\t\t"+tempStr);
        }
        //ʵ��GeneXus���ã����ܾ�ֵ֮��
        tempStr = "";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	tempStr+=" "+Print4.PrintNoZero(pv.GetLabDataSubtractOverallMean(i),2);
        }
        System.out.println("���ܾ�ֵ֮��:\t\t"+tempStr);
        //ʵ��GeneXus���ã����ܾ�ֵ֮��ƽ��
        tempStr = "";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	tempStr+=" "+Print4.PrintNoZero(pv.GetLabDataSubtractOverallMeanSquare(i),4);
        }
        System.out.println("���ܾ�ֵ֮���ƽ��:\t\t"+tempStr);
	}
    public static void main(String[] args)
    {			
        System.out.println("***************************���ܶ�GeneXus�ӿ�  start ***************************");
        System.out.println("");
        
        Precision pv=new Precision();
        //pv.SetCheckedValue(12.0);	//���ó������Ƶ�У��ֵ
        pv.SetDecimalBits(4);	//����С��λ��
        pv.SetFalseRejectionRate(0.05, 2);	//���ü��ų��ʺͼ��ˮƽ
        
        pv.SetLabData("140 140 140;138 139 138;143 144 144;143 143 142;142 143 141"); //�����������
        //pv.SetLabData("140 139 138 138 140;140 143 141 143 137;140 138 136 141 136;141 144 142 143 144;139 140 141 138 141"); //Sample A.pdf
        //pv.SetLabData("606 627 621 606 620;612 610 611 595 630;649 626 636 639 648;615 633 605 616 625;622 632 646 619 623");	//Sample B.pdf
        System.out.println("------���ܶ�ʵ��------");
        
        PrintBase(pv);
        
        System.out.println("���䷽��:\t" + Print4.Print(pv.BetweenGroupVariance()));
        System.out.println(("�����׼��:\t" + Print4.PrintNoZero(pv.BetweenGroupSD(),4)));
        System.out.println(("�ܾ�ֵ:\t" + Print4.PrintNoZero(pv.OverallMean(),2)));
        System.out.println(("�ظ����ɶ�:\t" + Print4.PrintNoZero(pv.DegreeOfFreedom(0),2)));
        System.out.println(("�ظ�C��:\t" + Print4.PrintNoZero(pv.C(0),2)));
        System.out.println(("�ظ�����:\t" + Print4.PrintNoZero(pv.Variance(0),4)));
        System.out.println(("�ظ���׼��:\t" + Print4.PrintHaveZero(pv.StandardDeviation(0),2)));
        pv.SetCheckedValue(2.0);
        //pv.SetProbability(0.95);
        System.out.println(("�ظ���ֵ֤:\t" + Print4.PrintHaveZero(pv.MeasurementValue(0),2)));
        System.out.println(("�ڼ����ɶ�:\t" + Print4.PrintNoZero(pv.DegreeOfFreedom(1),2)));
        System.out.println(("�ڼ䷽��:\t" + Print4.PrintNoZero(pv.Variance(1),4)));
        System.out.println(("�ڼ��׼��:\t" + Print4.PrintHaveZero(pv.StandardDeviation(1),2)));
        System.out.println(("�ڼ�C��:\t" + Print4.PrintNoZero(pv.C(1),2)));
        //pv.SetCheckedValue(3.5);
        System.out.println(("�ڼ���ֵ֤:\t" + Print4.PrintHaveZero(pv.MeasurementValue(1),2)));    
        String sdiStr="";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	for(int j=0;j<pv.MeasuringTimes();j++)
            {
        		sdiStr += Print4.Print(pv.GetSDI(i,j))+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("SDI����:\n" + sdiStr));
        
        sdiStr="";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	for(int j=0;j<pv.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(pv.GetLabDataByString(i,j),0)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("�������:\n" + sdiStr));
        System.out.println("");
        System.out.println("***************************���ܶ�GeneXus�ӿ�  end ***************************");
        System.out.println("");
        System.out.println("***************************��ȷ��GeneXus�ӿ�  start ***************************");
        System.out.println("");
        
        System.out.println("**** �������� ****");
        AccuracyFromPatientSample p2=new AccuracyFromPatientSample();
        p2.SetCheckedValue(2.0);
        p2.SetFalseRejectionRate(0.01);
        p2.SetDecimalBits(2);
        p2.SetTea(20, 0.1);		//ע�⣬Tea����ȱʧʱ��EIָ�������
       
        //p2.SetRi(LabData.CreateByStr("76 127 256 303 29 345 42 154 398 93 240 72 312 99 375 168 59 183 213 436"));
        //p2.SetRc(LabData.CreateByStr("77 121 262 294 25 348 41 154 388 92 239 69 308 101 375 162 54 185 204 431"));
        p2.SetRiByStr("76 127 256 303 29 345 42 154 398 93 240 72 312 99 375 168 59 183 213 436");
        p2.SetRcByStr("77 121 262 294 25 348 41 154 388 92 239 69 308 101 375 162 54 185 204 431");
        //ʵ��GeneXus���ã���X����X�����ܾ�ֵ֮��
        String tempArr = "";
        System.out.println("����ƫ��:\t"+Print4.Print(p2.AbsoluteOffset()));
        //����ƫ������
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+  Print4.PrintNoZero(p2.AbsoluteOffsetArray(i),0);
        }
        System.out.println("����ƫ������:\t"+tempArr);
        //����ƫ�����������ƫ��֮��
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+  Print4.PrintNoZero(p2.RelativeOffsetArraySubtract(i),2);
        }
        System.out.println("����ƫ�����������ƫ��֮��:\t"+tempArr);
        //����ƫ�����������ƫ��֮��ƽ��
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+  Print4.PrintNoZero(p2.RelativeOffsetArraySubtractSquare(i),2);
        }
        System.out.println("����ƫ�����������ƫ��֮��ƽ��:\t"+tempArr);
        System.out.println("����ƫ�Ʊ�׼��:\t"+Print4.PrintNoZero(p2.StandardDeviation(),2));
        System.out.println("����ƫ������:\t["+Print4.Print(p2.MinOfInterval())+"-->"+Print4.Print(p2.MaxOfInterval())+"]");
        System.out.println("���ƫ��:\t"+Print4.Print(p2.RelativeOffset()));
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+ Print4.PrintNoZero(p2.RelativeOffsetArray(i),0);
        }
        System.out.println("���ƫ������:\t"+tempArr);
        System.out.println("���ƫ�Ʊ�׼��:\t"+Print4.PrintNoZero(p2.RelativeStandardDeviation(),2));
        System.out.println("���ƫ������:\t["+Print4.Print(p2.RelativeMinOfInterval())+"-->"+Print4.Print(p2.RelativeMaxOfInterval())+"]");
        p2.SetTea(20, 0.1);
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+ Print4.PrintNoZero(p2.ErrorIndex(i),2);
        }
        System.out.println("Error Indexes:\t"+tempArr);
        LinearRegression line=p2.GetLine();
        System.out.println("����:\ty="+Print4.PrintNoZero(line.Slope(),4)+"x+"+Print4.PrintNoZero(line.Origin(),4));
        System.out.println("���ϵ��:\t"+Print4.PrintNoZero(line.Relation()*line.Relation(),5));
        System.out.println("����ָ����ֵ:\t"+Print4.Print(p2.AverageErrorIndex()));
        System.out.println("����ָ����Χ:\t"+Print4.Print(p2.MinEI())+" to "+Print4.Print(p2.MaxEI()));
        
        Graph g=new Graph();
        g.SetAllParameters(20, 0.25, 1.085, -1.527, 500, 500); //ע�⣺�ٷֱ�Ϊ0ʱ��������4�͵�5��ֱ��
        //g.SetAllParameters(20, 0.25, 1.085, -1.527, 500, 500);
       
        /* ��ͼ���ò���
            g.SetConcentration(20);	 	//Ũ��
	        g.SetMaxX(500);				//X���޷�Χ
	        g.SetMaxY(500);				//Y���޷�Χ
	        g.SetOrigin(-1.527);		//�ؾ�
	        g.SetPercent(0.25);			//�ٷֱ�	�ٷֱ�Ϊ0ʱ��������4�͵�5��ֱ��
	        g.SetSlope(1.085);			//б��
         */
        System.out.println("��1��ֱ�߲���:\t"+g.GetLine1());	//�м��ֱ��
        System.out.println("��2��ֱ�߲���:\t"+g.GetLine2());	//���Ϸ�ֱ��
        System.out.println("��3��ֱ�߲���:\t"+g.GetLine3());	//���·�ֱ��
        System.out.println("��4��ֱ�߲���:\t"+g.GetLine4());	//���Ϸ�ֱ��
        System.out.println("��5��ֱ�߲���:\t"+g.GetLine5());	//���·�ֱ��        
        System.out.println("��ͼ����:\t"+g.GetLines());
        
        sdiStr="";
        for(int i=0;i<p2.BatchCount();i++)
        {
        	for(int j=0;j<p2.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(p2.GetLabDataByString(i,j),0)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("�������:\n" + sdiStr));
        System.out.println("**** �ο����� ****");
        
        
        AccuracyFromRefMaterial p3=new AccuracyFromRefMaterial();
        //p3.SetFalseRejectionRate(0.01);
        p3.SetFalseRejectionRate(0.005);
        //p3.SetXfromRef(40);
        p3.SetXfromRef(2.2);
        //p3.SetSprogram(1.73);
        //p3.SetLabsCount(135);
        p3.SetSa(0.008);
        
        //p3.SetLabData("37 38;39 37;38 36;39 38;38 37");
        p3.SetLabData("2.04 2.09;2.15 2.04;2.09 1.98;2.15 2.09;2.09 2.04");
        //ʵ��GeneXus���ã���X����X�����ܾ�ֵ֮��
        tempArr = "";
        for(int i=0;i<p3.BatchCount();i++)
        {
        	tempArr = "";
        	for(int j=0;j<p3.MeasuringTimes();j++)
            {
        		tempArr += Print4.PrintNoZero(p3.GetLabDataSubtractOverallMean(i,j),2)+"\t";
            }
        	System.out.println("��"+String.valueOf(i)+"����1,2�����ܾ�ֵ֮��:\t\t"+tempArr);
        }
        
        //ʵ��GeneXus���ã���X�����ܾ�ֵ֮��ƽ��
        tempArr = "";
        for(int i=0;i<p3.BatchCount();i++)
        {
        	tempArr = "";
        	for(int j=0;j<p3.MeasuringTimes();j++)
            {
        		tempArr += Print4.PrintNoZero(p3.GetLabDataSubtractOverallMeanSquare(i,j),4)+"\t";
            }
        	System.out.println("��"+String.valueOf(i)+"����1,2�����ܾ�ֵ֮��ƽ��:\t\t"+tempArr);
        }
        System.out.println("����ƫ��ֵ:\t"+Print4.PrintNoZero(p3.Deviant(),2));
        System.out.println("���ɶ�:\t"+Print4.Print(p3.Freedom()));
        System.out.println("�ܾ�ֵ:\t"+Print4.Print(p3.OverallMean()));
        System.out.println("��׼��:\t"+Print4.PrintNoZero(p3.StandardDeviation(),4));
        System.out.println("������׼��ȷ����:\t"+Print4.PrintNoZero(p3.GetSa(),4));
        System.out.println("��֤����:\t["+Print4.PrintNoZero(p3.MinOfInterval(),2)+"-->"+Print4.PrintNoZero(p3.MaxOfInterval(),2)+"]");
        sdiStr="";
        for(int i=0;i<p3.BatchCount();i++)
        {
        	for(int j=0;j<p3.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(p3.GetSDI(i,j),2)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("SDI����:\t\n" + sdiStr)); 
        
        sdiStr="";
        for(int i=0;i<p3.BatchCount();i++)
        {
        	for(int j=0;j<p3.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(p3.GetLabDataByString(i,j),0)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("�������:\n" + sdiStr));
        System.out.println("");
        System.out.println("***************************��ȷ��GeneXus�ӿ�  end ***************************");
        System.out.println("");
        System.out.println("***************************����GeneXus�ӿ�  start ***************************");
        System.out.println("");
        
        LinearRegression l=new LinearRegression();
        l.SetDecimalBits(3);        
        l.SetLabData("4.7 7.8 10.4 13.0 15.5;4.6 7.6 10.2 13.1 15.3");
        //l.SetX(LabData.CreateByStr("1 2 3 4 5"));  
        l.SetXByStr("1 2 3 4 5");
        //��ֵ
        tempArr="";
        for(int i=0;i<l.MeasuringTimes();i++)
        {
        	tempArr+=" "+Print4.PrintNoZero(l.averageY(i),2);
        }
        System.out.println("��ֵ(ʵ��Ũ��):\t"+tempArr);
        //����Ũ��
        tempArr="";
        for(int i=0;i<l.MeasuringTimes();i++)
        {
        	tempArr+=" "+l.TheoreticalY(i);
        }
        System.out.println("����Ũ��:\t"+tempArr);
        //ʵ��Ũ��-����Ũ��
        tempArr="";
        for(int i=0;i<l.MeasuringTimes();i++)
        {
        	tempArr+=" "+Print4.PrintNoZero(l.averageY(i)-l.TheoreticalY(i),2);
        }
        System.out.println("��ֵ(ʵ��Ũ��)-����Ũ��:\t"+tempArr);
        System.out.println("����:\ty="+Print4.PrintNoZero(l.Slope(),3)+"x+"+Print4.PrintNoZero(l.Origin(),3));
        System.out.println("���ϵ��:\t"+Print4.PrintNoZero(l.Relation()*l.Relation(),4));
        //
        
        sdiStr="";
        for(int i=0;i<l.BatchCount();i++)
        {
        	for(int j=0;j<l.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(l.GetLabDataByString(i,j),0)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("�������:\n" + sdiStr));
        p2=new AccuracyFromPatientSample();
        p2.SetCheckedValue(2.0);
        p2.SetFalseRejectionRate(0.01);
        p2.SetDecimalBits(2);
        p2.SetTea(5, 0);
        //p2.SetRi(l.GetLabData(0));
        //p2.SetRc(l.GetLabData(1));
        p2.SetRiByStr("4.7 7.8 10.4 13.0 15.5");
        p2.SetRcByStr("4.6 7.6 10.2 13.1 15.3");
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+ Print4.PrintNoZero(p2.ErrorIndex(i),2);
        }
        System.out.println("����ʵ���EIָ��:\t"+tempArr);
        
        System.out.println("");
        System.out.println("***************************����GeneXus�ӿ�  end ***************************");
    }
}
