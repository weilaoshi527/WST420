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
	 * ����ʵ������
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
	 * ��ȡ���β��������x��ϡ�ͶȽ���ľ�ֵ
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
	 * ��ȡƽ��
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
	 * б��
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
	 * �ؾ�
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
	 * ���ϵ��
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
