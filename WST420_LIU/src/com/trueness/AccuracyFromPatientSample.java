package com.trueness;

import com.base.BaseVerification;
import com.base.LabData;
import com.linear.LinearRegression;
import com.statistics.Probability;

/**
 * ��ȷ����֤����������
 * @author sungiant
 *
 */
public class AccuracyFromPatientSample extends BaseVerification
{
	LinearRegression line;
    double t;	
    /**
     * ���ų��ʣ�Ĭ��ֵ0.01
     */    
    double FalseRejectionRate;
    double AllowableTotalErrorConc;
    double AllowableTotalErrorPercent;
    /**
     * �����������췽��
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
     * ����Tea����, Allowable Total Error
     * @param value
     */
    public void SetTea(double conc,double percent)
    {
    	AllowableTotalErrorConc=conc;
    	AllowableTotalErrorPercent=percent;
    }
    /**
     * ��ȡTea������Ũ��
     * @return
     */
    public double GetConcOfTea()
    {    	
    	return AllowableTotalErrorConc;
    }
    /**
     * ��ȡTea�����İٷֱ�
     * @return
     */
    public double GetPercentOfTea()
    {
    	return AllowableTotalErrorPercent;
    }
    /**
     * ��ȡt���ֵ
     * @return
     */
    public double GetT()
    {
    	if(t<0)
    		t=Probability.re_t(this.MeasuringTimes()-1,1-FalseRejectionRate);	
    	return t;
    }
    /**
     * Ԥ������С��λ��
     * @param bits
     */
    public void SetDecimalBits(int bits)
    {
    	super.DecimalBits=bits;
    }
    /**
     * ���ó������Ƶ�У��ֵ
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
     * ����t������
     * @param p
     */
    public void SetPropability(double p)
    {
    	FalseRejectionRate=1-p;
        t=Probability.re_t(MeasuringTimes()-1,p);
    }
    /**
     * ���ü��ų��ʣ�Ĭ��ֵ0.01
     */
    public void SetFalseRejectionRate(double rate)
    {
    	FalseRejectionRate = rate;
    }
    /**
     * ��������
     */
    public int MeasuringTimes()
    {
    	return super.MeasuringTimes();
    }
    
    /**
     * ��������
     */
    public int BatchCount()
    {
    	return super.BatchCount();
    }
    /**
     * ʵ������
     */
    public LabData Ri()
    {
       return batches.get(0);
    }
    
    /**
     * �Ƚ�����
     */
    public LabData Rc()
    {
        return batches.get(1);
    }
    /**
     * ��ȡ�ڼ����ڼ�������
     * @param batchIndex
     * @param dataIndex
     * @return
     */
    public double GetLabDataByString(int batchIndex,int dataIndex)
    {
    	return super.GetLabData(batchIndex).Data(dataIndex);
    }
    /**
     * ����ʵ�����ݶ���
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
     * ���ñȽ����ݶ���
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
     * ָ����ŵľ���ƫ��ֵ
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
     * ָ����ŵ����ƫ��ֵ     
     * @param i ��i��
     * @return
     */
    public double RelativeOffsetArray(int i)
    {
    	double rc=Rc().Data(i);
    	double arr = AbsoluteOffsetArray(i);
        return format(arr/rc);
    }
    /**
     * ָ����ŵ����ƫ��ֵ�����ƫ����ֵ֮�� 
     * @param i ��i��
     * @return
     */
    public double RelativeOffsetArraySubtract(int i)
    {
        return format(this.AbsoluteOffsetArray(i)-this.AbsoluteOffset());
    }
    /**
     * ָ����ŵ����ƫ��ֵ�����ƫ����ֵ֮��ƽ��
     * @param i ��i��
     * @return
     */
    public double RelativeOffsetArraySubtractSquare(int i)
    {
    	double d = this.AbsoluteOffsetArray(i)-this.AbsoluteOffset();
        return format(d*d);
    }
    /**
     * ����������ľ���ƫ����ֵ
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
     * Ri��Rc������ľ���ƫ��
     * @param i
     * @return
     */
    public double RiSubtractRc(int i)
    {    	
    	return this.AbsoluteOffsetArray(i);
    }
    /**
     * Ri��Rc���������ƫ��֮ƽ��
     * @param i
     * @return
     */
    public double RiSubtractRcSquare(int i)
    {
    	double d=this.RiSubtractRc(i);
    	return format2(d*d);
    }
    /**
     * ��������������ƫ��
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
     * ����ƫ�Ƶı�׼��
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
     * ���ƫ�Ƶı�׼��
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
     * ��֤����ľ�������
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
     * ��֤����ľ�������
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
     * ��֤������������
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
     * ��֤������������
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
     * ������������
     */
    public LinearRegression GetLine()
    {
    	if(line==null)
    	{
    		line=new LinearRegression();
    		line.Add(this.Rc());	//���Y    		
    		line.SetX(this.Ri());	//���X
    	}
    	return line;
    }
    /**
     * ����ָ���ľ�ֵ
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
     * ����ָ��
     * @param i ��i��
     * @return
     */
    public double ErrorIndex(int i)
    {
    	return format(0-this.RiSubtractRc(i)/this.GetConcOfTea());
    }
    /**
     * ����ָ������Сֵ
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
     * ����ָ�������ֵ
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
