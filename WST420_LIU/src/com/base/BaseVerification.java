package com.base;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal; 
 

/**
 * ��������֤����
 * @author sungiant
 *
 */
public class BaseVerification {
	
	protected List<LabData> batches;	//ʵ������
    protected double checkedValue; //��У���ֵ
    /**
     * Ԥ������С��λ��
     */
    public int DecimalBits; 
    
    
    public BaseVerification(double beta)
    {
            batches=new ArrayList<LabData>();
            checkedValue=beta;
            DecimalBits=-1;
    }     
    protected double format(double d)
    {
    	return LabData.Format45(d,DecimalBits);
    }
    protected double format2(double d)
    {
    	return LabData.Format452(d,DecimalBits);
    }
    public BaseVerification()
    {
          batches=new ArrayList<LabData>();
          DecimalBits=-1;
    }
    
    
    /**
     * ��������
     * @return
     */
    public int BatchCount()
    {
        return batches.size();
    }
    
    /**
     * ��������
     * @return
     */
    public int MeasuringTimes()
    {
        if(batches.size()>0)
            return batches.get(0).Count();
        return 0;
    }
    
    /**
     * ��Ӳ�������
     * @param data
     */
    public void Add(LabData data)
    {
    	data.DecimalBits=this.DecimalBits;
        batches.add(data);
    }
    
    /**
     * ��ȡ�ڼ���ʵ������
     * @param index
     * @return
     */
    public LabData GetLabData(int index)
    {
    	return batches.get(index);
    }
}
