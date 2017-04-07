package com.trueness;

import com.base.BaseVerification;
import com.base.LabData;
import com.base.Print4;
import com.statistics.Probability;

/**
 * ��ȷ����֤����������
 * @author sungiant
 *
 */
public class AccuracyFromPatientSample extends BaseVerification
{
    double t;	
    /**
     * ���ų��ʣ�Ĭ��ֵ0.01
     */    
    public double FalseRejectionRate;		
    public AccuracyFromPatientSample(double deta)
    {
        super(deta);
        checkedValue=deta;
        FalseRejectionRate=0.01;
        t=-1.0;
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
     * ����������ľ���ƫ��
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
}
