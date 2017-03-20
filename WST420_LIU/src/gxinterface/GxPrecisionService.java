package gxinterface;

import com.base.LabData;
import com.precision.PrecisionVerification;

/**
 * ���ܶ�
 * @author sungiant
 *
 */
public class GxPrecisionService {
	
	/**
	 * ���ܶ���֤
	 */
	public PrecisionVerification precision = null;
	
	/**
	 * ��ʾ��С��λ��
	 */
	public String showDecimal = "0";
	
	/**
	 * �������ܶȶ���
	 */
	public void CreatePrecisionObj(String betaStr,String decimalStr)
	{
		precision = new PrecisionVerification(Double.valueOf(betaStr));
		precision.DecimalBits=Integer.valueOf(decimalStr);
		showDecimal = decimalStr;
	}
	
	/**
	 * ����ʵ������
	 * @param labDataStr
	 */
	public void LoadLabData(String labDataStr)
	{
		precision.Add(new LabData(labDataStr));
	}
	
	/**
	 * ��ȡ�ڼ������ݽ��֮��
	 * @param index
	 */
	public String GetLabDataSum(int index,int decimalStr)
	{
		String labDataSum = String.format("%1$."+decimalStr+"f",precision.GetLabData(index).Sum());
		
		return labDataSum;
	}

	/**
	 * ��ȡ�ڼ������ݽ����ֵ
	 * 
	 * @param index
	 */
	public String GetLabDataAverage(int index,int decimalStr)
	{
		String labDataAverage = String.format("%1$."+decimalStr+"f",precision.GetLabData(index).Average());
		
		return labDataAverage;
	}
	
	/**
	 * ��ȡÿ�����������ÿ�������ֵ֮��
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
	 * ��ȡÿ�����������ÿ�������ֵ֮���ƽ����
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
	 * ��ȡÿ��������������ڷ���
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
	 * ��ȡÿ����������ľ�ֵ���ܾ�ֵ֮��
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
	 * ��ȡƽ��
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
	 * �ܾ�ֵ
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
	 * �ظ���׼��
	 * 
	 * @return
	 */
	public String RepeatedStandardDeviation()
	{
		String repeatedStandardDeviation = String.format("%1$."+showDecimal+"f",precision.RepeatedStandardDeviation());
		
		return repeatedStandardDeviation;
	}
	
	/**
	 * �����׼��
	 * 
	 * @return
	 */
	public String BetweenGroupVariance(int decimal)
	{
		String betweenGroupVariance = String.format("%1$."+decimal+"f",precision.BetweenGroupVariance());
		
		return betweenGroupVariance;
	}
	
	/**
	 * �ڼ��׼��
	 * 
	 * @return
	 */
	public String GroupStandardDeviation()
	{
		String groupStandardDeviation = String.format("%1$."+showDecimal+"f",precision.GroupStandardDeviation());
		
		return groupStandardDeviation;
	}
	
	/**
	 * ���ɶ�
	 */
	public String Freedom()
	{
		String freedom = String.format("%1$."+showDecimal+"f",precision.Freedom());
		
		return freedom;
	}
	
	/**
	 * ��ֵ֤
	 * @return
	 */
	public String PrecisionValue()
	{
		String groupStandardDeviation = String.format("%1$."+showDecimal+"f",precision.PrecisionValue());
		
		return groupStandardDeviation;
	}
}
