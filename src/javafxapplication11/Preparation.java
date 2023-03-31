package javafxapplication11;


import java.awt.Point;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Preparation {
    
    public ImageProcessing i_pro;
    
    public int[][] boundary_cloud;
    
    //seedPoint - Center 
    public int mx=0;
    public int my=0;

    private int wBlock;
    private int hBlock;
    
 //   public int thres=250;
   public int thres=220;

  //size for SeedPoint suche
    public  int sizeSearch=5;
    
  //Seed Point coordinates
    public int[][] x0;
    public int[][] y0;
   
   // image size
    public int Width=0; 
    public int Height=0; 

    
    //Final List of boundary points for each subimage  
   public LinkedList[][] neighbours;
   // List of boundary points for each subimage within one region growing step
   public LinkedList[][] neighboursT;
   // List of boundary points for each subimage within one region growing step
   public LinkedList[][] listGrowing;
   // A small list of boundary points which cannot be passed by region growing,
   //such that the final growing procedure only is performed in one direction
   //around the object
   public LinkedList[][] stopper;
  
  

    
    public Preparation(ImageProcessing image_pro){
    
    i_pro=image_pro;
    wBlock=i_pro.wBlock;
    hBlock=i_pro.hBlock;
     
    neighbours=  new LinkedList[wBlock][hBlock];
   neighboursT=  new LinkedList[wBlock][hBlock];
   listGrowing=  new LinkedList[wBlock][hBlock];
   stopper=  new LinkedList[wBlock][hBlock];
   
    x0=new int[wBlock][hBlock];
    y0=new int[wBlock][hBlock];
    
    
     for(int a=0;a<wBlock;a++){
        for(int b=0;b<hBlock;b++){
   
    neighbours[a][b]=  new LinkedList(); 
    neighboursT[a][b]=  new LinkedList();
    listGrowing[a][b]=  new LinkedList(); 
    stopper[a][b]=  new LinkedList(); 
    
                 }
             }
     
    }
    
      public void next_SeedPoint(){
    
        if(i_pro.img!=null){
            
            Width=i_pro.img.getWidth();
            Height=i_pro.img.getHeight();
            
        i_pro.sizeX=Width/wBlock;
        i_pro.sizeY=Height/hBlock;
                
        for(int u=-sizeSearch;u<sizeSearch;u++){
        for(int v=-sizeSearch;v<sizeSearch;v++){
            
                      
                        int x1=mx+u;
                        int y1=my+v;
           
           
           if(boundary_cloud[x1][y1]==255){
               
        
               // überschreibe den Seed Point =0
               // mit den aktuellen koordinaten
               //falls er im teilbild liegt
        for(int a=0;a<i_pro.wBlock;a++){
                 for(int b=0;b<i_pro.hBlock;b++){
   
               if(a*i_pro.sizeX<=x1 && x1<= (((a+1)*i_pro.sizeX)-1 ) && b*i_pro.sizeY<=y1 && y1<= (((b+1)*i_pro.sizeY)-1 )){
               
               x0[a][b]=x1;
               y0[a][b]=y1;
     
           int argb=i_pro.setARGB(255,255,0,0);
                         i_pro.img_prep.setRGB(x1,y1,argb);
                         
               }  
               
                 }}
                          i_pro.gui.preparation.repaint();

//                                      gui.output2.repaint();

        }
        }
        }
          }
   
        }
     
      
          public void neighbourneighbour(int a, int b, LinkedList neighbours_){
    
        
          // one region growing step with points collected in 
          // listGrowing
            listGrowing[a][b]=new LinkedList(); 
 
    
      Iterator it=neighboursT[a][b].iterator();
    
     
     while(it.hasNext()){
         
         Point u=(Point)it.next();
         
        int x=u.x;
        int y=u.y;
           
      //  if(x >0 && y>0){ //nur ausführen 
                        //wenn ein seed point gewählt wurde
          //Punkte P in der 8er Umgebung dieses letzten Punktes
                            
                                Point P=new Point(0,0);
                                
                                if(boundary_cloud[x-1][y]==255){
                                
                                P=new Point(x-1,y);
    if(!(neighboursT[a][b].contains(P)) && !(listGrowing[a][b].contains(P))&& !(stopper[a][b].contains(P))){ listGrowing[a][b].add(P);                                  
                    
                                    }
                                }
                                if(boundary_cloud[x+1][y]==255){
                                 P=new Point(x+1,y);
   if(!(neighboursT[a][b].contains(P)) && !(listGrowing[a][b].contains(P))&& !(stopper[a][b].contains(P))){ listGrowing[a][b].add(P);                                  
                                    }
                                }
                       
                                if(boundary_cloud[x][y-1]==255){
                                P=new Point(x,y-1);
   if(!(neighboursT[a][b].contains(P)) && !(listGrowing[a][b].contains(P))&& !(stopper[a][b].contains(P))){ listGrowing[a][b].add(P);                                  
                                    }
                                }
                       
                                if(boundary_cloud[x][y+1]==255){
                                P=new Point(x,y+1);
                                                                           
   if(!(neighboursT[a][b].contains(P)) && !(listGrowing[a][b].contains(P))&& !(stopper[a][b].contains(P))){ listGrowing[a][b].add(P);                                  
                    
                                    }
                                }
                       
                                if(boundary_cloud[x+1][y+1]==255){
                                P=new Point(x+1,y+1);
   if(!(neighboursT[a][b].contains(P)) && !(listGrowing[a][b].contains(P))&& !(stopper[a][b].contains(P))){ listGrowing[a][b].add(P);                                  
                                    }
                                }
                       
                                if(boundary_cloud[x+1][y-1]==255){
                                P=new Point(x+1,y-1);
   if(!(neighboursT[a][b].contains(P)) && !(listGrowing[a][b].contains(P))&& !(stopper[a][b].contains(P))){ listGrowing[a][b].add(P);                                  
                                           
                                    }
                                }
                       
                                if(boundary_cloud[x-1][y+1]==255){
                                P=new Point(x-1,y+1);
   if(!(neighboursT[a][b].contains(P)) && !(listGrowing[a][b].contains(P))&& !(stopper[a][b].contains(P))){ listGrowing[a][b].add(P);                                  
                                             
                                    }
                                }
                       
                                if(boundary_cloud[x-1][y-1]==255){
                                P=new Point(x-1,y-1);
   if(!(neighboursT[a][b].contains(P)) && !(listGrowing[a][b].contains(P))&& !(stopper[a][b].contains(P))){ listGrowing[a][b].add(P);                                  
                                                              
                                    }
                                }
    // }
     
     }
     
         
     neighbours_.addAll(listGrowing[a][b]); 
     
     neighboursT[a][b]=new LinkedList();
     
neighboursT[a][b].addAll(neighbours_);


     
    }    
   

        public void stopper(){
            
             //Perform only one step of region growing from a seed grow
// such that a small list is generated such that the final list will not pass that
// region and therefore final region growing is only performed in one direction
// around an object;
           
//First add the first seed point to the list stopper 
             
                    for(int a=0;a<wBlock;a++){
                 for(int b=0;b<hBlock;b++){

             Point p1=new Point(x0[a][b],y0[a][b]);
             
             neighboursT[a][b].add(p1); 
             
           
                 }}                        
             
      //perform a region growing step and add those points to list stopper
                    
            for(int a=0;a<wBlock;a++){
                 for(int b=0;b<hBlock;b++){

             neighbourneighbour(a,b,stopper[a][b]);
            
                 }}
  //Paint stopper regions
  
   for(int a=0;a<wBlock;a++){
                 for(int b=0;b<hBlock;b++){

         Iterator it1 = neighbours[a][b].iterator();
                     int argb=i_pro.setARGB(255,0,255,0);
                              
                     while(it1.hasNext()){

                      Point   p=(Point)it1.next();
             
                          i_pro.img_prep.setRGB(p.x,p.y,argb);
             
                       }
                     }}
           i_pro.gui.preparation.repaint();
                       
     
            
            
        }
        
  public void fullList(){
        
      //add a first seed point
       for(int a=0;a<wBlock;a++){
          for(int b=0;b<hBlock;b++){

        
        neighboursT[a][b]=new LinkedList();
  
        Point p1=new Point(x0[a][b],y0[a][b]);
        
        
        neighboursT[a][b].add(p1);
                 }        
                 }     

            //perform region growing in both directions.
            //one directoin is blocked with the list stopper
            
            for(int k=0;k<1500;k++){
            
            for(int a=0;a<wBlock;a++){
                 for(int b=0;b<hBlock;b++){

            neighbourneighbour(a,b,neighbours[a][b]);
                 }            
        }
        }

        
        
           for(int a=0;a<wBlock;a++){
                 for(int b=0;b<hBlock;b++){

         Iterator it1 = neighbours[a][b].iterator();
                     int argb=i_pro.setARGB(255,0,0,255);
                              
                     while(it1.hasNext()){

                      Point   p=(Point)it1.next();
             
                          i_pro.img_prep.setRGB(p.x,p.y,argb);
             
                       }
                     }}
           i_pro.gui.preparation.repaint();
                       
    
                  
           for(int a=0;a<wBlock;a++){
                 for(int b=0;b<hBlock;b++){

                    fourier(neighbours[a][b]);         
                 }}
         
    }

 public void fourier(LinkedList neighbours){
 //Fourier-Ellipse approximation with coefficiants c[0] and c[1]
  
  
  int NN = neighbours.size();
System.out.println(NN);

DFT dft =new DFT(NN);
Complex[] z=new Complex[NN];

Iterator it=neighbours.iterator();

int i=0;
while(it.hasNext()){
Point p=(Point)it.next();

z[i]=new Complex((double)p.x,(double)p.y);
i++;
}

// Transform the curve into DFT -Coefficiants
Complex[] c=dft.transform(z);


Complex[] c_filtered=new Complex[NN];

for(int j=0; j<NN; j++){
    c_filtered[j]=new Complex(0.0,0.0);
}
// The coefficiants c[0],c[1],c[N-1] will give an 
// ellipse-approximation. c[2],...,c[4] will give higher order features
//such as non convex regions

c_filtered[0]=c[0];
c_filtered[1]=c[1];
c_filtered[2]=c[2];
c_filtered[3]=c[3];
c_filtered[4]=c[4];


c_filtered[NN-1]=c[NN-1];
c_filtered[NN-2]=c[NN-2];
c_filtered[NN-3]=c[NN-3];
c_filtered[NN-4]=c[NN-4];

Complex[] rec=dft.invtrans(c_filtered);

for(int j=0; j<NN; j++){

int red=i_pro.setARGB(255, 255,255,0);    
i_pro.img_prep.setRGB((int)rec[j].getReal(),(int)rec[j].getImag(), red);

}
        i_pro.gui.preparation.repaint();
   
          
    }

  
          
          
   public void boundary_extraction(){
       
       
    if(i_pro.img!=null){
//x,y will be the left upper corner of the sliding window
//u,v will be the coordinates within the sliding window

//The size of the sliding window

int P=1;
int Q=1;



 Width= i_pro.img.getWidth();
 Height= i_pro.img.getHeight();


 
                         System.out.println("Width:" +Width);
           
                         System.out.println("Height:" +Height);
 
int[][] segm_data=new int[Width][Height];
int[][] segm_data_extended=new int[Width][Height];
 boundary_cloud=new int[Width][Height];

 

                        for(int x=0;x<Width;x++){
                        for(int y=0;y<Height;y++){
                                                        
                                int argb_in=i_pro.img.getRGB(x, y);
                                int r=i_pro.getRed(argb_in);
                                int g=i_pro.getGreen(argb_in);
                                int b=i_pro.getBlue(argb_in);
                                
                                 segm_data[x][y]=255;
                                
                                // segementation
                                if(!(r>thres && g>thres && b>thres)){
                                
                                 segm_data[x][y]=0;}
                                
                          int newgrey=segm_data[x][y];
                          int argb_out=i_pro.setARGB(255,newgrey,newgrey,newgrey);
                          i_pro.img_prep.setRGB(x , y ,argb_out);

                                
                        }}
                                
                        //Morphological filtering
                        
                        for(int x=P;x<Width-P;x++){
                        for(int y=Q;y<Height-Q;y++){
                         
                               int[] ar=new int[(2*P+1)*(2*Q+1)];
                         
                                
                                
                            int index=0;
                            for(int u=-P; u<=P;u++){
                                for(int v=-Q; v<=Q;v++){
                            
                                int grey=segm_data[x+u][y+v];
                                    
                                    ar[index]=grey;
                                
                                index++;
                                
                                }
                            } 

                            
                            //Make in ordering
                          Arrays.sort(ar);
                    
                          segm_data_extended[x][y]=ar[0];
                          
                          int newgrey=segm_data_extended[x][y];
                            
                          
                                int argb_out=i_pro.setARGB(255,newgrey,newgrey,newgrey);
                                i_pro.img_prep.setRGB(x , y ,argb_out);
                                }
                            }
                        
                        //subtraction
                        
                        for(int x=P;x<Width-P;x++){
                        for(int y=Q;y<Height-Q;y++){
                         
                             boundary_cloud[x][y]=255;
                            
                            if(segm_data[x][y]== segm_data_extended[x][y]){
                                boundary_cloud[x][y]=0;}
                         
                            int argb_out=i_pro.setARGB(255,boundary_cloud[x][y],boundary_cloud[x][y],boundary_cloud[x][y]);
                                i_pro.img_prep.setRGB(x , y ,argb_out);
                      
                                }
                            } 

                        // putting the bounday clowd in a list
                        
                        
                        
                        

    }

    }

       
  
    
}
