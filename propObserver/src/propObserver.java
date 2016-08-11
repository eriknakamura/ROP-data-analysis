import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
 * Created by erikn on 8/9/16.
 */
public class propObserver {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String fileDirectory = "//";
        boolean goodFile = false;
        while(goodFile == false) {
            System.out.println("Please enter the directory of the file you wish you edit (must be .txt file, line delineated)");
            fileDirectory = scan.nextLine();
            File readFile = new File(fileDirectory);
            if (readFile.exists() && !readFile.isDirectory()) {
                System.out.println("File found. Press enter to parse and begin simulations");

                if((scan.nextLine()).equals("")){
                    goodFile = true;
                }
                else{
                    goodFile = false;
                }
            } else {
                System.out.println("File not found, please make sure it is entered properly!");
                goodFile = false;
            }
        }
        HashMap<String,Population> populationMap = new HashMap<>();
        while(goodFile){
            File readFile = new File(fileDirectory);
            populationMap = parseFile(readFile);
            int popCount = populationMap.size();
            System.out.println("File read successfully with " + popCount + " new population creations.");
            goodFile = false;
        }

        System.out.println("There are now " + populationMap.size() + " samples created:" );
        for(int i=0;i<populationMap.size();i++){
            System.out.println("Population hash ID: " + "population" + i);
            System.out.println("Size of population: " +  populationMap.get("population" + i).getN());
            System.out.println("p_1: " + populationMap.get("population" + i).getP_1());
            System.out.println("p_2: " + populationMap.get("population" + i).getP_2());
            System.out.println("Multi representation for population: ");
            int[][] operMulti = multiGen(populationMap.get("population" + i).getP_1(),populationMap.get("population" + i).getP_2(),populationMap.get("population" + i).getN());
            printMulti(operMulti);
            //only print newN if you need to confirm that the multi is rounding properly
            int newN = 0;
            for (int k = 0; k < operMulti.length; k++) {
                for (int j = 0; j < operMulti[k].length; j++) {
                    newN += operMulti[k][j];
                }
            }
            int [][] ORMulti = implementOR(populationMap.get("population" + i).getP_1(),populationMap.get("population" + i).getP_2(),populationMap.get("population" + i).getN(),populationMap.get("population" + i).getOR());
            System.out.println("Multi with OR applied: ");
            printMulti(ORMulti);
        }

    }

    public static int[][] multiGen(double p_1, double p_2 , int n) {
        //increments will determine how many multis are made. They must be powers of 10 bc -> increment value 10 will makes squares with p_1 and p_2 incrementing by .1. 100 by .01 etc....
        int[][] multi = new int[2][2];
        multi[0][0] = (int) Math.round(p_1*p_2*n);
        multi[1][0] = (int) Math.round((p_2*n) - multi[0][0]);
        multi[0][1] = (int) Math.round((p_1*n) - multi[0][0]);
        multi[1][1] = (n - (multi[1][0] + multi[0][0] + multi[0][1])
        );
        return multi;
    }

    public static int[][] implementOR(double p_1, double p_2 , int n, double OR){
        int[][] ORmulti = new int[2][2];
        double intermediateORCalc = Math.sqrt(Math.pow(((p_1+p_2) * (OR - 1)) + 1,2) + (4*OR*(1-OR)*p_1*p_2));
        //only print for debug reasons...
        //System.out.println("Inter Calc = " + intermediateORCalc);
        ORmulti[0][0] = (int) Math.round(n * ((1 + (p_1+p_2) * (OR - 1) - intermediateORCalc)/(2*(OR-1))));
        ORmulti[1][0] = (int) Math.round((p_2*n) - ORmulti[0][0]);
        ORmulti[0][1] = (int) Math.round((p_1*n) - ORmulti[0][0]);
        ORmulti[1][1] = (n - (ORmulti[1][0] + ORmulti[0][0] + ORmulti[0][1]));

        return ORmulti;

    }

    public static void printMulti(int[][] multi){

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.FLOOR);

        for (int k = 0; k < multi.length; k++) {
            for (int j = 0; j < multi[k].length; j++) {
                System.out.print((multi[k][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    public static HashMap<String,Population> parseFile(File file) {
        int popCount = 0;
        HashMap<String,Population> populationMap = new HashMap<>();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] comps = line.split("\\,");
                if(comps.length >= 4) {
                    String p_1String = comps[0].substring(comps[0].indexOf(":") + 1);
                    String p_2String = comps[1].substring(comps[1].indexOf(":") + 1);
                    String nString = comps[2].substring(comps[2].indexOf(":") + 1);
                    String ORString = comps[3].substring(comps[3].indexOf(":") + 1);


                    double p_1Double = Double.parseDouble(p_1String);
                    double p_2Double = Double.parseDouble(p_2String);
                    int nInt = Integer.parseInt(nString);
                    double ORDouble = Double.parseDouble(ORString);

                    //now add parsed out variables into an object and hashmap...
                    populationMap.put("population" + popCount, new Population(p_1Double,p_2Double,nInt, ORDouble));
                }
                popCount++;

            }

        } catch (IOException e) {
            System.out.println("File Parse Failure");
            e.printStackTrace();
        }

        return populationMap;
    }

/*
    public static double[][] modP(double p_1, double p_2){

    }

     System.out.println("Please enter the following values:");
        Scanner scan = new Scanner(System.in);
        System.out.print("p_1: ");
        String p_1String = scan.nextLine();
        System.out.print("p_2: ");
        String p_2String = scan.nextLine();
        System.out.print("n: ");
        String nString = scan.nextLine();
        System.out.println("Raw multi has the following appearance: ");
    */
}
