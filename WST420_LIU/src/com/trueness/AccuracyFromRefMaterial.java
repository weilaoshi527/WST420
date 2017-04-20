package com.trueness;

import com.base.BaseVerification;
import com.base.LabData;
import com.base.Print4;
import com.statistics.Probability;

/**
 * 正确度验证：参考物质
 * @author sungiant
 *
 */
public class AccuracyFromRefMaterial extends BaseVerification
{
	/**
	 * 参考物质赋值 单位mg/dL,默认值40
	 */    
    double XfromRef;		
    /**
     * 室间质评结果标准差，默认值1.73
     */
    double Sprogram;		
    /**
     * 参加室间质评的实验室数量，默认值135
     */    
    int LabsCount;	
    /**
     * 参考物质测量标准不确定度=Sprogram/Sqrt(LabsCount)
     */
    double Sa=0d;

    
    /**
     * 假排除率，默认值0.01
     */    
    double FalseRejectionRate;	

    double t;				//t分布值

    /**
     * 参考物质构造方法
     */
    public AccuracyFromRefMaterial()
    {
        super();
        XfromRef=40;
        Sprogram=1.73;
        LabsCount=135;
        FalseRejectionRate=0.01;
        t=-1.0;
    }
    /**
     * 获取T查表值
     * @return
     */
    public double GetT()
    {
    	if(t<0)
            t= Probability.re_t(Freedom(),1-FalseRejectionRate);
    	return t;
    }
    /**
     * 设置假排除率，默认值0.01
     */  
    public void SetFalseRejectionRate(double rate)
    {
    	FalseRejectionRate = rate;
    }
    /**
     * 设置参加室间质评的实验室数量，默认值135
     */
    public void SetLabsCount(int labs)
    {
    	LabsCount = labs;
    }
    /**
     * 设置室间质评结果标准差，默认值1.73
     */
    public void SetSprogram(double spro)
    {
    	Sprogram = spro;
    }
    /**
     * 设置测量标准不确定度
     * @param sprogram 室间质评结果标准差
     * @param labsCount 参加室间质评的实验室数量
     */
    public void SetCalcSa(double sprogram,int labsCount)
    {
    	this.SetLabsCount(labsCount);
    	this.SetSprogram(sprogram);
    	this.Sa=sprogram/Math.sqrt(labsCount);
    }
    /**
     * 设置测量标准不确定度
     * @param sa
     */
    public void SetSa(double sa)
    {
    	this.Sa=sa;
    }
    public double GetSa()
    {
    	return this.Sa;
    }

    /**
     * 设置参考物质赋值 单位mg/dL,默认值40
     */
    public void SetXfromRef(double ref)
    {
    	XfromRef = ref;
    }
    /**
     * 一次性设置好所有数据
     * @param dataStr 形如37 38;39 37;38 36;39 38;38 37
     */
    public void SetLabData(String dataStr)
    {
    	super.SetLabData(dataStr);
    }
    /**
     * 获取第几批第几个数据
     * @param batchIndex
     * @param dataIndex
     * @return
     */
    public double GetLabDataByString(int batchIndex,int dataIndex)
    {
    	return super.GetLabData(batchIndex).Data(dataIndex);
    }
    /**
     * 测量次数
     */
    public int MeasuringTimes()
    {
    	return super.MeasuringTimes();
    }
    
    /**
     * 批次总数
     */
    public int BatchCount()
    {
    	return super.BatchCount();
    }
    /**
     * 获取第几批第几次实验数据-与总均值之差
     */
    public double GetLabDataSubtractOverallMean(int batchIndex,int dataIndex)
    {
    	return super.GetLabData(batchIndex).Data(dataIndex)-super.OverallMean();
    }
    /**
     * 获取第几批第几次实验数据-与总均值之差平方
     */
    public double GetLabDataSubtractOverallMeanSquare(int batchIndex,int dataIndex)
    {
    	double d = this.GetLabDataSubtractOverallMean(batchIndex,dataIndex);
    	return LabData.Format452(d*d,super.DecimalBits);
    }
    /**
     * 获取第几批第几次实验数据-SDI值
     */
    public double GetSDI(int batchIndex,int dataIndex)
    {   	
    	return super.GetSDI(batchIndex, dataIndex);
    }
    /**
     * 总均值
     * @return
     */
    public double GroupAverage()
    {
        double sum=0;
        for(LabData data : batches)
            for(int i=0;i<data.Count();i++)
                sum+=data.Data(i);
        sum=sum/(BatchCount()*MeasuringTimes());
        return format(sum);
    }
    public double OverallMean()
    {
        return super.OverallMean();
    }
    /**
     * 参考物质测量偏移值
     * @return
     */    
    public double Deviant()
    {
        return format(GroupAverage()-XfromRef);
    }
    /**
     * 标准差
     * @return
     */
    public double StandardDeviation()
    {
        double sum=0;
        double ave=GroupAverage();
        for(LabData data : batches)
            for(int i=0;i<data.Count();i++)
                sum+=(data.Data(i)-ave)*(data.Data(i)-ave);
        return format2(Math.sqrt(sum/(MeasuringTimes()*BatchCount()-1)));
    }
    public double SD()
    {
    	return this.StandardDeviation();
    }
    /**
     * 自由度
     * @return
     */    
    public int Freedom()
    {
        return MeasuringTimes()*BatchCount() -1;
    }
    /**
     * 验证区间的下限
     * @return
     */
    public double MinOfInterval()
    {        
        double s=StandardDeviation();        
        return format(GroupAverage()-this.GetT()*Math.sqrt(s*s +Sa *Sa));
    }
    /**
     * 验证区间的上限
     * @return
     */
    public double MaxOfInterval()
    {        
        double s=StandardDeviation();        
        return format(GroupAverage()+this.GetT()*Math.sqrt(s*s +Sa *Sa));
    }
}
