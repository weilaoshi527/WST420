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
    //double C;	//卡方 分布值    
    public double C1,C2; //卡方分布的两个值，C1是重复，C2是区间 
    double probability=0.975;	//卡方查表概率
    /**
     * 假排除率，默认值为5%
     */
    public double FalseRejectionRate=0.05;
    /**
     * 检测水平，默认值为2
     */
    public double DetectionLevel=2;
    
    /**
     * 厂家声称的精密度
     */
    public double PrecisionToBeDetected;
    /**
     * 厂家声称的变异系数
     */
    public double RepeatedVCToBeDetected;
    /**
     * 公式5
     * @param vc，厂家声称的重复变异系数
     * @param average，厂家测量结果总均值
     */
    public void setRepeatedVC(double vc,double average)
    {
    	RepeatedVCToBeDetected=vc;
    	RepeatedVCToBeDetected=vc*average;
    }
    
    /**
     * 卡方查表参数
     * @param a 假排除率
     * @param l 检测水平数字，直接输入double
     */
    public void setFalseRejectionRate(double a,double l)
    {  
    	FalseRejectionRate =a;
    	DetectionLevel=l;
    	probability=1-a/l; 
    }
    public PrecisionVerification(double beta)
    {
        super(beta);
        PrecisionToBeDetected=beta;
        C1=-1.0;
        C2=-1.0;
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
     * 期间标准差
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
    	double v=v(); 
        C1=Probability.re_chi2((int)v,probability);
        return format(checkedValue *Math.sqrt(C1)/Math.sqrt(v));
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
     * 区间精密度的自由度，公式9 
     * @return
     */    
    public double Freedom()
    {
        double m=MeasuringTimes();
        double r=RepeatedStandardDeviation();
        double b=BetweenGroupVariance();
        double d=BatchCount();
        r=r*r;			//重复标准差需要平方
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
    public double[] getSDI()
    {
    	double sd=this.GroupStandardDeviation();
    	double avg=this.GroupAverage();
    	double[] result=new double[this.BatchCount()*this.MeasuringTimes()];
    	int i=0;
    	for(LabData labData : this.batches)
    	{
    		for(int j=0;j<labData.Count();j++)
    		{
    			result[i]=format((labData.Data(j)-avg)/sd);
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
        C2=Probability.re_chi2((int)T,probability);
        return format(checkedValue *Math.sqrt(C2)/Math.sqrt(T));
    }
}
