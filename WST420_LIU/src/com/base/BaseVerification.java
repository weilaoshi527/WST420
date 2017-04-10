package com.base;

import java.util.ArrayList;
import java.util.List;
 

/**
 * ��������֤����
 * @author sungiant
 *
 */
public class BaseVerification {
	
	protected List<LabData> batches;	//ʵ������
    protected double checkedValue; //��У���ֵ
    /**
     * Ԥ������С��λ��
     */
    public int DecimalBits; 
    
    /*
    public BaseVerification(double beta)
    {
            batches=new ArrayList<LabData>();
            checkedValue=beta;
            DecimalBits=-1;
    }    
    */
    public BaseVerification()	
    {
    	batches=new ArrayList<LabData>();        
        DecimalBits=-1;
    }
    /**
     * ���ó������Ƶ�У��ֵ
     * @param beta
     */
    public void SetCheckedValue(double beta)
    {
    	checkedValue=beta;
    }
    protected double format(double d)
    {
    	return LabData.Format45(d,DecimalBits);
    }
    protected double format2(double d)
    {
    	return LabData.Format452(d,DecimalBits);
    }   
    /**
     * �����׼��
     * @return
     */
    public double BetweenGroupSD()
    {
    	return format2(Math.sqrt(BetweenGroupVariance()));
    }
    public String BetweenGroupSD2()
    {
    	return Print4.Print(BetweenGroupSD());
    }
    /**
     * ���䷽��
     * @return
     */
    public double BetweenGroupVariance()
    {
        double sum=0;
        for(LabData d : batches)
        {
            double ave=d.Average()-this.OverallMean();
            sum+=ave*ave;
        }
        return format2(sum/(BatchCount()-1));
    }
    public String BetweenGroupVariance2()
    {
    	return Print4.Print(BetweenGroupVariance());
    }
    /**
     * �ܾ�ֵ
     * @return
     */
    public double OverallMean()
    {
        double sum=0;
        for(LabData d : batches)
            sum+=d.Average();
        return format(sum/BatchCount());
    }
    /**
     * ��������
     * @return
     */
    public int BatchCount()
    {
        return batches.size();
    }
    public String BatchCount2()
    {
    	return Print4.Print(BatchCount());
    }
    /**
     * ��������
     * @return
     */
    public int MeasuringTimes()
    {
        if(batches.size()>0)
            return batches.get(0).Count();
        return 0;
    }
    public String MeasuringTimes2()
    {
    	return Print4.Print(MeasuringTimes());
    }
    /**
     * ��Ӳ�������
     * @param data
     */
    public void Add(LabData data)
    {
    	data.DecimalBits=this.DecimalBits;
        batches.add(data);
    }
    /**
     * һ�������ú���������
     * @param dataStr ����140 140 140;139 138 139;140 141 140
     */
    public void SetLabData(String dataStr)
    {
    	String[] labDataStr=dataStr.split(";");        
        for(int i=0;i<labDataStr.length;i++)
            Add(LabData.CreateByStr(labDataStr[i]));        
    }
    
    /**
     * ��ȡ�ڼ���ʵ������
     * @param index
     * @return
     */
    public LabData GetLabData(int index)
    {
    	return batches.get(index);
    }
    /**
     * ÿ��ʵ�����ݺ�
     * @return
     */
    public double[] GetSums()
    {
    	double[] temp=new double[BatchCount()];
    	for(int i=0;i<temp.length;i++)
    		temp[i]=this.GetLabData(i).Sum();
    	return temp;
    }
    /**
     * ��ȡָ�����SDI������
     * @param ��i��
     * @return
     */
    public double[] GetSDIs(int i)
    {
    	double[] result=new double[this.MeasuringTimes()];
    	for(LabData labData : this.batches)
    	{
    		for(int j=0;j<labData.Count();j++)
    		{    			
    			result[j]=GetSDI(i,j);    			
    		}
    	}
    	return result;
    }
    /**
     * ��ȡָ�����ݵ�SDIֵ
     * @param i ��i������
     * @param j ��j������
     * @return SDIֵ 
     */
    public double GetSDI(int i,int j)
    {
    	double sd=this.BetweenGroupSD();
    	double avg=this.OverallMean();    	
    	return format((this.batches.get(i).Data(j)-avg)/sd);
    }
    /**
     * ÿ��ʵ�����ݾ�ֵ
     * @return
     */
    public double[] GetMeans()
    {
    	double[] temp=new double[BatchCount()];
    	for(int i=0;i<temp.length;i++)
    		temp[i]=GetMean(i);
    	return temp;
    }
    /**
     * ��ȡ�ƶ������ݵľ�ֵ
     * @param i ������к�
     * @return ��ֵ
     */
    public double GetMean(int i)
    {    	    	
    	return this.GetLabData(i).Average();
    }
    /**
     * ÿ��ʵ���������ڷ���ƽ��
     * @return
     */
    public double[] GetIntraGroupVars()
    {
    	double[] temp=new double[BatchCount()];
    	for(int i=0;i<temp.length;i++)
    		temp[i]=this.GetLabData(i).IntraGroupVariance();
    	return temp;
    }
    /**
     * ��ȡָ������������ڷ���
     * @param i
     * @return
     */
    public double GetIntraGroupVar(int i)
    {
    	return this.GetLabData(i).IntraGroupVariance();
    }
    /**
     * ÿ��ʵ���������ڱ�׼��
     * @return
     */
    public double[] GetSampleStandardDeviations()
    {
    	double[] temp=new double[BatchCount()];
    	for(int i=0;i<temp.length;i++)
    		temp[i]=this.GetLabData(i).SampleStandardDeviation();
    	return temp;
    }
    /**
     * ��ȡָ�����������׼��
     * @param i
     * @return
     */
    public double GetSampleStandarDeviation(int i)
    {
    	return this.GetLabData(i).SampleStandardDeviation();
    }
    /**
     * ÿ��ʵ�����ݱ�׼��
     * @return
     */
    public double[] GetStandardDeviations()
    {
    	double[] temp=new double[BatchCount()];
    	for(int i=0;i<temp.length;i++)
    		temp[i]=this.GetLabData(i).StandardDeviation();
    	return temp;
    }
    /**
     * ÿ��ʵ���������ƽ����
     * @return
     */
    public double[] GetSumOfSquares()
    {
    	double[] temp=new double[BatchCount()];
    	for(int i=0;i<temp.length;i++)
    		temp[i]=this.GetLabData(i).SumOfSquares();
    	return temp;
    }
    /**
     * ��ȡָ��������ƽ����
     * @param i
     * @return
     */
    public double GetSumOfSquare(int i)
    {
    	return this.GetLabData(i).SumOfSquares();
    }
    /**
     * ÿ��ʵ�����ݱ���ϵ��
     * @return
     */
    public double[] GetCVs()
    {
    	double[] temp=new double[BatchCount()];
    	for(int i=0;i<temp.length;i++)
    		temp[i]=this.GetLabData(i).CV();
    	return temp;
    }
    public double GetCV(int i)
    {
    	return this.GetLabData(i).CV();
    }
    /**
     * ÿ��ʵ�����ݵľ�ֵ���ܾ�ֵ�Ĳ�
     * @param i
     * @return
     */
    public double SubtractOverallMean(int i)
    {
    	return format(this.GetLabData(i).Average()-this.OverallMean());
    }
    public double[] SubtractOverallMeans()
    {
    	double[] r=new double[this.BatchCount()];
    	for(int i=0;i<r.length;i++)
    	{
    		r[i]=this.SubtractOverallMean(i);
    	}
    	return r;
    }
    /**
     * ÿ��ʵ�����ݵľ�ֵ���ܾ�ֵ�Ĳ��ƽ��
     * @param i
     * @return
     */
    public double SubtractOverallMeanSquare(int i)
    {
    	double d=this.SubtractOverallMean(i);
    	return format2(d*d);
    }
    public double[] SubtractOverallMeanSquares()
    {
    	double[] r=new double[this.BatchCount()];
    	for(int i=0;i<r.length;i++)
    	{
    		r[i]=this.SubtractOverallMeanSquare(i);
    	}
    	return r;
    }
    /**
     * ��ȡÿ�����ݼ�ȥ�����ھ�ֵ�Ĳ�
     * @param i ��i��
     * @param j ��j��
     * @return ������ֵ֮��
     */
    public double SubtractMean(int i,int j)
    {
    	return this.batches.get(i).SubtractMean(j);
    }
    public double[] SubtractMeans(int i)
    {
    	double[] r=new double[this.MeasuringTimes()];
    	for(int j=0;j<r.length;j++)
    		r[j]=this.SubtractMean(i, j);
    	return r;
    }
    /**
     * ��ȡÿ�����ݼ�ȥ�����ھ�ֵ�Ĳ�ƽ��
     * @param i ��i��
     * @param j ��j��
     * @return ������ֵ֮���ƽ��
     */
    public double SubtractMeanSquare(int i,int j)
    {
    	return this.batches.get(i).SubtractMeanSquare(j);
    }
    public double[] SubtractMeanSquares(int i)
    {
    	double[] r=new double[this.MeasuringTimes()];
    	for(int j=0;j<r.length;j++)
    		r[j]=this.SubtractMeanSquare(i, j);
    	return r;
    }
}
