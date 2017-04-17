package com.trueness;

import com.base.BaseVerification;
import com.base.LabData;
import com.linear.LinearRegression;
import com.statistics.Probability;

/**
 * 正确度验证：病人样本
 * @author sungiant
 *
 */
public class AccuracyFromPatientSample extends BaseVerification
{
	LinearRegression line;
    double t;	
    /**
     * 假排除率，默认值0.01
     */    
    double FalseRejectionRate;
    double AllowableTotalErrorConc;
    double AllowableTotalErrorPercent;
    /**
     * 病人样本构造方法
     */
    public AccuracyFromPatientSample()
    {
        super();        
        FalseRejectionRate=0.01;
        t=-1.0;
        line=null;
        AllowableTotalErrorConc=Double.MAX_VALUE;
        AllowableTotalErrorPercent=0d;
    }     
   
    /**
     * 设置Tea参数, Allowable Total Error
     * @param value
     */
    public void SetTea(double conc,double percent)
    {
    	AllowableTotalErrorConc=conc;
    	AllowableTotalErrorPercent=percent;
    }
    /**
     * 获取Tea参数的浓度
     * @return
     */
    public double GetConcOfTea()
    {    	
    	return AllowableTotalErrorConc;
    }
    /**
     * 获取Tea参数的百分比
     * @return
     */
    public double GetPercentOfTea()
    {
    	return AllowableTotalErrorPercent;
    }
    /**
     * 获取t查表值
     * @return
     */
    public double GetT()
    {
    	if(t<0)
    		t=Probability.re_t(this.MeasuringTimes()-1,1-FalseRejectionRate);	
    	return t;
    }
    /**
     * 预保留的小数位数
     * @param bits
     */
    public void SetDecimalBits(int bits)
    {
    	super.DecimalBits=bits;
    }
    /**
     * 设置厂家声称的校验值
     * @param beta
     */
    public void SetCheckedValue(double beta)
    {
    	super.SetCheckedValue(beta);
    }
    public void Set_t(double p)
    {
    	FalseRejectionRate=1-p;
        t=Probability.re_t(MeasuringTimes()-1,p);			
    }
    /**
     * 设置t查表概率
     * @param p
     */
    public void SetPropability(double p)
    {
    	FalseRejectionRate=1-p;
        t=Probability.re_t(MeasuringTimes()-1,p);
    }
    /**
     * 设置假排除率，默认值0.01
     */
    public void SetFalseRejectionRate(double rate)
    {
    	FalseRejectionRate = rate;
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
     * 实验数据
     */
    public LabData Ri()
    {
       return batches.get(0);
    }
    
    /**
     * 比较数据
     */
    public LabData Rc()
    {
        return batches.get(1);
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
     * 设置实验数据对象
     */
    public void SetRi(LabData data)
    {
    	if(this.batches.size()>0)
    		this.batches.set(0, data);
    	else
    		this.batches.add(data);
    }
    public void SetRiByStr(String dataStr)
    {
    	this.SetRi(LabData.CreateByStr(dataStr));
    }
    /**
     * 设置比较数据对象
     */
    public void SetRc(LabData data)
    {
    	if(this.batches.size()>1)
    		this.batches.set(1, data);
    	else
    		if(this.batches.size()>0)
    			this.batches.add(data);
    		else
    		{
    			this.batches.add(null);
    			this.batches.add(data);
    		}
    }
    public void SetRcByStr(String dataStr)
    {
    	this.SetRc(LabData.CreateByStr(dataStr));
    }
    /**
     * 指定序号的绝对偏移值
     * @param i
     * @return
     */
    public double AbsoluteOffsetArray(int i)
    {
    	double Ri=Ri().Data(i);
    	double Rc=Rc().Data(i);
    	
    	return format(Ri-Rc);
    }
    /**
     * 指定序号的相对偏移值     
     * @param i 第i个
     * @return
     */
    public double RelativeOffsetArray(int i)
    {
    	double rc=Rc().Data(i);
    	double arr = AbsoluteOffsetArray(i);
        return format(arr/rc);
    }
    /**
     * 指定序号的相对偏移值与绝对偏移总值之差 
     * @param i 第i个
     * @return
     */
    public double RelativeOffsetArraySubtract(int i)
    {
        return format(this.AbsoluteOffsetArray(i)-this.AbsoluteOffset());
    }
    /**
     * 指定序号的相对偏移值与绝对偏移总值之差平方
     * @param i 第i个
     * @return
     */
    public double RelativeOffsetArraySubtractSquare(int i)
    {
    	double d = this.AbsoluteOffsetArray(i)-this.AbsoluteOffset();
        return format(d*d);
    }
    /**
     * 两个方法间的绝对偏移总值
     * @return
     */
    public double AbsoluteOffset()
    {
        double sum=0;

        int m=MeasuringTimes();
        
        for(int i=0;i<m;i++)
            sum+=AbsoluteOffsetArray(i);
        return format(sum/m);
    }
    /**
     * Ri和Rc方法间的绝对偏移
     * @param i
     * @return
     */
    public double RiSubtractRc(int i)
    {    	
    	return this.AbsoluteOffsetArray(i);
    }
    /**
     * Ri和Rc方法间绝对偏移之平方
     * @param i
     * @return
     */
    public double RiSubtractRcSquare(int i)
    {
    	double d=this.RiSubtractRc(i);
    	return format2(d*d);
    }
    /**
     * 两个方法间的相对偏移
     * @return
     */
    public double RelativeOffset()
    {
    	double sum=0;
    	int mt=MeasuringTimes();
        
        for(int i=0;i<mt;i++)
            sum+=RelativeOffsetArray(i);
        return format(sum/mt);
    }
    /**
     * 绝对偏移的标准差
     * @return
     */
    public double StandardDeviation()
    {
        double sum=0;
        double a=AbsoluteOffset();
        int m=MeasuringTimes();
        for(int i=0;i<m;i++)
        {
            double b=Ri().Data(i)-Rc().Data(i)-a;
            sum+=b*b;
        }
        return format2(Math.sqrt(sum/(m-1)));
    }
    /**
     * 相对偏移的标准差
     * @return
     */
    public double RelativeStandardDeviation()
    {
    	double sum=0;
        double a=RelativeOffset();
        int m=MeasuringTimes();
        for(int i=0;i<m;i++)
        {
            double b=Ri().Data(i)-Rc().Data(i)-a;
            sum+=b*b;
        }
        return format2(Math.sqrt(sum/(m-1)));
    }
    /**
     * 验证区间的绝对下限
     * @return
     */
    public double MinOfInterval()
    {
        int m=MeasuringTimes();
        double s=StandardDeviation();
        t=GetT();
        return format(checkedValue-t*s/Math.sqrt(m));
    }
    /**
     * 验证区间的绝对上限
     * @return
     */
    public double MaxOfInterval()
    {
        int m=MeasuringTimes();
        double s=StandardDeviation();
        t=GetT();
        return format(checkedValue+t*s/Math.sqrt(m));
    }
    /**
     * 验证区间的相对下限
     * @return
     */
    public double RelativeMinOfInterval()
    {
        int m=MeasuringTimes();
        double s=RelativeStandardDeviation();
        t=GetT();
        return format(checkedValue-t*s/Math.sqrt(m));
    }
    /**
     * 验证区间的相对上限
     * @return
     */
    public double RelativeMaxOfInterval()
    {
        int m=MeasuringTimes();
        double s=RelativeStandardDeviation();
        t=GetT();	
        return format(checkedValue+t*s/Math.sqrt(m));
    }
    
    /**
     * 患者样本线性
     */
    public LinearRegression GetLine()
    {
    	if(line==null)
    	{
    		line=new LinearRegression();
    		line.Add(this.Rc());	//添加Y    		
    		line.SetX(this.Ri());	//添加X
    	}
    	return line;
    }
    /**
     * 错误指数的均值
     * @return
     */
    public double AverageErrorIndex()
    {
    	double mt = this.MeasuringTimes();
    	double avg=0;
    	for(int i=0;i<mt;i++)
    		avg+=this.ErrorIndex(i);
    	return format(avg/mt);
    }
    /**
     * 错误指数
     * @param i 第i个
     * @return
     */
    public double ErrorIndex(int i)
    {
    	return format(0-this.RiSubtractRc(i)/this.GetConcOfTea());
    }
    /**
     * 错误指数的最小值
     * @return
     */
    public double MinEI()
    {
    	double mt = this.MeasuringTimes();
    	double d=this.ErrorIndex(0);
    	for(int i=1;i<mt;i++)
    	{
    		if(this.ErrorIndex(i)<d)
    			d=this.ErrorIndex(i);
    	}
    	return d;
    }
    /**
     * 错误指数的最大值
     * @return
     */
    public double MaxEI()
    {
    	double mt = this.MeasuringTimes();
    	double d=this.ErrorIndex(0);
    	for(int i=1;i<mt;i++)
    	{
    		if(this.ErrorIndex(i)>d)
    			d=this.ErrorIndex(i);
    	}
    	return d;
    }
    
}
