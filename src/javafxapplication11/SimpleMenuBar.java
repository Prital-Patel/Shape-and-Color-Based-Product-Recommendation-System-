package javafxapplication11;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author KC
 */
import javax.swing.*;
import java.awt.*;
import javax.imageio.*; //ImageIO
import java.awt.image.*;
import java.io.*;


public class SimpleMenuBar extends JMenuBar{

    private JMenu FileMenu;
    private JMenuItem OpenMenuItem;
    private GUI g;


    public SimpleMenuBar(GUI g){

    this.g=g;

    FileMenu = new JMenu();
    OpenMenuItem = new JMenuItem();

    FileMenu.setText("File");
    OpenMenuItem.setText("Open");

    add(FileMenu);
    FileMenu.add(OpenMenuItem);

    OpenMenuItem.addActionListener(new java.awt.event.ActionListener()
    {
          public void actionPerformed(java.awt.event.ActionEvent evt)
                         { OpenMenuAction(evt);
                                      }
     });

    }

    private void OpenMenuAction(java.awt.event.ActionEvent evt)
    {

    FileDialog fd = new FileDialog(g);
    fd.setVisible(true);
                //fd.show();


    g.setTitle("Dir: " +fd.getDirectory() + " File: " + fd.getFile());
    if (fd.getFile() == null) return;



    try{
    g.img_pro.img= ImageIO.read( new File(fd.getDirectory() + fd.getFile()));             // load the image
                }catch(Exception e){};

    g.img_pro.img_prep=  new BufferedImage(g.img_pro.img.getWidth(),g.img_pro.img.getHeight(),BufferedImage.TYPE_INT_ARGB);

    g.img_pro.img_out=  new BufferedImage(g.img_pro.img.getWidth(),g.img_pro.img.getHeight(),BufferedImage.TYPE_INT_ARGB);

    g.ic=new ImageIcon(g.img_pro.img);
    g.ic_prep=new ImageIcon(g.img_pro.img_prep);
    g.ic_out=new ImageIcon(g.img_pro.img_out);

   
              g.input.setIcon(g.ic);
              g.preparation.setIcon(g.ic_prep);
              g.output.setIcon(g.ic_out);
              g.pack();

  }
}

