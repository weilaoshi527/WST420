package com.base;

import java.util.ArrayList;
import java.util.List;
 

/**
 * ��������֤����
 * @author sungiant
 *
 */
public class BaseVerification {
	
	/**
	 * ʵ������
	 */
	protected List<LabData> batches;
	/**
	 * ��У���ֵ
	 */
    protected double checkedValue;
    /**
     * Ԥ������С��λ��
     */
    public int DecimalBits; 
 
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
     * ��ȡָ�����ݵľ�ֵ
     * @param i ������к�
     * @return ��ֵ
     */
    public double GetLabDataAverage(int i)
    {    	    	
    	return this.GetLabData(i).Average();
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
     * ��ȡָ��������ƽ����
     * @param i
     * @return
     */
    public double GetLabDataSumOfSquare(int i)
    {
    	return this.GetLabData(i).SumOfSquares();
    }
    /**
     * ÿ��ʵ�����ݱ���ϵ��
     * @return
     */
    public double GetLabDataCV(int i)
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
}
