package Main;

import View.MainFrame;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by 上官刀刀 on 2017/11/17.
 */
public class test {
    public static final void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int j, n;
        n = in.nextInt();

        {
            for (j = 2; j <= n / 2; j++) //第二个循环是判断i的值是不是素数.
            {
                if (n % j == 0) //如果被整除 那么就不是素数.跳出
                    break;
            }
            if (j > n / 2) //判断上面循环是否正常结束
            {
                System.out.print("prime");
            } else {
                System.out.print("not prime");
            }


        }
    }

}
