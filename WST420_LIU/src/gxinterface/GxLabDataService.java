package gxinterface;

import com.base.LabData;

/**
 * 实验数据
 * @author sungiant
 *
 */
public class GxLabDataService {

	/**
	 * 获取实验数据之和
	 * 
	 * @param dataStr
	 * @return
	 */
	public String LabDataSum(String dataStr)
	{
		LabData ld = new LabData(dataStr);
		
		return String.valueOf(ld.Sum());
	}
	
	/**
	 * 获取实验数据均值
	 * 
	 * @param dataStr
	 * @return
	 */
	public String LabDataAverage(String dataStr)
	{
		LabData ld = new LabData(dataStr);
		
		return String.valueOf(ld.Average());
	}
	
	/**
	 * 获取实验数据标准方差
	 * 
	 * @param dataStr
	 * @return
	 */
	public String LabDataStandardDeviation(String dataStr)
	{
		LabData ld = new LabData(dataStr);
		
		return String.valueOf(ld.StandardDeviation());
	}
}
