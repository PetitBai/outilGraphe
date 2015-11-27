package com.miniProjet.outils;

import java.awt.Point;
import java.util.ArrayList;
/*
 * change longitute and latitude to view point,you can read the article as follow link:
 * http://blog.csdn.net/pipi0714/article/details/5987107
 * i don't all understand the article,just use the method
 * the most code is in the method changeToScreenPoint()
 */
public class MakePoint {
	public static ArrayList<Point> point = new ArrayList<Point>();
	
	//�Ѿ�γ��ת������Ļ���겢������point������
	public static void makeScreenPoint(){
		for(int i = 0;i<ReadData.longitude.size();i++){
			point.add(changeToScreenPoint(ReadData.NODE_PATH,ReadData.longitude.get(i), ReadData.latitude.get(i)));
		}
	}
	
	public static Point changeToScreenPoint(String filename,double longitude,double latitude){
		int POLAR_RADII   =   6356752 ;  //����[�ϼ�]�İ뾶
	    int EQUATOR_RADII = 6378137  ; //����İ뾶
	    
	    //1:�Ѿ�γ��ת��Ϊ������
		double laRadian,loRadian;
		loRadian = longitude*Math.PI/180;
		laRadian = latitude*Math.PI/180;
             
        //2:�����������Ĳ��վ���
        double m_refY,m_refX;
        m_refY = POLAR_RADII + (EQUATOR_RADII - POLAR_RADII)*(90 - longitude)/90;
        m_refX = m_refY*Math.cos(laRadian);
        
        //3:����֪��ľ�γ��ת���ɻ���; ��(�ձ�)��Ϊ��:N35.54.10.8 E139.37.37��������ƪ�������ձ���д�ģ�
        double relaRadian,reloRadian;
        relaRadian  = (35 + 54/60 + 10.8/3600) * Math.PI/180;
        reloRadian  = (139 + 37/60 +37/3600) * Math.PI/180;

        
        //4:��������ص�,�Թ���Ļ��ʾ
        double dx =  (reloRadian - loRadian)*m_refX;
        double dy = -(relaRadian - laRadian)*m_refY;
        
        //5:DIVISORΪ����λ�þ�������ص���뻻���ϵ��,��С�ɸ�������Լ�����.
        Point point = new Point();
        int DIVISOR;
        
        /*
         * according to the node file's name to set the value of DIVISOR
         * because if we set DIVISOR only one value ,we can't get all the map
         */
        if(filename.equals("paris_noeuds.csv")){
        	 DIVISOR = 36;
             point.x = (int)dx/DIVISOR - 277300;       
             point.y = (int)dy/DIVISOR - 42600;
        }
        if(filename.equals("berlin_noeuds.csv")){
       	 	DIVISOR = 62;
            point.x = (int)dx/DIVISOR - 136570;       
            point.y = (int)dy/DIVISOR - 31100;
       }
        if(filename.equals("Data/sf_noeuds.csv")){
        	DIVISOR = 280;
            point.x = (int)dx/DIVISOR - 81760;       
            point.y = (int)dy/DIVISOR - 840;
       }
        return point;
	}
}
