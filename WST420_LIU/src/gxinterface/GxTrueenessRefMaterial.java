package gxinterface;

import com.base.LabData;
import com.trueness.AccuracyFromRefMaterial;

/**
 * 正确度：参考物质
 * @author sungiant
 *
 */
public class GxTrueenessRefMaterial {

	private AccuracyFromRefMaterial accuracyFromRefMaterial = null;
	
	/**
	 * 显示的小数位数
	 */
	public String showDecimal = "0";
	
	/**
	 * 
	 * @param xfromRef 参考物质赋值 单位mg/dL,默认值40
	 * @param sprogram 室间质评结果标准差，默认值1.73
	 * @param labsCount 参加室间质评的实验室数量，默认值135
	 * @param falseRejectionRate 假排除率，默认值0.01
	 */
	public void CreateTrueenessRefMaterial(String xfromRef,String sprogram,String labsCount,String falseRejectionRate)
	{
		accuracyFromRefMaterial = new AccuracyFromRefMaterial();
		
		accuracyFromRefMaterial.XfromRef = Double.valueOf(xfromRef);
		accuracyFromRefMaterial.Sprogram = Double.valueOf(sprogram);
		accuracyFromRefMaterial.LabsCount = Integer.valueOf(labsCount);
		accuracyFromRefMaterial.FalseRejectionRate = Double.valueOf(falseRejectionRate);
	}
	
	/**
	 * 加载实验数据
	 * @param labDataStr
	 */
	public void LoadLabData(String labDataStr)
	{
		accuracyFromRefMaterial.Add(new LabData(labDataStr));
	}
	
	/**
	 * 总均值
	 * @param decimal
	 * @return
	 */
	public String GroupAverage(int decimal)
	{
		String groupAverage = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.GroupAverage());
		
		return groupAverage;
	}
	
	/**
	 * 获取每批测量结果与总均值之差
	 * @param batchIndex
	 * @param dataIndex
	 * @param decimal
	 * @return
	 */
	public String GetLabDataEveryResultGroupAverageDiff(int batchIndex,int dataIndex,int decimal)
	{
		double diff = accuracyFromRefMaterial.GetLabData(batchIndex).Data(dataIndex) - Double.valueOf(GroupAverage(decimal));
		String labDataAverageDiff = String.format("%1$."+decimal+"f",diff);
		
		return labDataAverageDiff;
	}
	
	/**
	 * 获取平方
	 * 
	 * @param a
	 * @param b
	 * @param decimalStr
	 * @return
	 */
	public String GetMatchPow2(String a,int decimalStr)
	{
		double ab = Math.pow(Double.valueOf(a), 2);
		String powDecimal = String.format("%1$."+decimalStr+"f",ab);
		
		return powDecimal;
	}
	
	/**
	 * 参考物质测量偏移值
	 * @param decimal
	 * @return
	 */
	public String Deviant(int decimal)
	{
		String deviant = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.Deviant());
		
		return deviant;
	}
	
	/**
	 * 标准差
	 * @param decimal
	 * @return
	 */
	public String StandardDeviation(int decimal)
	{
		String standardDeviation = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.StandardDeviation());
		
		return standardDeviation;
	}
	
	/**
	 * 参考物质测量标准不确定度
	 * @param decimal
	 * @return
	 */
	public String Uncertainty(int decimal)
	{
		String standardDeviation = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.Uncertainty());
		
		return standardDeviation;
	}
	
	/**
	 * 验证区间的下限
	 * @param decimal
	 * @return
	 */
	public String MinOfInterval(int decimal)
	{
		String minOfInterval = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.MinOfInterval());
		
		return minOfInterval;
	}
	
	/**
	 * 验证区间的上限
	 * @param decimal
	 * @return
	 */
	public String MaxOfInterval(int decimal)
	{
		String maxOfInterval = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.MaxOfInterval());
		
		return maxOfInterval;
	}
}
