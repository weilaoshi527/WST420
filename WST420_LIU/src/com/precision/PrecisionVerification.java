package com.precision;


import com.base.BaseVerification;
import com.base.LabData;
import com.statistics.Probability;

/**
 * 
 * 精密度
 * @author sungiant
 *
 */
public class PrecisionVerification extends BaseVerification {
    double C;	//卡方 分布值    
    double probability=0.975;	//卡方查表概率
    
    public void Set_C(double p)
    {
    	probability=p;		
    }
    /**
     * 卡方查表参数
     * @param a 假排除率
     * @param l 检测水平数字，直接输入double
     */
    public void SetFalseRejectionRate(double a,double l)
    {  
    	probability=1-a/l;
    }
    public PrecisionVerification(double beta)
    {
        super(beta);
        C=-1.0;
    }
    
    /**
     * 重复标准差
     * @return
     */
    public double RepeatedStandardDeviation()
    {
        double sum=0;
        for(LabData d : batches)
        {
                sum+=d.IntraGroupVariance();
        }
        return format2(Math.sqrt(sum/BatchCount()));
    }
    
    /**
     * 总均值
     * @return
     */
    public double GroupAverage()
    {
        double sum=0;
        for(LabData d : batches)
            sum+=d.Average();
        return format(sum/BatchCount());
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
            double ave=d.Average()-GroupAverage();
            sum+=ave*ave;
        }
        return format2(sum/(BatchCount()-1));
    }
    
    /**
     * 期间标准差，最终结果
     * @return
     */
    public double GroupStandardDeviation()
    {
        double sum=0;
        sum+=(MeasuringTimes()-1)*RepeatedStandardDeviation()/MeasuringTimes();
        sum+=BetweenGroupVariance();
        return format2(Math.sqrt(sum));
    }
    /**
     * 重复精密度的自由度v
     * @return
     */
    public double v()
    {
    	return this.MeasuringTimes()*(this.BatchCount()-1);
    }
    /**
     * 重复精密度的验证值
     * @return
     */
    public double RepeatPrecisionValue()
    {
    	double T=v(); 
        C=Probability.re_chi2((int)T,probability);
        return format(checkedValue *Math.sqrt(C)/Math.sqrt(T));
    }
    /**
     * 期间精密度的自由度T
     * @return
     */
    public double T()
    {
    	return Freedom();
    }
    /**
     * 区间精密度的自由度，对应需求文档中公式9 原文有误!
     * @return
     */    
    public double Freedom()
    {
        double m=MeasuringTimes();
        double r=RepeatedStandardDeviation();
        double b=BetweenGroupVariance();
        double d=BatchCount();
        r=r*r;	//重复标准差需要平方
        double T=(m-1)*r+m* b;
        T=T*T;
        double k=(m-1)*r*r/d;
        k+=m*m*b*b/(d-1);
        T=T/k;
        return format(T);
    }
    /**
     * 精确度的SDI(Standard Deviation Index)数组
     * @return
     */
    public double[] sdi()
    {
    	double sd=this.GroupStandardDeviation();
    	double avg=this.GroupAverage();
    	double result[]=new double[this.BatchCount()*this.MeasuringTimes()];
    	int i=0;
    	for(LabData data : batches)
    	{
    		for(int j=0;j<data.Count();j++)
    		{
    			result[i]=format((data.Data(j)-avg)/sd);
    			i=i+1;
    		}
    	}
    	return result;
    }
    /**
     * 区间精准度的验证值
     * @return
     */    
    public double PrecisionValue()
    {
        double T=Freedom();        
        C=Probability.re_chi2((int)T,probability);
        return format(checkedValue *Math.sqrt(C)/Math.sqrt(T));
    }
}
