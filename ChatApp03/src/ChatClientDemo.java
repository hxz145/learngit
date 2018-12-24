
//������2.0 �ͻ���   ��½����

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ChatClientDemo extends JFrame {
	private JFrame jf;// ����
	private Container c;
	private String name;// �û���
	private JLabel label;// ���촰���е��û���
	private JLabel jl1;// ����"ip��ַ:"��ǩ
	private JTextField jtf1;// �����û����봰
	private JTextField jpf1;// �����˿����봰
	private JLabel jl2;// ����"����:"��ǩ
	private JLabel jl3;
	private JTextField jif1;
	private JButton jb1;
	private JButton jb2;
	private JTextArea jta;// ����������ʾ�ı���
	private JTextField jtf;// �Լ��������ݵ��ı���
	private JButton jb;// ���Ͱ�ť
	private Socket s;// ʵ�ֿͻ��������˵�����
	private PrintWriter pw;// �������д��������
	private BufferedReader br;

	public ChatClientDemo() {

		// ���ñ���x
		setTitle("������2.0");
		setAlwaysOnTop(true);
		// ���Բ���
		getContentPane().setLayout(null);
		// ����һ������
		Container c = getContentPane();
		// ����"ip:"��ǩ
		JLabel jl1 = new JLabel(" i p \uFF1A");
		// �����ı���
		jtf1 = new JTextField();
		// ����"�˿ں�:"��ǩ
		JLabel jl2 = new JLabel("�˿ں�:");
		// ���������
		jpf1 = new JTextField();
		JLabel jl3=new JLabel("�û���");
		jif1=new JTextField();

		// ����"�ύ"��ť
		JButton jb1 = new JButton("�ύ");
		jb1.addMouseListener(new mouse());
		// ����"����"��ť
		JButton jb2 = new JButton("����");
		jb2.addMouseListener(new ChongZhi());
		

		c.add(jl1);
		c.add(jtf1);
		c.add(jl2);
		c.add(jpf1);
		c.add(jb1);
		c.add(jb2);
		// ���ø������λ���Լ���С
		jl1.setBounds(10, 19, 90, 30);
		jtf1.setBounds(60, 20, 210, 30);
		jl2.setBounds(10, 60, 90, 30);
		jpf1.setBounds(60, 60, 210, 30);
		jb1.setBounds(60, 161, 70, 50);
		jb2.setBounds(223, 161, 70, 50);
		
		
		// ���ô����С���رշ�ʽ����������
		setSize(381, 271);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	public void showMe() {
		jf.pack();
		// �ɽ�
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}

	// ע�������
	class mouse extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {

			toMouseCd();

		}

		
	}

	class ChongZhi extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {
		 
			jtf1.setText("");
			jpf1.setText("");


		}

	}

	protected void toMouseCd() {
		String ip = this.jtf1.getText();
		String port = this.jpf1.getText();

			try {

				s = new Socket(ip,Integer.parseInt(port));// �������� ��������
				pw = new PrintWriter(s.getOutputStream());
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		this.dispose();

		ChatJFram jFram = new ChatJFram();

		jFram.init();

	}

	public static void main(String[] args) {

		ChatClientDemo d = new ChatClientDemo();

	}
}
