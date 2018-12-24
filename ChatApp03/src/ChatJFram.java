
//������2.0 �����Ҵ���

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatJFram extends JFrame {
	private JFrame jf;// ����
	private Container c;
	private JTextArea jta;// ����������ʾ�ı���
	private JTextField jtf;// �Լ��������ݵ��ı���
	private JButton jb;// ���Ͱ�ť
	private String name;// �û���
	private JLabel label;// ���촰���е��û���
	private BufferedReader br;
	private JTextField jtf1;// �����û����봰
	private JTextField jpf1;// �����˿����봰
	private PrintWriter pw;// �������д��������

	private Socket s;// ʵ�ֿͻ��������˵�����

	private Set<Socket> allSockets;
	private JButton jb1;// ���Ͱ�ť
	

	// public ChatClientDemo ccd;

	public ChatJFram() {
		
		
		do {

			try {
		
//				
				s = new Socket("127.0.0.1",7777);// �������� ��������
				pw = new PrintWriter(s.getOutputStream());
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				allSockets = new HashSet<Socket>();// �Լ��Ͻ��г�ʼ��
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (s == null);

		jf = new JFrame("�����ҿͻ���v2.0");
		jta = new JTextArea(20, 40);

		// ����������������
		jta.setFont(new Font("����", Font.BOLD, 24));
		// �ı��򲻿ɱ༭
		jta.setEditable(false);
		jtf = new JTextField(28);
		jtf.addActionListener(new GlaoTf());
		jb = new JButton("����");
		jb.addMouseListener(new fasong());
		jb1=new JButton("���ļ�");
		jb1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FileDemo fileDemo=new FileDemo();
			}
		} );
		
	name=JOptionPane.showInputDialog(jf,"�������������ƣ�");
 		label=new JLabel(name+":");

		init();
		new MessageThread().start();
		
	}

	public void init() {

		// �ɹ������
		JScrollPane jsp = new JScrollPane(jta);
		// ���ַ�ʽ
		jf.add(jsp, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(jtf);
		panel.add(jb);
		panel.add(jb1);
		jf.setVisible(true);
		jf.setSize(705, 500);
		jf.add(panel, BorderLayout.SOUTH);

	}

	private void showMe() {
		// ���ô����С

		jf.pack();
		// �ɽ�
		jf.setVisible(true);

		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}

	// Ϊ�����ҷ��Ͱ�ť���һ��������
	class fasong extends MouseAdapter {

		private String mesg;

		@Override
		public void mouseClicked(MouseEvent e) {

			Writeable();

		

		}
		

		public void Writeable() {

			if (jtf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(jf, "���ܷ��Ϳ���Ϣ");
				return;
			}
			pw.println(name + ":" + jtf.getText());//
			pw.flush();
			jtf.setText("");// ��������

		}

		

	}

	// ������������һ���¼�����
	class GlaoTf implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			jtf.addActionListener(this);

		}

	}
	
	
	
	class MessageThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					String str = br.readLine();
					// ��ʾ���������ı���
					jta.append(str + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) {
		ChatJFram jFram = new ChatJFram();
		jFram.init();

	}


}