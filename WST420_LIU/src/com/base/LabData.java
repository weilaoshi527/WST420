package com.base;

import java.math.BigDecimal;

/**
 * ������ʵ������
 * @author sungiant
 *
 */
public class LabData {
	
    private double[] data;
    //public static int decimalBits;	//С��λ�� 
    //public  BaseVerification parent;
    public int DecimalBits;
    /**
     * ���������ʽ��
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
     * ��һ����ʽ��
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
     * ���캯��
     * @param dataStr���ÿո�����ĸ���ʵ�������ַ���
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
     * ���һ��ʵ������
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
     * ���һ��ʵ������
     * @param d
     */
    public void Add(double[] d)
    {
        for(int i=0;i<d.length;i++)
            Add(d[i]);
    }
    
    /**
     * ��ȡ�ڼ���ʵ������
     * @param i
     * @return
     */
    public double Data(int i)
    {
        return data[i];
    }
    
    /**
     * ��ȡ��һ��ʵ��������
     * @return
     */
    public int Count()
    {
        return data.length;
    }
    
    /**
     * ��ȡ��һ��ʵ�������ܺ�
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
     * ��ȡ��һ��ʵ������ƽ��ֵ
     * @return
     */
    public double Average()
    {
         return LabData.Format45(Sum()/Count(),DecimalBits);
    }
    
    /**
     * ���ƽ����
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
     * ��׼����
     * @return
     */
    public double StandardDeviation()
    {
        return LabData.Format452(Math.sqrt(SumOfSquares()/data.length),DecimalBits);
    }
    
    /**
     * ���ڷ���
     * @return
     */
    public double  IntraGroupVariance()
    {
        return LabData.Format452(SumOfSquares()/(data.length-1),DecimalBits);
    }
}
