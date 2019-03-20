package ca.mcgill;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {




    public static void main(String[] args) {
        int maxCost = Integer.MAX_VALUE;
        double minRel = 0.8;
        double totalRel = 1.0;
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
        ArrayList<Edge> spanEdges = kruskal(edges,nodes);
        spanEdges.sort(Comparator.comparing(edge -> edge.getV1()));
        System.out.println("\nThe maximum spanning tree of reliability:");
        double spanRel = 1.0;
        for (Edge edge : spanEdges) {
            edges.remove(edge);
            spanRel= edge.getRel()*spanRel;
            System.out.println(edge);
        }
        totalRel = spanRel;
        System.out.println("The MaxST reliability is: "+spanRel);

    }

    private static ArrayList<Edge> kruskal(ArrayList<Edge> inputEdges, int nodes) {
        ArrayList<Edge> span = new ArrayList<Edge>();
        TreeTraversal traversal = new TreeTraversal(nodes);

        for(Edge edge : inputEdges) {
            int v1 = edge.getV1();
            int v2 = edge.getV2();
           if (traversal.connected(v1, v2)){
                continue;
            }

            traversal.union(v1,v2);
            span.add(edge);

            if (traversal.size(0) == nodes) break;
        }

        if (traversal.size(0) != nodes) return null;

        return span;
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



