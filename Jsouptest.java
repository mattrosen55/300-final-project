import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class Jsouptest {

	public static void main(String[] args) throws IOException {
	Scanner scanner = new Scanner(System.in);
	String keyword = scanner.nextLine();
	Document doc = Jsoup.connect("https://dealsea.com/search?search_mode=Deals&q="+keyword).get(); 
	ArrayList<Element> elements = doc.getElementsByClass("dealbox");
	for (int i = 0; i<elements.size(); i++){
		System.out.println(elements.get(i).getElementsByTag("strong").get(0).text());
	} 
	}

}
