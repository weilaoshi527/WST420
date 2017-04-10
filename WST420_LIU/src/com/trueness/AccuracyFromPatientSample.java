package com.trueness;

import com.base.BaseVerification;
import com.base.LabData;
import com.base.Print4;
import com.linear.*;
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
    public double FalseRejectionRate;	
    /*
    public AccuracyFromPatientSample(double deta)
    {
        super(deta);
        checkedValue=deta;
        FalseRejectionRate=0.01;
        t=-1.0;
    }
    */
    public AccuracyFromPatientSample()
    {
        super();        
        FalseRejectionRate=0.01;
        t=-1.0;
        line=null;
    }
    public void Set_t(double p)
    {
    	FalseRejectionRate=1-p;
        t=Probability.re_t(MeasuringTimes()-1,p);			
    }
    
    public LabData Ri()
    {
       return batches.get(0);
    }
    
    public LabData Rc()
    {
        return batches.get(1);
    }
    public void SetRi(LabData data)
    {
    	if(this.batches.size()>0)
    		this.batches.set(0, data);
    	else
    		this.batches.add(data);
    }
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
    /**
     * ����ƫ������
     * @return
     */
    public double[] AbsoluteOffsetArray()
    {
    	int m=MeasuringTimes();
    	double[] array=new double[m];    	
        for(int i=0;i<m;i++)
        {
        	double Ri=Ri().Data(i);
        	double Rc=Rc().Data(i);
            array[i]=format(Ri-Rc);
        }
        return array;
    }
    /**
     * ָ����ŵľ���ƫ��ֵ
     * @param i
     * @return
     */
    public double AbsoluteOffsetArray(int i)
    {
    	return AbsoluteOffsetArray()[i];
    }
    /**
     * ���ƫ������
     * @return
     */
    public double[] RelativeOffsetArray()
    {    	
    	double[] array=AbsoluteOffsetArray();  	
        for(int i=0;i<array.length;i++)
        {        	
        	double Rc=Rc().Data(i);
            array[i]=format(array[i]/Rc);
        }
        return array;
    }
    /**
     * ָ����ŵ����ƫ��ֵ     
     * @param i ��i��
     * @return
     */
    public double RelativeOffsetArray(int i)
    {
    	return RelativeOffsetArray()[i];
    }
    /**
     * ����������ľ���ƫ����ֵ
     * @return
     */
    public double AbsoluteOffset()
    {
        double sum=0;
        double[] array=AbsoluteOffsetArray(); 
        
        for(int i=0;i<array.length;i++)
            sum+=array[i];
        return format(sum/array.length);
    }
    public String AbsoluteOffset2()
    {
    	return Print4.Print(AbsoluteOffset());
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
     * Ri��Rc������ľ���ƫ������
     * @param i
     * @return
     */
    public double[] RiSubtractRcs()
    {
    	return this.AbsoluteOffsetArray();
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
     * Ri��Rc���������ƫ��֮ƽ����
     * @param i
     * @return
     */
    public double[] RiSubtractRcSquares()
    {
    	double d[]=new double[this.MeasuringTimes()];
    	for(int i=0;i<d.length;i++)
    		d[i]=this.RiSubtractRcSquare(i);
    	return d;
    }
    
    /**
     * ��������������ƫ��
     * @return
     */
    public double RelativeOffset()
    {
    	double sum=0;
        double[] array=RelativeOffsetArray(); 
        
        for(int i=0;i<array.length;i++)
            sum+=array[i];
        return format(sum/array.length);
    }
    public String RelativeOffset2()
    {
    	return Print4.Print(RelativeOffset());
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
    public String StandardDeviation2()
    {
    	return Print4.Print(StandardDeviation());
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
    public String RelativeStandardDeviation2()
    {
    	return Print4.Print(RelativeStandardDeviation());
    }
    /**
     * ��֤����ľ�������
     * @return
     */
    public double MinOfInterval()
    {
        int m=MeasuringTimes();
        double s=StandardDeviation();
         if(t<0)
            t=Probability.re_t(m-1,1-FalseRejectionRate);	
        return format(checkedValue-t*s/Math.sqrt(m));
    }
    public String MinOfInterval2()
    {
    	return Print4.Print(MinOfInterval());
    }
    /**
     * ��֤����ľ�������
     * @return
     */
    public double MaxOfInterval()
    {
        int m=MeasuringTimes();
        double s=StandardDeviation();
         if(t<0)
            t=Probability.re_t(m-1,1-FalseRejectionRate);	
        return format(checkedValue+t*s/Math.sqrt(m));
    }
    public String MaxOfInterval2()
    {
    	return Print4.Print(MaxOfInterval());
    }
    /**
     * ��֤������������
     * @return
     */
    public double RelativeMinOfInterval()
    {
        int m=MeasuringTimes();
        double s=RelativeStandardDeviation();
        if(t<0)
            t=Probability.re_t(m-1,1-FalseRejectionRate);	
        return format(checkedValue-t*s/Math.sqrt(m));
    }
    public String RelativeMinOfInterval2()
    {
    	return Print4.Print(RelativeMinOfInterval());
    }
    /**
     * ��֤������������
     * @return
     */
    public double RelativeMaxOfInterval()
    {
        int m=MeasuringTimes();
        double s=RelativeStandardDeviation();
        if(t<0)
            t=Probability.re_t(m-1,1-FalseRejectionRate);	
        return format(checkedValue+t*s/Math.sqrt(m));
    }
    public String RelativeMaxOfInterval2()
    {
    	return Print4.Print(RelativeMaxOfInterval());
    }
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
    	double[] ds=this.ErrorIndexes();
    	double avg=0;
    	for(int i=0;i<ds.length;i++)
    		avg+=ds[i];
    	return format(avg/ds.length);
    }
    /**
     * ����ָ��
     * @param i ��i��
     * @return
     */
    public double ErrorIndex(int i)
    {
    	return format(0-this.RiSubtractRc(i)/this.MeasuringTimes());
    }
    /**
     * ����ָ������Сֵ
     * @return
     */
    public double MinEI()
    {
    	double[] ds=this.ErrorIndexes();
    	double d=ds[0];
    	for(int i=1;i<ds.length;i++)
    	{
    		if(ds[i]<d)
    			d=ds[i];
    	}
    	return d;
    }
    /**
     * ����ָ�������ֵ
     * @return
     */
    public double MaxEI()
    {
    	double[] ds=this.ErrorIndexes();
    	double d=ds[0];
    	for(int i=1;i<ds.length;i++)
    	{
    		if(ds[i]>d)
    			d=ds[i];
    	}
    	return d;
    }
    /**
     * ����ָ��������     
     * @return
     */
    public double[] ErrorIndexes()
    {
    	double[] d=new double[this.MeasuringTimes()];
    	for(int i=0;i<d.length;i++)
    		d[i]=this.ErrorIndex(i);
    	return d;
    }
}
