package teamlab;

import static teamlab.GA.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Sample {

    public static void main(String[] args)throws IOException{
    	InitializeGA();

		for(int gen = 1; gen <= MAX_GEN; gen++) {
			int n_gen=(int)((double)POP_SIZE*GEN_GAP);
			for(int j=0; j<n_gen; j++){
				Crossover(Select(Math.random()), Select(Math.random()));
				Mutation(child1); Mutation(child2);
				Statistics();
			}

			System.out.print("Generation " + gen + " Fitness(score) is ");
			for(int i = 0; i < POP_SIZE; i++) System.out.print(fitness[i] + ",");
			System.out.println("");
		}
		/*
    	for(int i = 0; i < 1<<16; i++) {
    		str = randomPatern();
    		System.out.println(str);
    		result = query(BASE_URL  + str);
    		System.out.println(result);

    		 try {
                 Thread.sleep(1000L);
             } catch(InterruptedException e){}

             str = "";
    	}
    	*/
    }

    public static String randomPatern() {
		String str = "";
		for(int i = 0; i < 50; i++) {
			switch((int)(Math.random()*4)) {
			case 0:
				str += "A";
				break;
			case 1:
				str += "B";
				break;
			case 2:
				str += "C";
				break;
			case 3:
				str += "D";
				break;
			}
		}
		return str;
	}

    public static String query(String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
            InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            String res = "";
            String line = null;

            while((line = reader.readLine()) != null){
                res += line;
            }
            return res.trim();
        }
        return "0";
    }
 }
