package control;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import config.Constant;
import view.MianWindow;

public class FileManage {
	public static String[] ROOT;
	public static void main(String[] args){
	
		//设置初始目录为系统默认
		FileSystemView rootview=FileSystemView.getFileSystemView();
		File root=rootview.getDefaultDirectory();
		MianWindow mainwindow = new MianWindow(root.getPath());
		FileOperate operate = new FileOperate();
		mainwindow.setVisible(true);
	}
}
