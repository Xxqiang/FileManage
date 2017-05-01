package control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.swing.JOptionPane;
import config.Constant;
import view.MianWindow;
public class FileOperate {
	


	// 添加文件
	public boolean addFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			JOptionPane.showMessageDialog(null, "文件已经存在", "提示", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// 判断文件是否为目录：后面以‘／’结尾
		if (filePath.endsWith(File.separator)) {
			JOptionPane.showMessageDialog(null, "文件不能为目录", "提示", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		try {
			if (file.createNewFile()) {
				JOptionPane.showMessageDialog(null, "文件创建成功", "提示", JOptionPane.WARNING_MESSAGE);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "文件创建失败", "提示", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	public boolean addDir(String destDirName) {

		File file = new File(destDirName);
		if (file.exists()) {
			// 目录已经存在
			destDirName = destDirName + "副本";
			file = new File(destDirName);
		}

		// 判断目录文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			JOptionPane.showMessageDialog(null, "文件创建失败", "提示", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (file.mkdirs()) {
			JOptionPane.showMessageDialog(null, "文件夹创建成功", "提示", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "文件创建失败", "提示", JOptionPane.WARNING_MESSAGE);
			return false;
		}

	}

	public boolean deleteFile(String filePath) throws FileNotFoundException {
		try {

			File file = new File(filePath);
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] list = file.list();
				for (int i = 0; i < list.length; i++) {
					File temp = new File(filePath + File.separator + list[i]);
					temp.delete();
				}
				file.delete();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "提示", JOptionPane.WARNING_MESSAGE);
		}
		JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.WARNING_MESSAGE);
		return true;
	}

	
    public  void copy(String src, String des) {  
        File file1=new File(src);  
        File[] fs=file1.listFiles();
        File file2=new File(des);  
        if(!file2.exists()){  
            file2.mkdirs();  
        }
        for (File f : fs) {  
            if(f.isFile()){  
                fileCopy(f.getPath(),des+File.separator+f.getName()); //调用文件拷贝的方法  
            }else if(f.isDirectory()){  
            	addDir(des+File.separator+f.getName());
                copy(f.getPath(),des+File.separator+f.getName());  
            }  
        }  
          
    }  
  
    /** 
     * 文件拷贝的方法 
     */  
    public static void fileCopy(String src, String des) {  
      
        BufferedReader br=null;  
        PrintStream ps=null;  
          
        try {  
            br=new BufferedReader(new InputStreamReader(new FileInputStream(src)));  
            ps=new PrintStream(new FileOutputStream(des));  
            String s=null;  
            while((s=br.readLine())!=null){  
                ps.println(s);  
                ps.flush();  
            }  
              
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
              
                try {  
                    if(br!=null)  br.close();  
                    if(ps!=null)  ps.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
                  
        }  
          
          
    }  

	public boolean encryptFile(String filePath) {

		return false;
	}

	public boolean decodeFile(String filePath) {

		return false;
	}

	// 返回上一级
	public String pathBackTo(String path) // 处理路径字符串，删除最后一个\和之后的字符串
	{
		StringBuffer temp = new StringBuffer(path);
		if (temp.length() != 0) {
			int start = temp.lastIndexOf("/"); // 转义字符\\表示\
			if (start != -1)
				temp.delete(start, temp.length());
			if (temp.charAt(temp.length() - 1) == ':')
				temp.append('/');
		}
		return temp.toString();
	}

	// 得到路径结尾文件名
	public String getFilenameFromPath(String path) // 处理路径字符串，删除最后一个\和之后的字符串
	{
		StringBuffer temp = new StringBuffer(path);
		if (temp.length() != 0) {
			int end = temp.lastIndexOf("/"); // 转义字符\\表示\
			if (end != -1)
				temp.delete(0, end + 1);
		}
		return temp.toString();
	}

}
