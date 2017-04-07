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
    
    
    public BaseVerification(double beta)
    {
            batches=new ArrayList<LabData>();
            checkedValue=beta;
            DecimalBits=-1;
    }     
    protected double format(double d)
    {
    	return LabData.Format45(d,DecimalBits);
    }
    protected double format2(double d)
    {
    	return LabData.Format452(d,DecimalBits);
    }
    public BaseVerification()
    {
          batches=new ArrayList<LabData>();
          DecimalBits=-1;
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
            Add(new LabData(labDataStr[i]));        
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
     * ÿ��ʵ�����ݾ�ֵ
     * @return
     */
    public double[] GetAverages()
    {
    	double[] temp=new double[BatchCount()];
    	for(int i=0;i<temp.length;i++)
    		temp[i]=this.GetLabData(i).Average();
    	return temp;
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
     * ÿ��ʵ���������ڷ���
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
     * ÿ��ʵ�����ݱ���ϵ��
     * @return
     */
    public double[] GetCV()
    {
    	double[] temp=new double[BatchCount()];
    	for(int i=0;i<temp.length;i++)
    		temp[i]=this.GetLabData(i).CV();
    	return temp;
    }
}
