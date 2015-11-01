package utills;

import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Algorithm extends Thread{

	ConcurrentMap<String, Integer> wordCounterAG = new ConcurrentHashMap<String, Integer>();
	ConcurrentMap<String, Integer> wordCounterHN = new ConcurrentHashMap<String, Integer>();
	ConcurrentMap<String, Integer> wordCounterOU = new ConcurrentHashMap<String, Integer>();
	ConcurrentMap<String, Integer> wordCounterVZ = new ConcurrentHashMap<String, Integer>();
	String text = null;
	
	public Algorithm(ConcurrentMap<String, Integer> wordCounterAG, ConcurrentMap<String, Integer> wordCounterHN,
			ConcurrentMap<String, Integer> wordCounterOU, ConcurrentMap<String, Integer> wordCounterVZ,
						String text){
		this.wordCounterAG = wordCounterAG;
		this.wordCounterHN = wordCounterHN;
		this.wordCounterOU = wordCounterOU;
		this.wordCounterVZ = wordCounterVZ;
		this.text = text;
		this.start();
	}
	
	public void run(){
		
		StringTokenizer st = new StringTokenizer(text, " "); 
		while(st.hasMoreTokens()){
            String word = st.nextToken().toLowerCase();
            Integer num = Character.getNumericValue(word.charAt(0));
            
            if(isBetween(num, 10, 16)){
            	checkWord(wordCounterAG, word);
            }else if(isBetween(num, 17, 23)){
            	checkWord(wordCounterHN, word);
            }else if(isBetween(num, 24, 30)){
            	checkWord(wordCounterOU, word);
            }else if(isBetween(num, 31, 35)){
            	checkWord(wordCounterVZ, word);
            }
        }
		
	}
	
	public void checkWord(ConcurrentMap<String, Integer> wordCounter, String word){
		synchronized(wordCounter){
			if(wordCounter.containsKey(word)){
	        	wordCounter.put(word, wordCounter.get(word)+1);
	        } else {
	        	wordCounter.put(word, 1);
	        }
		}
	}
	
	public static boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}
	
}
