package gxinterface;

import com.base.LabData;
import com.trueness.AccuracyFromPatientSample;

/**
 * ��ȷ��
 * @author sungiant
 *
 */
public class GxTrueenessPatientSample {

	private AccuracyFromPatientSample accuracyFromPatientSample = null;

	
	/**
	 * ��ʾ��С��λ��
	 */
	public String showDecimal = "0";
	
	/**
	 * ��ȷ�ȣ���������
	 * @param betaStr
	 * @param decimalStr
	 */
	public void CreateTrueenessPatientSample(String betaStr,String decimalStr)
	{
		accuracyFromPatientSample = new AccuracyFromPatientSample(Double.valueOf(betaStr));
	}
	
	/**
	 * ����ʵ������
	 * @param labDataStr
	 */
	public void LoadLabData(String labDataStr)
	{
		accuracyFromPatientSample.Add(new LabData(labDataStr));
	}
	
	/**
	 * ��ȡÿ�������������������������ľ���ƫ��
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
	 * ��ȡÿ�������������������ľ���ƫ�Ʋ�
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
	 * ����ƫ��
	 * @param decimal
	 * @return
	 */
	public String AbsoluteOffset(int decimal)
	{
		String absoluteOffset = String.format("%1$."+decimal+"f",accuracyFromPatientSample.AbsoluteOffset());
		
		return absoluteOffset;
	}
	
	/**
	 * ����ƫ�Ƶı�׼��
	 * @param decimal
	 * @return
	 */
	public String StandardDeviation(int decimal)
	{
		String standardDeviation = String.format("%1$."+decimal+"f",accuracyFromPatientSample.StandardDeviation());
		
		return standardDeviation;
	}
	
	/**
	 * ��֤���������
	 * @param decimal
	 * @return
	 */
	public String MinOfInterval(int decimal)
	{
		String minOfInterval = String.format("%1$."+decimal+"f",accuracyFromPatientSample.MinOfInterval());
		
		return minOfInterval;
	}
	
	/**
	 * ��֤���������
	 * @param decimal
	 * @return
	 */
	public String MaxOfInterval(int decimal)
	{
		String maxOfInterval = String.format("%1$."+decimal+"f",accuracyFromPatientSample.MaxOfInterval());
		
		return maxOfInterval;
	}
}
