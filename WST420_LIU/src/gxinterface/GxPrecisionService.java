package gxinterface;

import com.base.LabData;
import com.precision.PrecisionVerification;

/**
 * 精密度
 * @author sungiant
 *
 */
public class GxPrecisionService {
	
	/**
	 * 精密度验证
	 */
	public PrecisionVerification precision = null;
	
	/**
	 * 显示的小数位数
	 */
	public String showDecimal = "0";
	
	/**
	 * 创建精密度对象
	 */
	public void CreatePrecisionObj(String betaStr,String decimalStr)
	{
		precision = new PrecisionVerification(Double.valueOf(betaStr));
		precision.DecimalBits=Integer.valueOf(decimalStr);
		showDecimal = decimalStr;
	}
	
	/**
	 * 加载实验数据
	 * @param labDataStr
	 */
	public void LoadLabData(String labDataStr)
	{
		precision.Add(new LabData(labDataStr));
	}
	
	/**
	 * 获取第几批数据结果之和
	 * @param index
	 */
	public String GetLabDataSum(int index,int decimalStr)
	{
		String labDataSum = String.format("%1$."+decimalStr+"f",precision.GetLabData(index).Sum());
		
		return labDataSum;
	}

	/**
	 * 获取第几批数据结果均值
	 * 
	 * @param index
	 */
	public String GetLabDataAverage(int index,int decimalStr)
	{
		String labDataAverage = String.format("%1$."+decimalStr+"f",precision.GetLabData(index).Average());
		
		return labDataAverage;
	}
	
	/**
	 * 获取每批测量结果与每批结果均值之差
	 * 
	 * @param index
	 */
	public String GetLabDataEveryResultAverageDiff(int batchIndex,int dataIndex,int decimal)
	{
		double diff = precision.GetLabData(batchIndex).Data(dataIndex) - precision.GetLabData(batchIndex).Average();
		String labDataAverage = String.format("%1$."+decimal+"f",diff);
		
		return labDataAverage;
	}
	
	/**
	 * 获取每批测量结果与每批结果均值之差的平方和
	 * 
	 * @param index
	 */
	public String GetLabDataBatchAverageDiffPow2Sum(int batchIndex,int decimal)
	{
		double sqrtSum=0;
		
		int batchCount = precision.GetLabData(batchIndex).Count();
		
		for(int i = 0 ; i< batchCount;i++)
		{
			sqrtSum+=Double.valueOf(GetMatchPow2(GetLabDataEveryResultAverageDiff(batchIndex,i,decimal),decimal));
		}
		
		String labDataAverageDiffSqrtSum = String.format("%1$."+decimal+"f",sqrtSum);
		
		return labDataAverageDiffSqrtSum;
	}
	
	/**
	 * 获取每批测量结果的批内方差
	 * 
	 * @param index
	 */
	public String GetLabDataIntraGroupVariance(int batchIndex,int decimal)
	{
		double batchIntraGroupVariance = precision.GetLabData(batchIndex).IntraGroupVariance();
		
		String intraGroupVariance = String.format("%1$."+decimal+"f",batchIntraGroupVariance);
		
		return intraGroupVariance;
	}
	
	
	/**
	 * 获取每批测量结果的均值与总均值之差
	 * 
	 * @param index
	 */
	public String GetLabDataAverageGroupAverageDiff(int batchIndex,int decimal)
	{
		String batchAverage = String.format("%1$."+decimal+"f",precision.GetLabData(batchIndex).Average());
		String groupAverage = String.format("%1$."+decimal+"f",precision.GroupAverage());
		double diff = Double.valueOf(batchAverage)-Double.valueOf(groupAverage);
		
		String averageDiff = String.format("%1$."+decimal+"f",diff);
		
		return averageDiff;
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
	 * 总均值
	 * @param decimalStr
	 * @return
	 */
	public String GroupAverage(int decimalStr)
	{
		double average = precision.GroupAverage();
		String averageDecimal = String.format("%1$."+decimalStr+"f",average);
		
		return averageDecimal;
	}
	
	/**
	 * 重复标准差
	 * 
	 * @return
	 */
	public String RepeatedStandardDeviation()
	{
		String repeatedStandardDeviation = String.format("%1$."+showDecimal+"f",precision.RepeatedStandardDeviation());
		
		return repeatedStandardDeviation;
	}
	
	/**
	 * 批间标准差
	 * 
	 * @return
	 */
	public String BetweenGroupVariance(int decimal)
	{
		String betweenGroupVariance = String.format("%1$."+decimal+"f",precision.BetweenGroupVariance());
		
		return betweenGroupVariance;
	}
	
	/**
	 * 期间标准差
	 * 
	 * @return
	 */
	public String GroupStandardDeviation()
	{
		String groupStandardDeviation = String.format("%1$."+showDecimal+"f",precision.GroupStandardDeviation());
		
		return groupStandardDeviation;
	}
	
	/**
	 * 自由度
	 */
	public String Freedom()
	{
		String freedom = String.format("%1$."+showDecimal+"f",precision.Freedom());
		
		return freedom;
	}
	
	/**
	 * 验证值
	 * @return
	 */
	public String PrecisionValue()
	{
		String groupStandardDeviation = String.format("%1$."+showDecimal+"f",precision.PrecisionValue());
		
		return groupStandardDeviation;
	}
}
