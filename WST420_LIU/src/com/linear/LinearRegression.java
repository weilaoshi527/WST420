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
    public LinearRegression()
    {
        super();
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
     * 相关系数，与原文略有不同，为什么？
     * @return
     */
    public double Relation()
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
        return format(sum1/(x.StandardDeviation()*y.StandardDeviation())/m);	
        //return (sum1/(x.StandardDeviation()*y.StandardDeviation())/m);	
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
        return format(sum1/sum2);
        //return (sum1/sum2);
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
        LabData x=X();
        LabData y=Y();
        return format(y.Average()-Slope()*x.Average());
        //return y.Average()-Slope()*x.Average();
    }
    public String Origin2()
    {
    	return Print4.Print(Origin());
    }
}
