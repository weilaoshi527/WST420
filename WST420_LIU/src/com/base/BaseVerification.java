package com.base;

import java.util.ArrayList;
import java.util.List;
 

/**
 * 基础：验证方法
 * @author sungiant
 *
 */
public class BaseVerification {
	
	protected List<LabData> batches;	//实验数据
    protected double checkedValue; //待校验的值
    /**
     * 预保留的小数位数
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
     * 批次总数
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
     * 测量次数
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
     * 添加测量数据
     * @param data
     */
    public void Add(LabData data)
    {
    	data.DecimalBits=this.DecimalBits;
        batches.add(data);
    }
    /**
     * 一次性设置好所有数据
     * @param dataStr 形如140 140 140;139 138 139;140 141 140
     */
    public void SetLabData(String dataStr)
    {
    	String[] labDataStr=dataStr.split(";");        
        for(int i=0;i<labDataStr.length;i++)
            Add(new LabData(labDataStr[i]));        
    }
    
    /**
     * 获取第几批实验数据
     * @param index
     * @return
     */
    public LabData GetLabData(int index)
    {
    	return batches.get(index);
    }
    /**
     * 每组实验数据和
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
     * 每组实验数据均值
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
     * 每组实验数据批内方差平方
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
     * 每组实验数据批内方差
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
     * 每组实验数据标准差
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
     * 每组实验数据离差平方和
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
     * 每组实验数据变异系数
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
