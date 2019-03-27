package ca.mcgill;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

public class Network {




    public static void main(String[] args) {
        int maxCost = 130;
     
        String[] inputLines = new String[0];
        int nodes;
        double[] rMatrix;
        int[] costMatrix;
            try(Stream<String> stream = Files.lines(Paths.get("input.txt"))) {
                  inputLines = stream
                            .filter(line -> !line.startsWith("#"))
                            .toArray(String[]::new);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        nodes =Integer.parseInt(inputLines[0]);
        rMatrix= Arrays.stream(inputLines[1].split(" "))
                       .mapToDouble(Double::parseDouble)
                        .toArray();
        costMatrix= Arrays.stream(inputLines[2].split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println("Number of nodes: "+ nodes);
        System.out.print("Reliability Matrix in Row-Major Form: ");
        Arrays.stream(rMatrix).forEach(item->System.out.print(item+ " "));
        System.out.print("\nCost Matrix in Row-Major Form: ");
        Arrays.stream(costMatrix).forEach(item->System.out.print(item+ " "));
        ArrayList<Edge> edges = createEdges(nodes,rMatrix,costMatrix);
        Collections.sort(edges);
        edges = CycleBundle.greedCycleFind(edges, nodes);
        double rel = CycleBundle.cycleReliability(edges);
        System.out.println("\nCycle Rel is: "+rel);
        edges = CycleBundle.maximizeRel(edges,maxCost,rel);
        rel= CycleBundle.cycleReliability(edges);
        System.out.println("Max Cycle Rel with cost of " +maxCost+" is: "+rel);


    }

    private static ArrayList<Edge> createEdges(int nodes, double[] rMatrix, int[] costMatrix){
            ArrayList<Edge> edges;
            int matrixIndex =0;
            edges = new ArrayList<Edge>();
            for(int i=0;i<nodes-1;i++){
                for(int j=i+1;j<nodes;j++) {
                    if(i!=j) {
                        edges.add(new Edge(i, j, costMatrix[matrixIndex], rMatrix[matrixIndex]));
                        matrixIndex++;
                    }
                }
            }
        return edges;
    }




}



