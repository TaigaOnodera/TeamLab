package teamlab;
import static teamlab.Sample.*;

import java.io.IOException;

public class GA {
  public final static int POP_SIZE = 32;
  public final static int MAX_GEN = 100;
  public final static int LEN_CHROM = 50;
  public final static int OUTPUT_NUM = 3;
  public final static double GEN_GAP = 0.9;
  public final static double P_MUTATION = 0.1;
  public final static int MAX_RAND = Integer.MAX_VALUE;
  public static final String TOKEN = "6npJyNxZNIr9cHoR9oHSeUE4wJDARTAe";
  public static final String BASE  = "https://runner.team-lab.com/";
  public static final String BASE_URL = BASE + "/q?token=" + TOKEN + "&str=";

  public static int[][] chrom = new int[POP_SIZE][LEN_CHROM];
  public static int[] fitness = new int[POP_SIZE];
  public static int max, min, sumfitness;
  public static int n_max, n_min;
  public static int child1, child2;

  public static int ObjFunc(int pop) {
    String str = "";
    int result = 0;
    for(int i = 0; i < LEN_CHROM; i++) {
      switch(chrom[pop][i]) {
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

    System.out.println(str);
    
    try {
      result = Integer.parseInt(query(BASE_URL  + str));
    } catch (NumberFormatException | IOException e1) {
      e1.printStackTrace();
    }

    System.out.println(result);
    
    try {
      Thread.sleep(1000L);
    } catch(InterruptedException e){}
    
    return result;
  }

  public static void InitializeGA() {
    for(int i=0;i<POP_SIZE; i++){
      for(int j=0;j<LEN_CHROM;j++)
        chrom[i][j] = (int)( Math.random() * OUTPUT_NUM );

      fitness[i] = ObjFunc(i);
    }
    Statistics();
  }

  public static int Select(double standard) {
    int sum = 0, idx = 0;
    for(idx = 0; idx < POP_SIZE; idx++){
      sum += fitness[idx];
      if((double)sum/(double)sumfitness >= standard) break;
    }
    
    return idx;
  }

  public static void Crossover(int parent1, int parent2) {
    child1 = n_min;

  int min2 = POP_SIZE;
    for(int i=0; i<POP_SIZE; i++){
      if(i != child1){
        if(min <= fitness[i] && fitness[i] < min2){
          min2 = fitness[i];
          child2 = i;
        }
      }
    }

    for(int j = 0; j < LEN_CHROM; j++) {
      if( Math.random() < 0.5) {
        chrom[child1][j] = chrom[parent2][j];
        chrom[child2][j] = chrom[parent1][j];
      } else {
        chrom[child1][j] = chrom[parent1][j];
        chrom[child2][j] = chrom[parent2][j];
      }
    }
    
    fitness[child1] = ObjFunc(child1);
    fitness[child2] = ObjFunc(child2);
  }

  public static void Mutation(int child) {
    if(Math.random()<P_MUTATION){
      int n_mutate = (int)((Math.random()*MAX_RAND)) % LEN_CHROM;
      chrom[child][n_mutate] = (int) ((chrom[child][n_mutate] + 1) % OUTPUT_NUM);
      fitness[child] = ObjFunc(child);
    }
  }

  public static void Statistics() {
    max = 0; min = Integer.MAX_VALUE;
    sumfitness = 0;

    for(int i=0; i<POP_SIZE; i++){
      if(fitness[i] > max) {
        max = fitness[i];
        n_max = i;
      }

      if(fitness[i] < min){
        min = fitness[i];
        n_min = i;
      }
      
      sumfitness += fitness[i];
    }
  }
}
