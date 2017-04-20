package Test;

import com.precision.*;
import com.base.*;
import com.trueness.*;
import com.linear.*;


/**
 * 测试
 * @author sungiant
 *
 */
public class TestProgramMain 
{
	static void PrintBase(Precision pv)
	{
		String tempStr = "";
		//实际GeneXus调用：数据之和
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.PrintNoZero(pv.GetLabDataSum(i),0);
        }
		System.out.println("数据之和:\t\t"+tempStr);
		
		//实际GeneXus调用：数据均值
		tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.PrintNoZero(pv.GetLabDataAverage(i),3);
        }
        System.out.println("数据均值:\t\t"+tempStr);
        
        //实际GeneXus调用：数标准差
        tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.PrintNoZero(pv.GetLabDataStandardDeviation(i),4);
        }
        System.out.println("数标准差:\t\t"+tempStr);
        
        //实际GeneXus调用：批内方差
        tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.Print(pv.GetLabDataIntraGroupVariance(i));
        }
        System.out.println("批内方差:\t\t"+tempStr);
        
        //实际GeneXus调用：变异系数
        tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.PrintPercentage(pv.GetLabDataCV(i),1);
        }
        System.out.println("变异系数:\t\t" + tempStr); 
        
        //实际GeneXus调用：离差平方和
        tempStr = "";
		for(int i=0;i<pv.BatchCount();i++)
        {
			tempStr += " " + Print4.Print(pv.GetLabDataSumOfSquare(i));
        }
        System.out.println("离差平方和:\t\t"+tempStr);
        
        //实际GeneXus调用：第X批与均值之差
        tempStr = "";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	tempStr = "";
        	for(int j=0;j<pv.MeasuringTimes();j++)
            {
        		tempStr += Print4.PrintNoZero(pv.GetLabDataSubtractMean(i,j),2)+"\t";
            }
        	System.out.println("第"+String.valueOf(i)+"批与均值之差:\t\t"+tempStr);
        }
        
        //实际GeneXus调用：第X批与均值之差平方
        tempStr = "";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	tempStr = "";
        	for(int j=0;j<pv.MeasuringTimes();j++)
            {
        		tempStr += Print4.PrintNoZero(pv.GetLabDataSubtractMeanSquare(i,j),4)+"\t";
            }
        	System.out.println("第"+String.valueOf(i)+"批与均值之差平方:\t\t"+tempStr);
        }
        //实际GeneXus调用：与总均值之差
        tempStr = "";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	tempStr+=" "+Print4.PrintNoZero(pv.GetLabDataSubtractOverallMean(i),2);
        }
        System.out.println("与总均值之差:\t\t"+tempStr);
        //实际GeneXus调用：与总均值之差平方
        tempStr = "";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	tempStr+=" "+Print4.PrintNoZero(pv.GetLabDataSubtractOverallMeanSquare(i),4);
        }
        System.out.println("与总均值之差的平方:\t\t"+tempStr);
	}
    public static void main(String[] args)
    {			
        System.out.println("***************************精密度GeneXus接口  start ***************************");
        System.out.println("");
        
        Precision pv=new Precision();
        //pv.SetCheckedValue(12.0);	//设置厂家声称的校验值
        pv.SetDecimalBits(4);	//设置小数位数
        pv.SetFalseRejectionRate(0.05, 2);	//设置假排除率和检测水平
        
        pv.SetLabData("140 140 140;138 139 138;143 144 144;143 143 142;142 143 141"); //添加样本数据
        //pv.SetLabData("140 139 138 138 140;140 143 141 143 137;140 138 136 141 136;141 144 142 143 144;139 140 141 138 141"); //Sample A.pdf
        //pv.SetLabData("606 627 621 606 620;612 610 611 595 630;649 626 636 639 648;615 633 605 616 625;622 632 646 619 623");	//Sample B.pdf
        System.out.println("------精密度实验------");
        
        PrintBase(pv);
        
        System.out.println("批间方差:\t" + Print4.Print(pv.BetweenGroupVariance()));
        System.out.println(("批间标准差:\t" + Print4.PrintNoZero(pv.BetweenGroupSD(),4)));
        System.out.println(("总均值:\t" + Print4.PrintNoZero(pv.OverallMean(),2)));
        System.out.println(("重复自由度:\t" + Print4.PrintNoZero(pv.DegreeOfFreedom(0),2)));
        System.out.println(("重复C点:\t" + Print4.PrintNoZero(pv.C(0),2)));
        System.out.println(("重复方差:\t" + Print4.PrintNoZero(pv.Variance(0),4)));
        System.out.println(("重复标准差:\t" + Print4.PrintHaveZero(pv.StandardDeviation(0),2)));
        pv.SetCheckedValue(2.0);
        //pv.SetProbability(0.95);
        System.out.println(("重复验证值:\t" + Print4.PrintHaveZero(pv.MeasurementValue(0),2)));
        System.out.println(("期间自由度:\t" + Print4.PrintNoZero(pv.DegreeOfFreedom(1),2)));
        System.out.println(("期间方差:\t" + Print4.PrintNoZero(pv.Variance(1),4)));
        System.out.println(("期间标准差:\t" + Print4.PrintHaveZero(pv.StandardDeviation(1),2)));
        System.out.println(("期间C点:\t" + Print4.PrintNoZero(pv.C(1),2)));
        //pv.SetCheckedValue(3.5);
        System.out.println(("期间验证值:\t" + Print4.PrintHaveZero(pv.MeasurementValue(1),2)));    
        String sdiStr="";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	for(int j=0;j<pv.MeasuringTimes();j++)
            {
        		sdiStr += Print4.Print(pv.GetSDI(i,j))+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("SDI参数:\n" + sdiStr));
        
        sdiStr="";
        for(int i=0;i<pv.BatchCount();i++)
        {
        	for(int j=0;j<pv.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(pv.GetLabDataByString(i,j),0)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("数据输出:\n" + sdiStr));
        System.out.println("");
        System.out.println("***************************精密度GeneXus接口  end ***************************");
        System.out.println("");
        System.out.println("***************************正确度GeneXus接口  start ***************************");
        System.out.println("");
        
        System.out.println("**** 患者样本 ****");
        AccuracyFromPatientSample p2=new AccuracyFromPatientSample();
        p2.SetCheckedValue(2.0);
        p2.SetFalseRejectionRate(0.01);
        p2.SetDecimalBits(2);
        p2.SetTea(20, 0.1);		//注意，Tea参数缺失时，EI指数会出错
       
        //p2.SetRi(LabData.CreateByStr("76 127 256 303 29 345 42 154 398 93 240 72 312 99 375 168 59 183 213 436"));
        //p2.SetRc(LabData.CreateByStr("77 121 262 294 25 348 41 154 388 92 239 69 308 101 375 162 54 185 204 431"));
        p2.SetRiByStr("76 127 256 303 29 345 42 154 398 93 240 72 312 99 375 168 59 183 213 436");
        p2.SetRcByStr("77 121 262 294 25 348 41 154 388 92 239 69 308 101 375 162 54 185 204 431");
        //实际GeneXus调用：第X批第X次与总均值之差
        String tempArr = "";
        System.out.println("绝对偏移:\t"+Print4.Print(p2.AbsoluteOffset()));
        //绝对偏移数组
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+  Print4.PrintNoZero(p2.AbsoluteOffsetArray(i),0);
        }
        System.out.println("绝对偏移数组:\t"+tempArr);
        //绝对偏移数组与绝对偏移之差
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+  Print4.PrintNoZero(p2.RelativeOffsetArraySubtract(i),2);
        }
        System.out.println("绝对偏移数组与绝对偏移之差:\t"+tempArr);
        //绝对偏移数组与绝对偏移之差平方
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+  Print4.PrintNoZero(p2.RelativeOffsetArraySubtractSquare(i),2);
        }
        System.out.println("绝对偏移数组与绝对偏移之差平方:\t"+tempArr);
        System.out.println("绝对偏移标准差:\t"+Print4.PrintNoZero(p2.StandardDeviation(),2));
        System.out.println("绝对偏移区间:\t["+Print4.Print(p2.MinOfInterval())+"-->"+Print4.Print(p2.MaxOfInterval())+"]");
        System.out.println("相对偏移:\t"+Print4.Print(p2.RelativeOffset()));
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+ Print4.PrintNoZero(p2.RelativeOffsetArray(i),0);
        }
        System.out.println("相对偏移数组:\t"+tempArr);
        System.out.println("相对偏移标准差:\t"+Print4.PrintNoZero(p2.RelativeStandardDeviation(),2));
        System.out.println("相对偏移区间:\t["+Print4.Print(p2.RelativeMinOfInterval())+"-->"+Print4.Print(p2.RelativeMaxOfInterval())+"]");
        p2.SetTea(20, 0.1);
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+ Print4.PrintNoZero(p2.ErrorIndex(i),2);
        }
        System.out.println("Error Indexes:\t"+tempArr);
        LinearRegression line=p2.GetLine();
        System.out.println("方程:\ty="+Print4.PrintNoZero(line.Slope(),4)+"x+"+Print4.PrintNoZero(line.Origin(),4));
        System.out.println("相关系数:\t"+Print4.PrintNoZero(line.Relation()*line.Relation(),5));
        System.out.println("错误指数均值:\t"+Print4.Print(p2.AverageErrorIndex()));
        System.out.println("错误指数范围:\t"+Print4.Print(p2.MinEI())+" to "+Print4.Print(p2.MaxEI()));
        
        Graph g=new Graph();
        g.SetAllParameters(20, 0.25, 1.085, -1.527, 500, 500); //注意：百分比为0时，不画第4和第5条直线
        //g.SetAllParameters(20, 0.25, 1.085, -1.527, 500, 500);
       
        /* 画图配置参数
            g.SetConcentration(20);	 	//浓度
	        g.SetMaxX(500);				//X象限范围
	        g.SetMaxY(500);				//Y象限范围
	        g.SetOrigin(-1.527);		//截距
	        g.SetPercent(0.25);			//百分比	百分比为0时，不画第4和第5条直线
	        g.SetSlope(1.085);			//斜率
         */
        System.out.println("第1条直线参数:\t"+g.GetLine1());	//中间的直线
        System.out.println("第2条直线参数:\t"+g.GetLine2());	//左上方直线
        System.out.println("第3条直线参数:\t"+g.GetLine3());	//左下方直线
        System.out.println("第4条直线参数:\t"+g.GetLine4());	//右上方直线
        System.out.println("第5条直线参数:\t"+g.GetLine5());	//右下方直线        
        System.out.println("画图参数:\t"+g.GetLines());
        
        sdiStr="";
        for(int i=0;i<p2.BatchCount();i++)
        {
        	for(int j=0;j<p2.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(p2.GetLabDataByString(i,j),0)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("数据输出:\n" + sdiStr));
        System.out.println("**** 参考物质 ****");
        
        
        AccuracyFromRefMaterial p3=new AccuracyFromRefMaterial();
        //p3.SetFalseRejectionRate(0.01);
        p3.SetFalseRejectionRate(0.005);
        //p3.SetXfromRef(40);
        p3.SetXfromRef(2.2);
        //p3.SetSprogram(1.73);
        //p3.SetLabsCount(135);
        p3.SetSa(0.008);
        
        //p3.SetLabData("37 38;39 37;38 36;39 38;38 37");
        p3.SetLabData("2.04 2.09;2.15 2.04;2.09 1.98;2.15 2.09;2.09 2.04");
        //实际GeneXus调用：第X批第X次与总均值之差
        tempArr = "";
        for(int i=0;i<p3.BatchCount();i++)
        {
        	tempArr = "";
        	for(int j=0;j<p3.MeasuringTimes();j++)
            {
        		tempArr += Print4.PrintNoZero(p3.GetLabDataSubtractOverallMean(i,j),2)+"\t";
            }
        	System.out.println("第"+String.valueOf(i)+"批第1,2次与总均值之差:\t\t"+tempArr);
        }
        
        //实际GeneXus调用：第X批与总均值之差平方
        tempArr = "";
        for(int i=0;i<p3.BatchCount();i++)
        {
        	tempArr = "";
        	for(int j=0;j<p3.MeasuringTimes();j++)
            {
        		tempArr += Print4.PrintNoZero(p3.GetLabDataSubtractOverallMeanSquare(i,j),4)+"\t";
            }
        	System.out.println("第"+String.valueOf(i)+"批第1,2次与总均值之差平方:\t\t"+tempArr);
        }
        System.out.println("测量偏移值:\t"+Print4.PrintNoZero(p3.Deviant(),2));
        System.out.println("自由度:\t"+Print4.Print(p3.Freedom()));
        System.out.println("总均值:\t"+Print4.Print(p3.OverallMean()));
        System.out.println("标准差:\t"+Print4.PrintNoZero(p3.StandardDeviation(),4));
        System.out.println("测量标准不确定度:\t"+Print4.PrintNoZero(p3.GetSa(),4));
        System.out.println("验证区间:\t["+Print4.PrintNoZero(p3.MinOfInterval(),2)+"-->"+Print4.PrintNoZero(p3.MaxOfInterval(),2)+"]");
        sdiStr="";
        for(int i=0;i<p3.BatchCount();i++)
        {
        	for(int j=0;j<p3.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(p3.GetSDI(i,j),2)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("SDI参数:\t\n" + sdiStr)); 
        
        sdiStr="";
        for(int i=0;i<p3.BatchCount();i++)
        {
        	for(int j=0;j<p3.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(p3.GetLabDataByString(i,j),0)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("数据输出:\n" + sdiStr));
        System.out.println("");
        System.out.println("***************************正确度GeneXus接口  end ***************************");
        System.out.println("");
        System.out.println("***************************线性GeneXus接口  start ***************************");
        System.out.println("");
        
        LinearRegression l=new LinearRegression();
        l.SetDecimalBits(3);        
        l.SetLabData("4.7 7.8 10.4 13.0 15.5;4.6 7.6 10.2 13.1 15.3");
        //l.SetX(LabData.CreateByStr("1 2 3 4 5"));  
        l.SetXByStr("1 2 3 4 5");
        //均值
        tempArr="";
        for(int i=0;i<l.MeasuringTimes();i++)
        {
        	tempArr+=" "+Print4.PrintNoZero(l.averageY(i),2);
        }
        System.out.println("均值(实测浓度):\t"+tempArr);
        //理论浓度
        tempArr="";
        for(int i=0;i<l.MeasuringTimes();i++)
        {
        	tempArr+=" "+l.TheoreticalY(i);
        }
        System.out.println("理论浓度:\t"+tempArr);
        //实测浓度-理论浓度
        tempArr="";
        for(int i=0;i<l.MeasuringTimes();i++)
        {
        	tempArr+=" "+Print4.PrintNoZero(l.averageY(i)-l.TheoreticalY(i),2);
        }
        System.out.println("均值(实测浓度)-理论浓度:\t"+tempArr);
        System.out.println("方程:\ty="+Print4.PrintNoZero(l.Slope(),3)+"x+"+Print4.PrintNoZero(l.Origin(),3));
        System.out.println("相关系数:\t"+Print4.PrintNoZero(l.Relation()*l.Relation(),4));
        //
        
        sdiStr="";
        for(int i=0;i<l.BatchCount();i++)
        {
        	for(int j=0;j<l.MeasuringTimes();j++)
            {
        		sdiStr += Print4.PrintNoZero(l.GetLabDataByString(i,j),0)+"\t";
            }
        	sdiStr +="\n";
        }
        System.out.println(("数据输出:\n" + sdiStr));
        p2=new AccuracyFromPatientSample();
        p2.SetCheckedValue(2.0);
        p2.SetFalseRejectionRate(0.01);
        p2.SetDecimalBits(2);
        p2.SetTea(5, 0);
        //p2.SetRi(l.GetLabData(0));
        //p2.SetRc(l.GetLabData(1));
        p2.SetRiByStr("4.7 7.8 10.4 13.0 15.5");
        p2.SetRcByStr("4.6 7.6 10.2 13.1 15.3");
        tempArr = "";
        for(int i =0; i < p2.MeasuringTimes(); i++)
        {
        	tempArr +=" "+ Print4.PrintNoZero(p2.ErrorIndex(i),2);
        }
        System.out.println("线性实验的EI指数:\t"+tempArr);
        
        System.out.println("");
        System.out.println("***************************线性GeneXus接口  end ***************************");
    }
}
