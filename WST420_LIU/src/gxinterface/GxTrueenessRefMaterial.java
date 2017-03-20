package gxinterface;

import com.base.LabData;
import com.trueness.AccuracyFromRefMaterial;

/**
 * ��ȷ�ȣ��ο�����
 * @author sungiant
 *
 */
public class GxTrueenessRefMaterial {

	private AccuracyFromRefMaterial accuracyFromRefMaterial = null;
	
	/**
	 * ��ʾ��С��λ��
	 */
	public String showDecimal = "0";
	
	/**
	 * 
	 * @param xfromRef �ο����ʸ�ֵ ��λmg/dL,Ĭ��ֵ40
	 * @param sprogram �Ҽ����������׼�Ĭ��ֵ1.73
	 * @param labsCount �μ��Ҽ�������ʵ����������Ĭ��ֵ135
	 * @param falseRejectionRate ���ų��ʣ�Ĭ��ֵ0.01
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
	 * ����ʵ������
	 * @param labDataStr
	 */
	public void LoadLabData(String labDataStr)
	{
		accuracyFromRefMaterial.Add(new LabData(labDataStr));
	}
	
	/**
	 * �ܾ�ֵ
	 * @param decimal
	 * @return
	 */
	public String GroupAverage(int decimal)
	{
		String groupAverage = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.GroupAverage());
		
		return groupAverage;
	}
	
	/**
	 * ��ȡÿ������������ܾ�ֵ֮��
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
	 * �ο����ʲ���ƫ��ֵ
	 * @param decimal
	 * @return
	 */
	public String Deviant(int decimal)
	{
		String deviant = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.Deviant());
		
		return deviant;
	}
	
	/**
	 * ��׼��
	 * @param decimal
	 * @return
	 */
	public String StandardDeviation(int decimal)
	{
		String standardDeviation = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.StandardDeviation());
		
		return standardDeviation;
	}
	
	/**
	 * �ο����ʲ�����׼��ȷ����
	 * @param decimal
	 * @return
	 */
	public String Uncertainty(int decimal)
	{
		String standardDeviation = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.Uncertainty());
		
		return standardDeviation;
	}
	
	/**
	 * ��֤���������
	 * @param decimal
	 * @return
	 */
	public String MinOfInterval(int decimal)
	{
		String minOfInterval = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.MinOfInterval());
		
		return minOfInterval;
	}
	
	/**
	 * ��֤���������
	 * @param decimal
	 * @return
	 */
	public String MaxOfInterval(int decimal)
	{
		String maxOfInterval = String.format("%1$."+decimal+"f",accuracyFromRefMaterial.MaxOfInterval());
		
		return maxOfInterval;
	}
}
