package javafxapplication11;

import java.awt.image.*;//BufferedImage
import java.util.Arrays;

public class Merkmale {

    public MerkmalsVector mv;

    
    public Merkmale(int a, int b, int sizeX, int sizeY, BufferedImage img) {

        mv = new MerkmalsVector(256 * 3);
        int numberOfPixels = 0;

        // initialize the histogram array to all zeros
        int[] histogramR = new int[256];
        int[] histogramG = new int[256];
        int[] histogramB = new int[256];
        Arrays.fill(histogramR, 0);
        Arrays.fill(histogramG, 0);
        Arrays.fill(histogramB, 0);

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {

                int u = sizeX * a + x;
                int v = sizeY * b + y;

                int argb_in = img.getRGB(u, v);

                int red = Pixel.getRed(argb_in);
                int green = Pixel.getGreen(argb_in);
                int blue = Pixel.getBlue(argb_in);

                if (!(red > 240 && green > 240 && blue > 240)) {

                    // increment the corresponding bin of the histogram
                    histogramR[red]++;
                    histogramG[green]++;
                    histogramB[blue]++;

                    numberOfPixels++;
                }
            }
        }

        // normalize the histogram
        for (int i = 0; i < 256; i++) {
            mv.doubleMerkmale[i] = (double) histogramR[i] / numberOfPixels;
            mv.doubleMerkmale[256 + i] = (double) histogramG[i] / numberOfPixels;
            mv.doubleMerkmale[512 + i] = (double) histogramB[i] / numberOfPixels;
        }
    }
}

