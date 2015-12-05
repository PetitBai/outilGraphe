package com.miniProjet.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
		JButton selArcButton = new JButton("Select Arc");
		JButton selNodeButton = new JButton("Select Node");
		JButton export = new JButton("Export");
		jToolBar.add(zoomButton1);
		jToolBar.add(zoomButton2);
		jToolBar.add(selArcButton);
		jToolBar.add(selNodeButton);
		jToolBar.add(export);
		
		/****************************************************************/
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mFile = new JMenu("File");
		JMenu mSelect = new JMenu("Select");
		JMenu mChange = new JMenu("Change");
		JMenuItem itemOpen = new JMenuItem("Open");
		JMenuItem itemSave = new JMenuItem("Save");
		JMenuItem itemSelNode = new JMenuItem("Select node");
		JMenuItem itemSelArc = new JMenuItem("Select arc");
		JMenuItem itemChgNodeColor = new JMenuItem("Change node's color");
		JMenuItem itemChgNodeSize = new JMenuItem("Change node's size");
		JMenuItem itemChgArcColor = new JMenuItem("Change arc's color");
		JMenuItem itemChgArcSize = new JMenuItem("Change arc's size");
		menuBar.add(mFile);
		menuBar.add(mSelect);
		menuBar.add(mChange);
		mFile.add(itemOpen);
		mFile.addSeparator();
		mFile.add(itemSave);
		mSelect.add(itemSelNode);
		mSelect.addSeparator();
		mSelect.add(itemSelArc);
		mChange.add(itemChgNodeSize);
		mChange.addSeparator();
		mChange.add(itemChgNodeColor);
		mChange.addSeparator();
		mChange.add(itemChgArcSize);
		mChange.addSeparator();
		mChange.add(itemChgArcColor);
		
		/****************************************************************/
		itemSelNode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showInputDialog("Please enter the id of node:");
				selectNode(frame);
			}
		});
		
		itemSelArc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectArc(frame);			
			}
		});
		/****************************************************************/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.add(jToolBar, BorderLayout.PAGE_START);
		
		/*********************************************/
		/*********************************************/
		frame.add(new graphics());
		//frame.setContentPane(new graphics());//add map to framework
		/****************************************************************************/
		//保存文件时貌似可以用
//		graphics gs = new graphics();
//		Graphics newGs = gs.getGraphics();
//		frame.add(gs);
		/*********************************************/
		
		selNodeButton.addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectNode(frame);		        
			}
		});	
		
		selArcButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectArc(frame);
			}			
		});
	}
	
	public static void selectNode(JFrame frame){
		// strNode save the number inputed by user
        String strNode=JOptionPane.showInputDialog("Please enter the id of node:");
        //judge if input is empty
		if (strNode.equals("")) {
			JOptionPane.showMessageDialog(null, "string is empty");
		} else {
			// judge if input is a number
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(strNode);
			if (isNum.matches()) {
				int pointId = Integer.parseInt(strNode);
				// judge if number out of range
				if (pointId > ReadData.longitude.size()) {
					JOptionPane.showMessageDialog(null,
							"Out of range!", "WARNING",
							JOptionPane.WARNING_MESSAGE);
				}
				// make a new JPanel with the node found
				pointFound hello = new pointFound(pointId);
				
				frame.add(hello);
				hello.updateUI();
			} else {
				JOptionPane.showMessageDialog(null,
								"Please enter an integer greater than zero or zero!",
								"WARNING", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public static void selectArc(JFrame frame){
		JDialog jd = new JDialog();
        jd.setBounds(320, 180, 260, 120);
        jd.setTitle("Select an arc");
        jd.getContentPane().setLayout(new GridLayout(3, 3));
        jd.add(new JLabel("Source"));
        JTextField textSource = new JTextField(80);
        jd.add(textSource);
        jd.add(new JLabel("Target"));
        JTextField textTarget = new JTextField(80);
        jd.add(textTarget);
        
        //two buttons
        JButton bCommit = new JButton("Commit");
        JButton bCancel = new JButton("Cancel");
        jd.add(bCommit);
        jd.add(bCancel);
        
        //ActionListener on button-Commit
        bCommit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String strSource = textSource.getText();
				String strTarget = textTarget.getText();
										
				//judge two string if is empty
				if (strSource.equals("") || strTarget.equals("")) {
					JOptionPane.showMessageDialog(null,"string is empty");
				}
				else {
					// judge if input is a number
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNumS = pattern.matcher(strSource);
					Matcher isNumT = pattern.matcher(strTarget);
					if(isNumS.matches() && isNumT.matches()){
						int iSource = Integer.parseInt(strSource);
						int iTarget = Integer.parseInt(strTarget);

						boolean arcIsExist = false;
						int index = 0;
						for (int i = 0; i < ReadData.source.size(); i++) {
							if (ReadData.source.get(i) == iSource
									&& ReadData.target.get(i) == iTarget) {
								arcIsExist = true;
								index = i;
								break;
							} else {
								arcIsExist = false;
							}
						}
						if (arcIsExist) {
							// make a new JPanel with the arc found
							arcFound arc = new arcFound(index, iSource,iTarget);
							frame.add(arc);
							arc.updateUI();

							// close dialog
							jd.dispose();
						} else {
							//怎么把之前输入的自动擦除？
							JOptionPane.showMessageDialog(null,"There is not the arc,please enter again!");
						}
					}else{
						//怎么把之前输入的自动擦除？
						JOptionPane.showMessageDialog(null,
								"Please enter an integer greater than zero!",
								"WARNING", JOptionPane.WARNING_MESSAGE);
					}							
				}
			}
		});
        
        //ActionListener on button-Cancel
        bCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jd.dispose();						
			}
		});
        
        //
        jd.setModal(true);//确保弹出的窗口在其他窗口前面
        jd.setVisible(true);
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
		ReadData.readArc(ReadData.ARC_PATH);
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
		
		
		/**
		 * Use ImageIO to get the image PointFound
		 */
		if (pointID >= 0 && pointID < MakePoint.point.size()) {
			try {
				//draw the picture
				Image imgDot2 = ImageIO.read(new File("Image/dot.gif"));
				g.drawImage(imgDot2, MakePoint.point.get(pointID).x, MakePoint.point.get(pointID).y,20,30, null);
				g.setColor(Color.BLACK);
				//draw the latitude
				g.drawString(Double.toString(ReadData.latitude.get(pointID)),30, 50);
				//draw the longtitude
				g.drawString(Double.toString(ReadData.longitude.get(pointID)),30, 70);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

class arcFound extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int index;
	private int arcSource;
	private int arcTarget;

	public arcFound(int index,int source, int target) {
		arcSource = source;
		arcTarget = target;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ReadData.readNode(ReadData.NODE_PATH);// read data
		ReadData.readArc(ReadData.ARC_PATH);
		MakePoint.makeScreenPoint();// get points

		for (int i = 0; i < MakePoint.point.size(); i++) {
			g.drawString(".", MakePoint.point.get(i).x,
					MakePoint.point.get(i).y);// draw map
			g.setColor(Color.RED);// set brush's color
		}

		g.setColor(Color.BLUE);
		g.drawLine(MakePoint.point.get(arcSource).x,
				MakePoint.point.get(arcSource).y,
				MakePoint.point.get(arcTarget).x,
				MakePoint.point.get(arcTarget).y);
		g.drawString("Source: " + Double.toString(ReadData.source.get(index+1)),30, 50);		
		g.drawString("Target: " + Double.toString(ReadData.target.get(index+1)),30, 70);
		g.drawString("Long: " + Double.toString(ReadData.distance.get(index+1)),30, 90);
		g.drawString("Dang " + Double.toString(ReadData.dang.get(index+1)),30, 110);
	}
}
