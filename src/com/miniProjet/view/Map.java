package com.miniProjet.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.miniProjet.outils.MakePoint;
import com.miniProjet.outils.ReadData;

/*
 * interface and draw map
 */
public class Map extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static void makeMap() {
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 1360, 760);
		
		/****************************************************************/
		JToolBar jToolBar = new JToolBar("JToolBar",0);
		JButton zoomButton1 = new JButton("ZOOM +");
		JButton zoomButton2 = new JButton("ZOOM -");
		JButton selectArc = new JButton("Select Arc");
		JButton selectNode = new JButton("Select Node");
		jToolBar.add(zoomButton1);
		jToolBar.add(zoomButton2);
		jToolBar.add(selectArc);
		jToolBar.add(selectNode);

		/****************************************************************/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.add(jToolBar, BorderLayout.PAGE_START);
		
		
		graphics mapGraphics = new graphics();		
		frame.add(mapGraphics);
				
		
	
		//frame.setContentPane(new graphics());//add map to framework

		/****************************************************************/
		selectNode.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
		        // s save the number inputed by user
		        String s=JOptionPane.showInputDialog("Please input the id of node:");
		        int pointId = Integer.parseInt(s);	
		        //make a new JPanel with the node found
		        pointFound hello = new pointFound(pointId);		       
		        frame.add(hello);
		        hello.updateUI();
		   	        
		        
			}
		});
		
		
		
	}
}

class graphics extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ReadData.readNode(ReadData.NODE_PATH);//read data
		MakePoint.makeScreenPoint();//get points
	
		for(int i = 0;i<MakePoint.point.size();i++){
			g.drawString(".",MakePoint.point.get(i).x,MakePoint.point.get(i).y);//draw map

			g.setColor(Color.RED);//set brush's color
		}
		

	}
}

class pointFound extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pointID;
	
	public pointFound(int i) {
		pointID = i;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ReadData.readNode(ReadData.NODE_PATH);//read data
		MakePoint.makeScreenPoint();//get points
		
		for(int i = 0;i<MakePoint.point.size();i++){
			g.drawString(".",MakePoint.point.get(i).x,MakePoint.point.get(i).y);//draw map

			g.setColor(Color.RED);//set brush's color
		}
		
		
//		/**
//		 * Use ImageIcon to draw the image PointFound
//		 */
//		ImageIcon icon = new ImageIcon("Images/dot.jpeg");	
//		Image imgDot = icon.getImage();
//		g.drawImage(imgDot, 500, 500, null);
		
		/**
		 * Use ImageIO to get the image PointFound
		 */
		if (pointID >= 0 && pointID < MakePoint.point.size()) {
			try {
				//draw the picture
				Image imgDot2 = ImageIO.read(new File("Images/dot.jpeg"));
				g.drawImage(imgDot2, MakePoint.point.get(pointID).x, MakePoint.point.get(pointID).y,20,30, null);
				g.setColor(Color.BLACK);
				//draw the latitude
				g.drawString(Integer.toString(MakePoint.point.get(pointID).x), MakePoint.point.get(pointID).x+30, MakePoint.point.get(pointID).y + 50);
				//draw the longtitude
				g.drawString(Integer.toString(MakePoint.point.get(pointID).y), MakePoint.point.get(pointID).x+30, MakePoint.point.get(pointID).y + 70);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}



