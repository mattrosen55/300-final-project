import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class DealsApp {
	private JFrame frame = new JFrame("Deals");
	private ArrayList<String> keywords = new ArrayList<String>();

	public DealsApp(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200,100,500,300);
		Container container = frame.getContentPane();
		container.setLayout(null);
		
		JLabel label = new JLabel("Enter your desired items:");
		label.setBounds(60,5,250,30);
		JTextField keywordInput = new JTextField();
		keywordInput.setBounds(65,30,250,30);
		JButton addButton = new JButton("Add");
		addButton.setBounds(325, 30, 100, 30);
		JTextPane keywordsText = new JTextPane();
		keywordsText.setEditable(false);
		keywordsText.setBounds(65, 70, 250, 100);
		JButton startButton = new JButton("Start");
		startButton.setBounds(200, 175, 100, 30);
		container.add(label);
		container.add(keywordInput);
		
		addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				keywordsText.setText(keywordsText.getText()+ keywordInput.getText()+"\n");
				keywords.add(keywordInput.getText());
				keywordInput.setText("");
			}
			
			
		});
		
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Timer timer = new Timer ();
				TimerTask hourlyTask = new TimerTask () {
				    @Override
				    public void run () {
				    	Document doc;
						try {
							for (String keyword: keywords){
							doc = Jsoup.connect("https://dealsea.com/search?n=0&q="+keyword).get();
							doc.append(Jsoup.connect("https://dealsea.com/search?n=10&q="+keyword).get().html());
					    	doc.append(Jsoup.connect("https://dealsea.com/search?n=20&q="+keyword).get().html());
					    	doc.append(Jsoup.connect("https://dealsea.com/search?n=30&q="+keyword).get().html());
					    	doc.append(Jsoup.connect("https://dealsea.com/search?n=40&q="+keyword).get().html());
					    	ArrayList<Element> elements = doc.getElementsByClass("dealbox");
					    	for (int i = 0; i<elements.size(); i++){
					    		if(elements.get(i).getElementsByClass("colr_red xxsmall").isEmpty())
					    			System.out.println(elements.get(i).getElementsByTag("strong").get(0).text());
					    	} 
							}
					    	
					    
					    
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				    	
				    }
				};

				
				timer.schedule (hourlyTask, 0l, 1000*10); 
				frame.dispose();
				
			}
			
		});
		container.add(addButton);
		container.add(keywordsText);
		container.add(startButton);
		frame.setVisible(true);
		 
	}

	public static void main(String[] args) throws IOException {
	DealsApp app = new DealsApp();
	
	

}
}
