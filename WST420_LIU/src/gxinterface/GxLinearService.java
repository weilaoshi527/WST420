package gxinterface;

import com.base.LabData;
import com.linear.LinearRegression;

public class GxLinearService {

	
	public LinearRegression linearRegression = null;
	
	public void CreateLinearRegression()
	{
		linearRegression = new LinearRegression();
		linearRegression.DecimalBits=-1;
	}
	
	/**
	 * 加载实验数据
	 * @param labDataStr
	 */
	public void LoadLabData(String labDataStr)
	{
		linearRegression.Add(new LabData(labDataStr));
	}
	public void LoadX(String labDataStr)
	{
		linearRegression.SetX(new LabData(labDataStr));
	}
	
	/**
	 * 获取两次测量结果第x个稀释度结果的均值
	 * 
	 * @param dataIndex
	 * @param decimal
	 * @return
	 */
	public String GetLabDataTwoResultAverage(int dataIndex,int decimal)
	{
		String averageStr = String.format("%1$."+decimal+"f",linearRegression.Y().Data(dataIndex));
		
		return averageStr;
	}
	
	/**
	 * 获取平方
	 * 
	 * @param a
	 * @param b
	 * @param decimalStr
	 * @return
	 */
	public String GetMatchPow2(String a,int decimal)
	{
		double ab = Math.pow(Double.valueOf(a), 2);
		String powDecimal = String.format("%1$."+decimal+"f",ab);
		
		return powDecimal;
	}
	
	/**
	 * 斜率
	 * 
	 * @param decimal
	 * @return
	 */
	public String Slope(int decimal)
	{
		String slope = String.format("%1$."+decimal+"f",linearRegression.Slope());
		
		return slope;
	}
	
	/**
	 * 截距
	 * 
	 * @param decimal
	 * @return
	 */
	public String Origin(int decimal)
	{
		String origin = String.format("%1$."+decimal+"f",linearRegression.Origin());
		
		return origin;
	}
	
	/**
	 * 相关系数
	 * 
	 * @param decimal
	 * @return
	 */
	public String Relation(int decimal)
	{
		String relation = String.format("%1$."+decimal+"f",linearRegression.Relation());
		
		return relation;
	}
}
