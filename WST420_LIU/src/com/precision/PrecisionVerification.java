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
    //double C;	//���� �ֲ�ֵ    
    public double C1,C2; //�����ֲ�������ֵ��C1���ظ���C2������ 
    double probability=0.975;	//����������
    /**
     * ���ų��ʣ�Ĭ��ֵΪ5%
     */
    public double FalseRejectionRate=0.05;
    /**
     * ���ˮƽ��Ĭ��ֵΪ2
     */
    public double DetectionLevel=2;
    
    /**
     * �������Ƶľ��ܶ�
     */
    public double PrecisionToBeDetected;
    /**
     * �������Ƶı���ϵ��
     */
    public double RepeatedVCToBeDetected;
    /**
     * ��ʽ5
     * @param vc���������Ƶ��ظ�����ϵ��
     * @param average�����Ҳ�������ܾ�ֵ
     */
    public void setRepeatedVC(double vc,double average)
    {
    	RepeatedVCToBeDetected=vc;
    	RepeatedVCToBeDetected=vc*average;
    }
    
    /**
     * ����������
     * @param a ���ų���
     * @param l ���ˮƽ���֣�ֱ������double
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
     * �ڼ��׼��
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
    	double v=v(); 
        C1=Probability.re_chi2((int)v,probability);
        return format(checkedValue *Math.sqrt(C1)/Math.sqrt(v));
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
     * ���侫�ܶȵ����ɶȣ���ʽ9 
     * @return
     */    
    public double Freedom()
    {
        double m=MeasuringTimes();
        double r=RepeatedStandardDeviation();
        double b=BetweenGroupVariance();
        double d=BatchCount();
        r=r*r;			//�ظ���׼����Ҫƽ��
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
     * ���侫׼�ȵ���ֵ֤
     * @return
     */    
    public double PrecisionValue()
    {
        double T=Freedom();        
        C2=Probability.re_chi2((int)T,probability);
        return format(checkedValue *Math.sqrt(C2)/Math.sqrt(T));
    }
}
