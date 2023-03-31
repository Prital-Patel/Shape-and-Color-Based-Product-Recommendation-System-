package javafxapplication11;

/**
 *
 * @author KC
 */

public class Pixel {

 public static int setARGB(int al,int red,int green, int blue){
    int val= al<<24 | red<<16 | green<< 8 | blue;
    return val;
    }

    public static  int getAlpha(int argb){

    return (argb >> 24) & 0xFF;
    }

  public static int getRed(int argb){

    return (argb >> 16) & 0xFF;
    }

  public static int getGreen(int argb){

    return (argb >> 8) & 0xFF;
    }

  public static int getBlue(int argb){

    return argb & 0xFF;
    }


}
