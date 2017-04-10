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
     * 设置厂家声称的校验值
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
     * 批间标准差
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
     * 批间方差
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
     * 总均值
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
            Add(LabData.CreateByStr(labDataStr[i]));        
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
     * 获取指定组的SDI参数组
     * @param 第i组
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
     * 获取指定数据的SDI值
     * @param i 第i批数据
     * @param j 第j个数据
     * @return SDI值 
     */
    public double GetSDI(int i,int j)
    {
    	double sd=this.BetweenGroupSD();
    	double avg=this.OverallMean();    	
    	return format((this.batches.get(i).Data(j)-avg)/sd);
    }
    /**
     * 每组实验数据均值
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
     * 获取制定组数据的均值
     * @param i 组的序列号
     * @return 均值
     */
    public double GetMean(int i)
    {    	    	
    	return this.GetLabData(i).Average();
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
     * 获取指定数据组的批内方差
     * @param i
     * @return
     */
    public double GetIntraGroupVar(int i)
    {
    	return this.GetLabData(i).IntraGroupVariance();
    }
    /**
     * 每组实验数据批内标准差
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
     * 获取指定组的样本标准差
     * @param i
     * @return
     */
    public double GetSampleStandarDeviation(int i)
    {
    	return this.GetLabData(i).SampleStandardDeviation();
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
     * 获取指定组的离差平方和
     * @param i
     * @return
     */
    public double GetSumOfSquare(int i)
    {
    	return this.GetLabData(i).SumOfSquares();
    }
    /**
     * 每组实验数据变异系数
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
     * 每组实验数据的均值与总均值的差
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
     * 每组实验数据的均值与总均值的差的平方
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
     * 获取每个数据减去其组内均值的差
     * @param i 第i组
     * @param j 第j个
     * @return 与改组均值之差
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
     * 获取每个数据减去其组内均值的差平方
     * @param i 第i组
     * @param j 第j个
     * @return 与改组均值之差的平方
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
