package ca.mcgill;

import java.util.ArrayList;
import ca.mcgill.Edge;


public class CycleBundle {

   static public ArrayList<Edge> greedCycleFind(ArrayList<Edge> allEdges, int nodes){
        ArrayList<Edge> chosenEdges = new ArrayList<Edge>();
        int ePerNode[] = new int[nodes];


        allEdges.forEach(edge -> {
           int v1 = edge.getV1();
           int v2 = edge.getV2();
            if(ePerNode[v1]<2 && ePerNode[v2]<2){
                chosenEdges.add(edge);
                ePerNode[v1]++;
                ePerNode[v2]++;
            }
        });

        return chosenEdges;
    }

    static public double cycleReliability(ArrayList<Edge> edges){
       double rel = 1.0;
       double edgeRel[] = new double[edges.size()];
       double cutE[] = new double[edges.size()];
       int i=0;

       for(Edge edge : edges){
           rel = rel*edge.getRel();
           edgeRel[i]=edge.getRel();
           i++;
       }
        double finalRel = rel;

        for (int j = 0; j < edgeRel.length; j++) {
            cutE[j]=rel/edgeRel[j];
            cutE[j]=cutE[j]*(1-edgeRel[j]);
            finalRel+=cutE[j];
        }


       return finalRel;
    }


    static public ArrayList<Edge> maximizeRel(ArrayList<Edge> edges, int maxCost, double baseRel){
        int totalCost = 0;
        int i = 0;
        int connections = edges.size();
        double baseEdgeRel[] = new double[connections];
        double finalRel = baseRel;
        for(Edge edge :edges){
            totalCost+=edge.getCost();
            baseEdgeRel[i]=edge.getRel();
            i++;
        }
        i=0;
        while(totalCost<maxCost){
           Edge currEdge = edges.get(i);
           finalRel=finalRel/currEdge.getRel();
           parallelize(currEdge, baseEdgeRel[i]);
           totalCost+=currEdge.getCost();
           i++;
           if(i>=connections){
               i=0;
           }
        }
        return edges;
    }

    private static void parallelize(Edge currEdge, double baseRel) {
        currEdge.setRel(1-((1-currEdge.getRel())*(1-baseRel)));
    }
}
