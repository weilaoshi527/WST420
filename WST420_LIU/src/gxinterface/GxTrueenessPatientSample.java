package gxinterface;

import com.base.LabData;
import com.trueness.AccuracyFromPatientSample;

/**
 * 正确度
 * @author sungiant
 *
 */
public class GxTrueenessPatientSample {

	private AccuracyFromPatientSample accuracyFromPatientSample = null;

	
	/**
	 * 显示的小数位数
	 */
	public String showDecimal = "0";
	
	/**
	 * 正确度：患者样本
	 * @param betaStr
	 * @param decimalStr
	 */
	public void CreateTrueenessPatientSample(String betaStr,String decimalStr)
	{
		accuracyFromPatientSample = new AccuracyFromPatientSample(Double.valueOf(betaStr));
	}
	
	/**
	 * 加载实验数据
	 * @param labDataStr
	 */
	public void LoadLabData(String labDataStr)
	{
		accuracyFromPatientSample.Add(new LabData(labDataStr));
	}
	
	/**
	 * 获取每个样本测量结果在两个方法间的绝对偏移
	 * @param decimal
	 * @return
	 */
	public String GetLabDataBi(int dataIndex,int decimal)
	{
		double bi = accuracyFromPatientSample.Ri().Data(dataIndex)-accuracyFromPatientSample.Rc().Data(dataIndex);
		String biStr = String.format("%1$."+decimal+"f",bi);
		
		return biStr;
	}
	
	/**
	 * 获取每个样本结果两个方法间的绝对偏移差
	 * @param decimal
	 * @return
	 */
	public String GetLabDataAbsoluteOffsetDiff(int dataIndex,int decimal)
	{
		double absoluteOffsetDiff = Double.valueOf(GetLabDataBi(dataIndex,decimal))-accuracyFromPatientSample.AbsoluteOffset();
		String absoluteOffsetDiffStr = String.format("%1$."+decimal+"f",absoluteOffsetDiff);
		
		return absoluteOffsetDiffStr;
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
	 * 绝对偏移
	 * @param decimal
	 * @return
	 */
	public String AbsoluteOffset(int decimal)
	{
		String absoluteOffset = String.format("%1$."+decimal+"f",accuracyFromPatientSample.AbsoluteOffset());
		
		return absoluteOffset;
	}
	
	/**
	 * 绝对偏移的标准差
	 * @param decimal
	 * @return
	 */
	public String StandardDeviation(int decimal)
	{
		String standardDeviation = String.format("%1$."+decimal+"f",accuracyFromPatientSample.StandardDeviation());
		
		return standardDeviation;
	}
	
	/**
	 * 验证区间的下限
	 * @param decimal
	 * @return
	 */
	public String MinOfInterval(int decimal)
	{
		String minOfInterval = String.format("%1$."+decimal+"f",accuracyFromPatientSample.MinOfInterval());
		
		return minOfInterval;
	}
	
	/**
	 * 验证区间的上限
	 * @param decimal
	 * @return
	 */
	public String MaxOfInterval(int decimal)
	{
		String maxOfInterval = String.format("%1$."+decimal+"f",accuracyFromPatientSample.MaxOfInterval());
		
		return maxOfInterval;
	}
}
