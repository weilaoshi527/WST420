package com.precision;


import com.base.BaseVerification;
import com.base.LabData;
import com.statistics.Probability;

/**
 * 
 * ���ܶ�
 * @author sungiant
 *
 */
public class PrecisionVerification extends BaseVerification {
    double C;	//���� �ֲ�ֵ    
    double probability=0.975;	//����������
    
    public void Set_C(double p)
    {
    	probability=p;		
    }
    /**
     * ����������
     * @param a ���ų���
     * @param l ���ˮƽ���֣�ֱ������double
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
     * �ظ���׼��
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
     * �ܾ�ֵ
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
     * ���䷽��
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
     * �ڼ��׼����ս��
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
     * �ظ����ܶȵ����ɶ�v
     * @return
     */
    public double v()
    {
    	return this.MeasuringTimes()*(this.BatchCount()-1);
    }
    /**
     * �ظ����ܶȵ���ֵ֤
     * @return
     */
    public double RepeatPrecisionValue()
    {
    	double T=v(); 
        C=Probability.re_chi2((int)T,probability);
        return format(checkedValue *Math.sqrt(C)/Math.sqrt(T));
    }
    /**
     * �ڼ侫�ܶȵ����ɶ�T
     * @return
     */
    public double T()
    {
    	return Freedom();
    }
    /**
     * ���侫�ܶȵ����ɶȣ���Ӧ�����ĵ��й�ʽ9 ԭ������!
     * @return
     */    
    public double Freedom()
    {
        double m=MeasuringTimes();
        double r=RepeatedStandardDeviation();
        double b=BetweenGroupVariance();
        double d=BatchCount();
        r=r*r;	//�ظ���׼����Ҫƽ��
        double T=(m-1)*r+m* b;
        T=T*T;
        double k=(m-1)*r*r/d;
        k+=m*m*b*b/(d-1);
        T=T/k;
        return format(T);
    }
    /**
     * ��ȷ�ȵ�SDI(Standard Deviation Index)����
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
     * ���侫׼�ȵ���ֵ֤
     * @return
     */    
    public double PrecisionValue()
    {
        double T=Freedom();        
        C=Probability.re_chi2((int)T,probability);
        return format(checkedValue *Math.sqrt(C)/Math.sqrt(T));
    }
}
