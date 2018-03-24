package education.cs.scu.utils;

import java.text.DecimalFormat;

public class NumUtil {
    private static DecimalFormat decimalFormat=new DecimalFormat(".0");

    public static float get1Decimal(double num) {
        return Float.parseFloat(decimalFormat.format(num));
    }
}
