package ca.mcgill;

public class Edge implements Comparable<Edge> {
     int v1,v2,cost, id;
     double rel;

    Edge(int v1, int v2, int cost, double rel, int id)
    {
        this.v1=v1;
        this.v2=v2;
        this.cost=cost;
        this.rel =rel;
        this.id=id
    }

    @Override
    public int compareTo(Edge o) {
        Edge e1 = (Edge)o;
        if(e1.rel==this.rel)
            return 0;
        return e1.rel > this.rel ? 1 : -1;
    }

    @Override
    public String toString()
    {
        return String.format("Vertex1:%d \t Vertex2:%d \t Cost:%d \t Reliability:%f \n", v1,v2,cost,rel);
    }

    public int getV1() {
        return v1;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public int getV2() {
        return v2;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getRel() {
        return rel;
    }

    public void setRel(double rel) {
        this.rel = rel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
