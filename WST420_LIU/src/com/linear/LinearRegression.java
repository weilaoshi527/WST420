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
     * 一次性设置好所有数据
     * @param dataStr 形如4.7 7.8 10.4 13.0 15.5;4.6 7.6 10.2 13.1 15.3
     */
    public void SetLabData(String dataStr)
    {
    	super.SetLabData(dataStr);
    }
    /**
     * 预保留的小数位数
     * @param bits
     */
    public void SetDecimalBits(int bits)
    {
    	super.DecimalBits=bits;
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
    public void SetXByStr(String xStr)
    {
    	this.SetX(LabData.CreateByStr(xStr));
    }
    /**
     * 获取第几批第几个数据
     * @param batchIndex
     * @param dataIndex
     * @return
     */
    public double GetLabDataByString(int batchIndex,int dataIndex)
    {
    	return super.GetLabData(batchIndex).Data(dataIndex);
    }
    /**
     * 批次总数
     * @return
     */
    public int MeasuringTimes()
    {
        return super.MeasuringTimes();
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
     * 指定序号的Y均值
     * @param i
     * @return
     */
    public double averageY(int i)
    {
    	return this.Y().Data(i);
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
    public String Graph(double concentration,double percent,double maxX,double maxY)
    {
    	String lineString="";
    	return lineString;
    } 
    public String Line1(double x,double y)
    {
    	String line="0,"+String.valueOf(this.Origin());
    	line+=" "+linePoint(this.Slope(),this.Origin(),x,y);
    	return line;
    }
    public String Line2(double concentration,double percent,double x,double y)
    {
    	double a=this.Slope();
    	double b=this.Origin();
    	double c=concentration;
    	double p=percent;
    	double x0=(b+c)/p;
    	double y0=(b+c)*(a+p)/p;
    	String line="0,"+String.valueOf(this.Origin());
    	line+=" "+String.valueOf(x0)+","+String.valueOf(y0);
    	return line;
    }
    public String Line3(double concentration,double percent,double x,double y)
    {
    	double a=this.Slope();
    	double b=this.Origin();
    	double c=concentration;
    	double p=percent;
    	double x0=(b-c)/p;
    	double y0=(b-c)*(a-p)/p;
    	String line="0,"+String.valueOf(this.Origin());
    	line+=" "+String.valueOf(x0)+","+String.valueOf(y0);
    	return line;
    }
    String linePoint(double a,double b,double x,double y)
    {
    	String str="";
    	double x0=(y-b)/a;
    	double y0=a*x+b;
    	if(y0<y)
    	{
    		str=String.valueOf(x)+","+String.valueOf(y0);
    	}
    	else
    	{
    		str=String.valueOf(x0)+","+String.valueOf(y);
    	}
    	return str;
    }
}
