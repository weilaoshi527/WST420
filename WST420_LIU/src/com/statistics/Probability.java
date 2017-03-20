package com.statistics;

/**
 * 统计：
 * @author sungiant
 *
 */
public class Probability {
	   
	/**
	 * 
	 * @param n
	 * @return
	 */
    public static double gam(int n)
        {
            int i;
            double k;

            if ((n % 2) == 1) // n为奇数
            {
                k = 1.772453850905516;
                i = 1;
            }
            else
            {
                k = 1.0;
                i = 2;
            }

            while (i <= n - 2)
            {
                k *= i / 2.0;
                i += 2;
            }

            return k;
	}
    
    
	/**
	 * 正态分布函数值:  p(-∞,u)
	 * @param u
	 * @return
	 */        
    public static double norm(double u)
    {
        if (u < -5.0) return 0.0;
        if (u > 5.0) return 1.0;

        double y = Math.abs(u) / Math.sqrt(2.0);

        double p = 1.0 + y * (0.0705230784 + y * (0.0422820123 + y * (0.0092705272 +
            y * (0.0001520143 + y * (0.0002765672 + y * 0.0000430638)))));

        double er = 1 - Math.pow(p, -16.0);
        p = (u < 0.0) ? 0.5 - 0.5 * er : 0.5 + 0.5 * er;
        return p;
    }
    
    /**
     * 正态分布的反函数, p(-∞,u)=p ; 已知p, 返回u
     * @param p
     * @return
     */        
    public static double re_norm(double p)
    {
        if (p == 0.5) return 0.0;
        if (p > 0.9999997) return 5.0;
        if (p < 0.0000003) return -5.0;
        if (p < 0.5) return -re_norm(1.0 - p);

        double y = -Math.log(4.0 * p * (1.0 - p));
        y = y * (1.570796288 + y * (0.3706987906e-1
            + y * (-0.8364353589e-3 + y * (-0.2250947176e-3
            + y * (0.6841218299e-5 + y * (0.5824238515e-5
            + y * (-0.1045274970e-5 + y * (0.8360937017e-7
            + y * (-0.3231081277e-8 + y * (0.3657763036e-10
            + y * 0.6936233982e-12))))))))));

        return Math.sqrt(y);

    }
	      
    /**
     * chi2分布函数值及密度值:
     * @param n
     * @param x
     * @return
     * 区间(0,x)上的概率p
     */        
    public static TwoValues chi2(int n, double x)
    {
        double f = 0;
        double iai;
        double p, Ux;
        double pi = 3.14159265358979312;

        double y = x / 2.0;
        if ((n % 2) == 1)
        {
            Ux = Math.sqrt(y / pi) * Math.exp(-y);
            p = 2.0 * norm(Math.sqrt(x)) - 1.0;
            iai = 0.5;
        }
        else
        {
            Ux = y * Math.exp(-y);
            p = 1.0 - Math.exp(-y);
            iai = 1.0;
        }

        while (iai != 0.5 * n)
        {
            p = p - Ux / iai;
            Ux = Ux * y / iai;
            iai += 1.0;
        }
        f = Ux / x;
        return new TwoValues(p,f);

    }
    
    /**
     * chi方分布的反函数:  p=F(0,x)
     * @param n
     * 自由度n
     * @param p
     * 已知概率值p
     * @return
     * 反求x
     */        
    public static double re_chi2(int n, double p)
    {
        if (p > 0.9999999) p = 0.9999999;
        if (n == 1)
        {
            double x = re_norm((1.0 - p) / 2.0);
            return x * x;
        }

        if (n == 2) return -2.0 * Math.log(1.0 - p);

        double u = re_norm(p);
        double w = 2.0 / (9.0 * n);
        double x0 = 1.0 - w + u * Math.sqrt(w);
        x0 = n * x0 * x0 * x0;

        while (true)
        {
            TwoValues t=chi2(n,x0);                
            double f=t.last;
            double pp = t.first;
            if (f + 1.0 == 1.0) return x0;
            double xx = x0 - (pp - p) / f;
            if (Math.abs(x0 - xx) < 0.001) return xx;

            x0 = xx;
        }

    } 
	  
    public static TwoValues B(int n1, int n2, double x)
    {
        double Ux = 0;
        int m1 = 0, m2 = 0;
        double Ix = 0;

        double pi = 3.14159265358979312;

        if ((n1 % 2) == 1 && (n2 % 2) == 1)   /*  n1,n2均是奇数  */
        {
            Ux = Math.sqrt(x * (1.0 - x)) / pi;
            Ix = 1.0 - 2.0 * Math.atan(Math.sqrt((1.0 - x) / x)) / pi;
            m1 = m2 = 1;
        }

        if ((n1 % 2) == 1 && (n2 % 2) != 1) /*  n1是奇数,n2是偶数  */
        {
            Ux = Math.sqrt(x) * (1.0 - x) / 2.0;
            Ix = Math.sqrt(x);
            m1 = 1;
            m2 = 2;
        }

        if ((n1 % 2) != 1 && n2 % 2 == 1)  /*  n1是偶数,n2是奇数  */
        {
            Ux = x * Math.sqrt(1.0 - x) / 2.0;
            Ix = 1.0 - Math.sqrt(1.0 - x);
            m1 = 2;
            m2 = 1;
        }

        if ((n1 % 2) != 1 && (n2 % 2) != 1)  /*  n1,n2均是偶数  */
        {
            Ux = x * (1.0 - x);
            Ix = x;
            m1 = m2 = 2;
        }

        while (m2 != n2)
        {
            Ix = Ix + 2.0 * Ux / m2;
            Ux = Ux * (1.0 + m1 / (m2 + 0.0)) * (1.0 - x);
            m2 = m2 + 2;
        }

        while (m1 != n1)
        {
            Ix = Ix - 2.0 * Ux / m1;
            Ux = Ux * (1.0 + m2 / (0.0 + m1)) * x;
            m1 = m1 + 2;
        }

        return new TwoValues(Ix,Ux);
    }
    
    /**
     * t分布的分布函数值（负无穷到t的积分值）
     * @param nn
     * 自由度
     * @param t
     * @return
     */        
    public static TwoValues t(int nn, double t)
    {
        double f = 0.0;
        if (t + 1.0 == 1.0) return new TwoValues(0.5,f);

        double x = t * t / (nn + t * t);

        TwoValues tv=B(1,nn,x);
        double P = 0.5 * tv.first;
        f=tv.last;

        if (x < 0.0) P = 0.5 - P;
        else P = 0.5 + P;

        f = f / Math.abs(t);

        return new TwoValues(P,f);
    }
    
    /**
     * t分布的反函数：p=F(x),已知p，反求x
     * @param n
     * @param p
     * @return
     */        
     public static double re_t(int n, double p)
     {
            if (p < 0.5)
            {
                return -re_t(n, 1.0 - p);
            }
            double x;
            double pi = 3.14159265358979312;

            if (n == 1)
            {
                x = Math.tan(pi * (p - 0.5));
            }
            else if (n == 2)
            {
                double u = 2.0 * p - 1.0;
                u = u * u;
                x = Math.sqrt(2.0 * u / (1.0 - u));
            }
            else
            {
                x = re_norm(p) * Math.sqrt(n / (n - 2.0));

                while (true)
                {
                    TwoValues tv=t(n,x);
                    double f=tv.last;
                    double F = tv.first;
                    double dx = (F - p) / f;
                    x = x - dx;
                    if (Math.abs(dx) < 0.001) break;
                }
            }
            return x;
    }
}
