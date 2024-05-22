/****************************************************
Name:			Brandon Evans
Problem Set:	Problem Set 2: Naive Bayes Algorithm
Due Date:		July 10, 2023
****************************************************/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.ArrayList;

public class UASimpleClassifier {
	
	HashMap<String, Integer> lexicon = new HashMap<String, Integer>();
	
	HashMap<String, Integer> germanyX0Y0 = new HashMap<String, Integer>();
	HashMap<String, Integer> germanyX1Y0 = new HashMap<String, Integer>();
	HashMap<String, Integer> germanyX0Y1 = new HashMap<String, Integer>();
	HashMap<String, Integer> germanyX1Y1 = new HashMap<String, Integer>();
	
	HashMap<String, Integer> franceX0Y0 = new HashMap<String, Integer>();
	HashMap<String, Integer> franceX1Y0 = new HashMap<String, Integer>();
	HashMap<String, Integer> franceX0Y1 = new HashMap<String, Integer>();
	HashMap<String, Integer> franceX1Y1 = new HashMap<String, Integer>();
	
	HashMap<String, Integer> spainX0Y0 = new HashMap<String, Integer>();
	HashMap<String, Integer> spainX1Y0 = new HashMap<String, Integer>();
	HashMap<String, Integer> spainX0Y1 = new HashMap<String, Integer>();
	HashMap<String, Integer> spainX1Y1 = new HashMap<String, Integer>();
	
	public int getTotal(HashMap<String, Integer> ht) {
		int total = 0;
		for(int i = 0; i<ht.size(); i++) {
			total++;
		}
		return total;
	}
	
	public void train(String filename) throws IOException {

			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			br.readLine(); //skip first line
			ArrayList<String> lines = new ArrayList<>();
			
				while ((line = br.readLine()) != null) {
					
				lines.add(line);
				
				int germanyX0Y0count = 1;
				int germanyX0Y1count = 1;
				int germanyX1Y0count = 1;
				int germanyX1Y1count = 1;
				
				int franceX0Y0count = 1;
				int franceX0Y1count = 1;
				int franceX1Y0count = 1;
				int franceX1Y1count = 1;
				
				int spainX0Y0count = 1;
				int spainX0Y1count = 1;
				int spainX1Y0count = 1;
				int spainX1Y1count = 1;
				
				int total = 0;
				for(String input: lines) {
					total++;
					String[] features = line.split(",");
					
					lexicon.put(line, total);
					
					if(features[0].contains("Germany")) {
						if(features[1].contains("0") ) {
							if(features[2].contains("0")) {
								germanyX0Y0.put(line, germanyX0Y0count);
								germanyX0Y0count++;
							} else if(features[2].contains("1")) {
								germanyX0Y1.put(line, germanyX0Y1count);
								germanyX0Y1count++;
							}
						if(features[1].contains("1")) {
							if(features[2].contains("0")) {
								germanyX1Y0.put(line, germanyX1Y0count);
								germanyX1Y0count++;
							} else if(features[2].contains("1")) {
								germanyX1Y1.put(line, germanyX1Y1count);
								germanyX1Y1count++;
							}
						}
					}
						
				}
				
					if(features[0].contains("France")) {
						if(features[1].contains("0") ) {
							if(features[2].contains("0")) {
								franceX0Y0.put(line, franceX0Y0count);
								franceX0Y0count++;
							} else if(features[2].contains("1")) {
								franceX0Y1.put(line, franceX0Y1count);
								franceX0Y1count++;
							}
						if(features[1].contains("1")) {
							if(features[2].contains("0")) {
								franceX1Y0.put(line, franceX1Y0count);
								franceX1Y0count++;
							} else if(features[2].contains("1")) {
								franceX1Y1.put(line, franceX1Y1count);
								franceX1Y1count++;
							}
						}
					}
						
				}
					
					if(features[0].contains("Spain")) {
						if(features[1].contains("0") ) {
							if(features[2].contains("0")) {
								spainX0Y0.put(line, spainX0Y0count);
								spainX0Y0count++;
							} else if(features[2].contains("1")) {
								spainX0Y1.put(line, spainX0Y1count);
								spainX0Y1count++;
							}
						if(features[1].contains("1")) {
							if(features[2].contains("0")) {
								spainX1Y0.put(line, spainX1Y0count);
								spainX1Y0count++;
							} else if(features[2].contains("1")) {
								spainX1Y1.put(line, spainX1Y1count);
								spainX1Y1count++;
							}
						}
					}
						
				}
			}

		
	br.close();
		
		System.out.println(
	            "Training Phase:  "+filename+"\n"+
	            "--------------------------------------------------------------\n"+
	            "     => Number of Entries (n):             "+total+"\n"+
	            "     => Number of Features (p):            "+5+"\n"+
	            "     => Number of Distinct Classes (y):    "+3+"\n"
	        );
	}
}
	
	public void test(String filename) throws IOException {
		
		System.out.println(
	            "Testing Phase:\n"+
	            "--------------------------------------------------------------\n"
	        );
	        System.out.printf(
	            "     %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
	            "F1","F2","F3","F4","F5","CLASS","PREDICT","RESULT"
	        );
	        System.out.printf(
	            "     %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
	            "---------","---------","---------","---------","---------",
	            "---------","---------","---------"
	        );
	        
	        
	       
	}
	
	public void predict(String filename) {
        System.out.println(
            "Prediction Phase:\n"+
            "--------------------------------------------------------------\n"
        );
        System.out.printf(
            "     %-10s %-10s %-10s %-10s %-10s %-10s\n",
            "F1","F2","F3","F4","F5","PREDICT"
        );
        System.out.printf(
            "     %-10s %-10s %-10s %-10s %-10s %-10s\n",
            "---------","---------","---------","---------","---------",
            "---------"
        );

    }
	
	public String classify(String f1, String f2, String f3, double f4, double f5) {
		double prob = 0;
		double greaterProb = 0;
		String className = "";
		int count = 0;
		
		for(int i = 0; i<lexicon.size(); i++) {
		prob = (count/lexicon.size()) * (lexicon.get(f1)/getTotal(lexicon)) * (lexicon.get(f2)/getTotal(lexicon)) * 
				(lexicon.get(f3)/getTotal(lexicon)) * pdf(f4,0,0) * pdf(f5,0,0);
		
		if (prob > greaterProb) {
			greaterProb = prob;
			className = lexicon.toString();
		 }
		}
		return className;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		
		UASimpleClassifier a = new UASimpleClassifier();
		
		a.train(args[0]);
		a.test(args[1]);
		a.predict(args[2]);
		

	}
	
	 public static double pdf(double x, double sd, double mean) {
		 double pdf = (1 / (sd*Math.sqrt(2*Math.PI)));
	        pdf *= Math.pow(Math.E, -0.5*(Math.pow(((x-mean)/sd),2)));
	        return pdf;
	    }
}

