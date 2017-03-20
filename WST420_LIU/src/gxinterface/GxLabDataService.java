package gxinterface;

import com.base.LabData;

/**
 * ʵ������
 * @author sungiant
 *
 */
public class GxLabDataService {

	/**
	 * ��ȡʵ������֮��
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
	 * ��ȡʵ�����ݾ�ֵ
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
	 * ��ȡʵ�����ݱ�׼����
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
