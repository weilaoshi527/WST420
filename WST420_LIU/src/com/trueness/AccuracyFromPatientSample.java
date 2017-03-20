package com.trueness;

import com.base.BaseVerification;
import com.base.LabData;
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
     * ����ƫ��
     * @return
     */
    public double AbsoluteOffset()
    {
        double sum=0;
        int m=MeasuringTimes();
        for(int i=0;i<m;i++)
            sum+=(Ri().Data(i)-Rc().Data(i));
        return format(sum/m);
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
     * ��֤���������
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
    
    /**
     * ��֤���������
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
}
