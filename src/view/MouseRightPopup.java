package view;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class MouseRightPopup extends JPopupMenu
{
	private JMenuItem[] item;
	
	
	public MouseRightPopup()
	{
		super();
		item=new JMenuItem[10];
		
		item[0]=new JMenuItem("打开");
		item[1]=new JMenuItem("删除");
		item[2]=new JMenuItem("复制");
		item[3]=new JMenuItem("粘贴");
		item[4]=new JMenuItem("压缩");
		item[5]=new JMenuItem("解压");
		item[6]=new JMenuItem("加密");
		item[7]=new JMenuItem("解密");
		item[8]=new JMenuItem("刷新");
		item[9]=new JMenuItem("添加到左侧边栏");
		
		this.add(item[0]);
		this.add(item[1]);
		this.add(item[2]);
		this.add(item[3]);
		this.add(item[4]);
		this.add(item[5]);
		this.add(item[6]);
		this.add(item[7]);
		this.add(item[8]);
		this.add(item[9]);
	}
	
	public void addItemListener(int i,ActionListener a)
	{
		item[i].addActionListener(a);
	}
}
