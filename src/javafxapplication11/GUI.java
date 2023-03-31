package javafxapplication11;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GUI extends JFrame{

    private Container con;
    private SimpleMenuBar sb;
    private JButton search;

    private JButton p_Extract;
    private JButton p_Stopper;
    private JButton p_FCoeff;
    

    
    
    public JLabel input;
    public JLabel preparation;
    public JLabel output;
    public ImageProcessing img_pro;
    public ImageIcon ic;
    public ImageIcon ic_prep;
    public ImageIcon ic_out;

//    public Diagramm dia;
    
    

    public GUI(ImageProcessing i_pro){

    img_pro=i_pro;
    

    sb=new SimpleMenuBar(this);
    setJMenuBar(sb);

    con=getContentPane();
    con.setLayout(new FlowLayout(FlowLayout.LEFT));

    p_Extract=new JButton("P_Extract");
    p_Stopper=new JButton("P_Stopper");
    p_FCoeff=new JButton("P_FCoeff");
    search= new JButton ("Search");
    
    
    input= new JLabel ("Input Image");
    preparation=new JLabel("Preparation Image");
    output= new JLabel ("Output Image");

    search.addActionListener(

      new ActionListener(){
      public void actionPerformed(ActionEvent e){

        // 

         img_pro.process_featureExtractionSearchImage();
        img_pro.process_featureExtractionSelectImages(); 
        img_pro.displaySelectedImage();
        
        output.repaint();

      }});

    
    p_Extract.addActionListener(

      new ActionListener(){
      public void actionPerformed(ActionEvent e){

        // 

                     img_pro.i_p.boundary_extraction();
                      preparation.repaint();

      }});

    
      preparation.addMouseListener(new MouseAdapter(){
                 public void mouseClicked(java.awt.event.MouseEvent e){

                int x=e.getX();
                int y=e.getY();
                seedPoint(x,y);
                preparation.repaint();

                }
   });

      p_Stopper.addActionListener(

      new ActionListener(){
      public void actionPerformed(ActionEvent e){

        // 

                     img_pro.i_p.stopper();
                      preparation.repaint();

      }});

      p_FCoeff.addActionListener(

      new ActionListener(){
      public void actionPerformed(ActionEvent e){

        // 

                     img_pro.i_p.fullList();
                      preparation.repaint();

      }});
      
    con.add(p_Extract);
    con.add(p_Stopper);
    con.add(p_FCoeff);
    con.add(search);
   
    
    con.add(input);
    con.add(preparation);
    con.add(output);

    
    setVisible(true);
    pack();
    }

    private void seedPoint(int x, int y){

      
              img_pro.i_p.mx=x;
              img_pro.i_p.my=y;
        
    img_pro.i_p.next_SeedPoint();

             

   }
}
