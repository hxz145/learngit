import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;






public class FileDemo {
	
		public FileDemo() {
			//默认路径
			String pathname="C:java";
			//设置对话框打开时的根目录
			JFileChooser fileChooser=new JFileChooser(pathname);
			//显示出选择文件的对话框
			fileChooser.showOpenDialog(null);
			//获得用户选择的文件
			File file=fileChooser.getSelectedFile();
			try {
				FileInputStream in=new FileInputStream(file);
				
				byte[] buf=new byte[1024];
				int len=0;
				
					while((len=in.read(buf))!=-1) {
						System.out.println(new String(buf,0,len));
					}
					in.close();
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
			//判断是否选择文件
			if(file==null) {
				System.out.println("未选择文件");
				System.exit(0);
			}
			
		}
	
}	
		
