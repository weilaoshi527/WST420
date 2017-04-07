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
    public double XfromRef;		
    /**
     * 室间质评结果标准差，默认值1.73
     */
    public double Sprogram;		
    /**
     * 参加室间质评的实验室数量，默认值135
     */    
    public int LabsCount;		
    /**
     * 假排除率，默认值0.01
     */    
    public double FalseRejectionRate;	

    double t;				//t分布值

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
    
    /**
     * 参考物质测量偏移值
     * @return
     */    
    public double Deviant()
    {
        return format(GroupAverage()-XfromRef);
    }
    public String Deviant2()
    {
    	return Print4.Print(Deviant());
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
    public String StandardDeviation2()
    {
    	return Print4.Print(StandardDeviation());
    }
    /**
     * 参考物质测量标准不确定度
     * @return
     */    
    public double Uncertainty()
    {
         return format(Sprogram/Math.sqrt(LabsCount));
    }
    public String Uncertainty2()
    {
    	return Print4.Print(Uncertainty());
    }
    /**
     * 自由度，对应公式24,原文有误!
     * @return
     */    
    public int Freedom()
    {
        return MeasuringTimes()*BatchCount() -1;
    }
    public String Freedom2()
    {
    	return Print4.Print(Freedom());
    }
    /**
     * 验证区间的下限
     * @return
     */
    public double MinOfInterval()
    {
        if(t<0)
            t= Probability.re_t(Freedom(),1-FalseRejectionRate);
        double s=StandardDeviation();
        double u=Uncertainty();
        return format(GroupAverage()-t*Math.sqrt(s*s +u *u));
    }
    public String MinOfInterval2()
    {
    	return Print4.Print(MinOfInterval());
    }
    /**
     * 验证区间的上限
     * @return
     */
    public double MaxOfInterval()
    {
        if(t<0)
            t= Probability.re_t(Freedom(),1-FalseRejectionRate);
        double s=StandardDeviation();
        double u=Uncertainty();
        return format(GroupAverage()+t*Math.sqrt(s*s +u *u));
    }
    public String MaxOfInterval2()
    {
    	return Print4.Print(MaxOfInterval());
    }
}
