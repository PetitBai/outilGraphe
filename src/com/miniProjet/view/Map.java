package com.miniProjet.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.miniProjet.outils.MakePoint;
import com.miniProjet.outils.ReadData;

/*
 * ����MakePoint���µ�point�����ﱣ������껭����ͼ
 */
public class Map extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	//������
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
		String nodeId = null;
		selectNode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TextField text1, text2; 
				text1 = new TextField("�������룺", 10);  
		        text1.setEditable(false);  
		        text2 = new TextField(10);  
		        text2.setEchoChar('*');  
		        String s=JOptionPane.showInputDialog("Please input the id of node:");
			}
		});
//		zoomButton1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				ReadData.readNode(ReadData.NODE_PATH);//��node�ļ�������
//				MakePoint.makeScreenPoint();//ת��
//				for(int i = 0;i<MakePoint.point.size();i++){
//					MakePoint.point.get(i).x *= 2;
//					MakePoint.point.get(i).y *= 2;
//				}
//			}
//		});
		/****************************************************************/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.add(jToolBar, BorderLayout.PAGE_START);
		frame.add(new graphics());
		//frame.setContentPane(new graphics());//�ѻ��ĵ���ӵ�������
		System.out.println(nodeId);
	}
}

//��ÿ�������
class graphics extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ReadData.readNode(ReadData.NODE_PATH);//��node�ļ�������
		MakePoint.makeScreenPoint();//ת��
	
		for(int i = 0;i<MakePoint.point.size();i++){
			g.drawString(".",MakePoint.point.get(i).x,MakePoint.point.get(i).y);//����
			g.setColor(Color.RED);//set brush's color
		}
	}
}



