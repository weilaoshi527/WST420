package com.base;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal; 
 

/**
 * 基础：验证方法
 * @author sungiant
 *
 */
public class BaseVerification {
	
	protected List<LabData> batches;	//实验数据
    protected double checkedValue; //待校验的值
    /**
     * 预保留的小数位数
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
     * 批次总数
     * @return
     */
    public int BatchCount()
    {
        return batches.size();
    }
    
    /**
     * 测量次数
     * @return
     */
    public int MeasuringTimes()
    {
        if(batches.size()>0)
            return batches.get(0).Count();
        return 0;
    }
    
    /**
     * 添加测量数据
     * @param data
     */
    public void Add(LabData data)
    {
    	data.DecimalBits=this.DecimalBits;
        batches.add(data);
    }
    
    /**
     * 获取第几批实验数据
     * @param index
     * @return
     */
    public LabData GetLabData(int index)
    {
    	return batches.get(index);
    }
}
