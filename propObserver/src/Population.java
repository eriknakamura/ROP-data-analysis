/**
 * Created by erikn on 8/9/16.
 */
public class Population {
    public double p_1;
    public double p_2;
    public int n; //population size
    public double OR;
    private static int counter;

    public Population(double startP_1, double startP_2, int startPopulationSize, double startOR){
        p_1 = startP_1;
        p_2 = startP_2;
        n = startPopulationSize;
        OR = startOR;
        counter++;
    }

    public void setP_1(double newVal){
        p_1 = newVal;
    }

    public void setP_2(double newVal){
        p_2 = newVal;
    }

    public void setPopulationSize(int newVal){
        n = newVal;
    }

    public void setOR(int newVal){
        OR = newVal;
    }

    public int getCount(){
        return counter;
    }

    public double getP_1(){
        return p_1;
    }

    public double getP_2(){
        return p_2;
    }

    public int getN(){
        return n;
    }

    public double getOR(){
        return OR;
    }



}
