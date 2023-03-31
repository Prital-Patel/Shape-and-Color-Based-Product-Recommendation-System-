package javafxapplication11;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ImageProcessing {
    
    public BufferedImage img=null;   
    public BufferedImage img_prep=null; 
    public BufferedImage img_out=null; 
 

public GUI gui;
public Preparation i_p;

public int wBlock=4;    
public int hBlock=5;     

public int sizeX;
public int sizeY;


int a_optimal=0;
int b_optimal=0;

int chosen_a=2;  // select image
int chosen_b=0;
            
public Merkmale m_searchImage;

public ImageProcessing(){

    gui=new GUI(this);
    i_p=new Preparation(this);
    
}

public void process_featureExtractionSearchImage(){

    if(img!=null ){
              
        sizeX=img.getWidth()/wBlock;
        sizeY=img.getHeight()/hBlock;
              
        m_searchImage=new Merkmale(chosen_a,chosen_b,sizeX ,sizeY ,img);
        System.out.println("histogram:"+Arrays.toString(m_searchImage.mv.doubleMerkmale));     
    }
}

public void process_featureExtractionSelectImages(){
    
    if(img!=null ){
             
   
        //If the distance is below a certain threshold, mark the corresponding image as selected. 
        
        double threshold = Double.MAX_VALUE;
        
        for(int a=0;a<wBlock;a++){
            for(int b=0;b<hBlock;b++){
                Merkmale m=new Merkmale(a,b,sizeX,sizeY,img);
                
                if(!((a==chosen_a)&& (b==chosen_b))){
                    double distance = histogramDistance(m_searchImage.mv.doubleMerkmale, 
                            m.mv.doubleMerkmale);
                    
                    if(distance < threshold){
                        threshold = distance;
                        a_optimal = a;
                        b_optimal = b;
                    }
                }        
            }
            
            System.out.println("a_optimal:"+a_optimal);
                
        }
            
    }
}           


public void displaySelectedImage(){

    if(img!=null){

        sizeX = img.getWidth()/wBlock;
        sizeY = img.getHeight()/hBlock;
        
        for(int x=0;x<sizeX;x++){
            for(int y=0;y<sizeY;y++){

                int u = sizeX*a_optimal+x;
                int v = sizeY*b_optimal+y;

                int argb_in = img.getRGB(u, v);

                img_out.setRGB(u , v ,argb_in);
            }
        }
                    
    }

}

public double histogramDistance(double[] input1, double[] input2){
    double sd = 0.0;
    for(int i=0; i<input1.length; i++){
        double diff = input1[i] - input2[i];
        sd += diff * diff;
    }
    return Math.sqrt(sd);
}

        
    public int setARGB(int al,int red,int green, int blue){
    int val= al<<24 | red<<16 | green<< 8 | blue;
    return val;
    }

    public int getAlpha(int argb){

    return (argb >> 24) & 0xFF;
    }

  public int getRed(int argb){

    return (argb >> 16) & 0xFF;
    }

  public int getGreen(int argb){

    return (argb >> 8) & 0xFF;
    }

  public int getBlue(int argb){

    return argb & 0xFF;
    }

}
