import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Scanner;

public class TheGUI /*implements ActionListener*/ {
	private JTextArea l;
	private JTextArea timeShow;
	private JTextArea times;
	private JFrame f;
	private JPanel p;
	private JTextField input;
	private boolean beginning = true;
	private boolean atNext = false;
	//private JButton button;
	private FileWriter FR;
	
	public TheGUI() throws Exception {
		FR = new FileWriter("Save data.txt");
		f = new JFrame();
		//button = new JButton("Save your progress to look at later");
		l = new JTextArea("                        ");
		l.setSize(200,200);
		l.setEditable(false);
		times = new JTextArea("                             ");
		times.setEditable(false);
		timeShow = new JTextArea();
		timeShow.setEditable(false);
		input = new JTextField();
		input.setBounds(100, 100, 100, 10);
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					atNext = true;
					System.out.println("atNext activated");
				}
			}
		);
		
		p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		p.setLayout(new GridLayout(0,1));
		p.add(l);
		p.add(timeShow);
		p.add(input);
		p.add(times);
		//p.add(button);
		
		f.add(p, BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Task Timer");
		f.pack();
//		l.setSize(300,400);
		f.setVisible(true);
	}
	
	public void changeLabel(String s) {
		l.setText(s);
	}
	public void changeTime(String s) {
		times.setText(s);
	}
	public void setTimesToDo(String s) {
		timeShow.setText(s);
	}
	public String take() throws InterruptedException {
		while(!atNext) {
			Thread.sleep(1);
			if(atNext) {
				break;
			}
		}
		atNext = false;
		String temp = input.getText();
		input.setText("");
		System.out.println("Successful take");
		return temp;
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		try {
//			FR.write(Formatter.getTimes());
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
}
