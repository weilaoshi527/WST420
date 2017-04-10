package com.linear;

import com.base.BaseVerification;
import com.base.LabData;
import com.base.Print4;

/**
 * 线性验证
 * @author sungiant
 *
 */
public class LinearRegression extends BaseVerification
{	
	LabData X;
	Double slope;
	Double relation;
	Double origin;
    public LinearRegression()
    {
        super();
        slope=new Double(Double.NaN);
        relation=slope;
        origin=slope;
    }
    /**
     * 设置X数据
     * @param x
     */
    public void SetX(LabData x)
    {
    	X=x;
    	x.DecimalBits=this.DecimalBits;
    }
    /**
     * 线性数据X
     * @return
     */
    public LabData X()
    {        
        return X;
    }
    
    /**
     * 线性数据Y
     * @return
     */
    public LabData Y()
    {
        LabData y=new LabData();
        for(int i=0;i<MeasuringTimes();i++)
        {
            double average=0;
            for(int j=0;j<BatchCount();j++)
                    average +=batches.get(j).Data(i);
            average=average/BatchCount();
            y.Add(average);
        }
        y.DecimalBits=this.DecimalBits;
        return y;
    }
    /**
     * Y的理论值
     * @return
     */
    public LabData TheoreticalY()
    {
    	LabData y=new LabData();
    	LabData x=this.X();
        for(int i=0;i<MeasuringTimes();i++)
        {
            double d=this.Slope()*x.Data(i)+this.Origin();
            y.Add(format(d));
        }
        y.DecimalBits=this.DecimalBits;
        return y;
    }
    /**
     * 指定序号的Y理论值
     * @param i
     * @return
     */
    public double TheoreticalY(int i)
    {
    	return this.TheoreticalY().Data(i);
    }
    /**
     * Y理论值数组
     * @return
     */
    public double[] TheoreticalYs()
    {
    	double[] ds=new double[this.MeasuringTimes()];
    	for(int i=0;i<ds.length;i++)
    		ds[i]=this.TheoreticalY(i);
    	return ds;
    }
    /**
     * 相关系数，与原文略有不同，为什么？
     * @return
     */
    public double Relation()
    {
    	if(relation.isNaN())
    	{
	        LabData x=X();
	        LabData y=Y();
	
	        double sum1=0;				
	        double x_ave=x.Average();
	        double y_ave=y.Average();
	        int m=MeasuringTimes(); 
	        for(int i=0;i<m;i++)
	        {
	            sum1+=(x.Data(i)-x_ave)*(y.Data(i)-y_ave);					
	        }
	        relation=format(sum1/(x.StandardDeviation()*y.StandardDeviation())/m);
    	}
    	return relation.doubleValue();
        //return (sum1/(x.StandardDeviation()*y.StandardDeviation())/m);	
    }
    /**
     * 相关系数
     * @return
     */
    public double Coeff()
    {
    	return this.Relation();
    }
    public String Relation2()
    {
    	return Print4.Print(Relation());
    }
    /**
     * 斜率
     * @return
     */
    public double Slope()
    {
    	if(slope.isNaN())
    	{
	        double sum1=0;
	        double sum2=0;				
	        LabData x=X();
	        LabData y=Y();
	        double x_ave=x.Average();
	        double y_ave=y.Average();
	        int m=MeasuringTimes();
	        for(int i=0;i<m;i++)
	        {
	            sum1+=(x.Data(i)-x_ave)*(y.Data(i)-y_ave);
	            sum2+=(x.Data(i)-x_ave)*(x.Data(i)-x_ave);					
	        }
	        slope=format(sum1/sum2);
    	}
        return slope.doubleValue();
    }
    public String Slope2()
    {
    	return Print4.Print(Slope());
    }
    /**
     * 截距
     * @return
     */
    public double Origin()
    {
    	if(origin.isNaN())
    	{
	        LabData x=X();
	        LabData y=Y();
	        origin= format(y.Average()-Slope()*x.Average());
    	}
    	return origin.doubleValue();        
    }
    public String Origin2()
    {
    	return Print4.Print(Origin());
    }    
}
