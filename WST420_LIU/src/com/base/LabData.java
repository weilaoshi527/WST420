package com.base;

import java.math.BigDecimal;

/**
 * 基础：实验数据
 * @author sungiant
 *
 */
public class LabData {
	
    private double[] data;
    //public static int decimalBits;	//小数位数 
    //public  BaseVerification parent;
    public int DecimalBits;
    /**
     * 四舍五入格式化
     * @param d
     * @return 
     */
    public static double Format45(double d,int bits)
    {
    	if(bits>-1)
    	{
	    	BigDecimal bg = new BigDecimal(d);  
	        return bg.setScale(bits, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    	}
    	return d;
    }
    public static double Format452(double d,int bits)
    {
    	if(bits>-1)
    	{
	    	BigDecimal bg = new BigDecimal(d);  
	        return bg.setScale(bits*bits, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    	}
    	return d;
    }
    /**
     * 进一法格式化
     * @param d
     * @return
     */
    public static double Format1(double d,int bits)
    {
    	if(bits>-1)
    	{
    		double k=4.0;
    		for(int i=0;i<bits+1;i++)
    		{
    			k=k/10.0;
    		}
	    	BigDecimal bg = new BigDecimal(d+k);
	        return bg.setScale(bits, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    	}
    	return d;
    }
    public static double Format12(double d,int bits)
    {
    	if(bits>-1)
    	{
    		double k=4.0;
    		for(int i=0;i<bits*bits+1;i++)
    		{
    			k=k/10.0;
    		}
	    	BigDecimal bg = new BigDecimal(d+k);
	        return bg.setScale(bits*bits, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    	}
    	return d;
    }
    public LabData()
    {
            data=new double[0];
    }
    
    /**
     * 构造函数
     * @param dataStr，用空格隔开的浮点实验数据字符串
     * @throws Exception 
     */
    public LabData(String dataStr)
    {        	
        String[] d=dataStr.split(" ");
        data=new double[d.length];
        for(int i=0;i<d.length;i++)
            data[i]=Double.parseDouble(d[i]);
    }
    
    /**
     * 添加一个实验数据
     * @param d
     */
    public void Add(double d)
    {
        double[] temp=new double[data.length+1];
        for(int i=0;i<data.length;i++)
            temp[i]=data[i];
        temp[data.length]=d;
        data=new double[temp.length];
        for(int i=0;i<data.length;i++)
            data[i]=temp[i];
    }
    
    /**
     * 添加一批实验数据
     * @param d
     */
    public void Add(double[] d)
    {
        for(int i=0;i<d.length;i++)
            Add(d[i]);
    }
    
    /**
     * 获取第几个实验数据
     * @param i
     * @return
     */
    public double Data(int i)
    {
        return data[i];
    }
    
    /**
     * 获取这一批实验数据量
     * @return
     */
    public int Count()
    {
        return data.length;
    }
    
    /**
     * 获取这一批实验数据总和
     * @return
     */
    public double Sum()
    {
        double sum=0;
        for(int i=0;i<data.length;i++)
            sum+=data[i];
        return LabData.Format45(sum,DecimalBits);
        
    }
    
    /**
     * 获取这一批实验数据平均值
     * @return
     */
    public double Average()
    {
         return LabData.Format45(Sum()/Count(),DecimalBits);
    }
    
    /**
     * 离差平方和
     * @return
     */
    public double SumOfSquares()
    {
        double ave=Average();
        double sum=0;
        for(double d: data)
        {
            sum+=(d-ave)*(d-ave);
        }
        return LabData.Format452(sum,DecimalBits);       
    }
    
    /**
     * 标准方差
     * @return
     */
    public double StandardDeviation()
    {
        return LabData.Format452(Math.sqrt(SumOfSquares()/data.length),DecimalBits);
    }
    
    /**
     * 批内方差
     * @return
     */
    public double  IntraGroupVariance()
    {
        return LabData.Format452(SumOfSquares()/(data.length-1),DecimalBits);
    }
}
