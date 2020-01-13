package prim;
import graph.Graph;
import graph.Entry;
import java.io.*;
import java.util.*;

/**
 *@author Greundzo
 *@author ShyGuy
 */
public class PrimUsage
{
  public static void printInfo(Graph<String,Double> graph)
  {
    System.out.println("Vertices: "+ graph.getVertices().size());
    System.out.println("Edges: "+ graph.totalEdges()/2);
    System.out.println("Weight: "+ (getWeight(graph)/2)/1000 +" Km\n ");
  }

  /**
   * Returns Graph weight
   */
  public static double getWeight(Graph<String,Double> graph)
  {
  	double weight = 0;
   	Collection<String> vertices = graph.getVertices();
   	for (String vertex1 : vertices)
  	{
   		for (String vertex2 : graph.getNeighbors(vertex1))
  	 		weight += graph.getEdgeWeight(vertex1, vertex2);
  	}
  		return weight;
  }

/**
 *@param graph: graph to use Prim
 *@return: ArrayList of minimum graphs founded by prim
 */
  public static Graph<String, Double> primAlg(Graph<String,Double> graph)
    throws IllegalAccessException
  {
		ArrayList<String> vertices = graph.getVertices();
		Graph<String, Double> min = new Graph<>(true);
		String start = "abadia a isola";
    min = primAlgorithm(graph, vertices, start);
		return min;
	}

/**
 *@param graph: graph where find minimum graph
 *@param vertices: list of graph vertices
 *@param start: start vertex of primAlgorithm
 *@return: minimum graph
 */
  public static Graph<String,Double> primAlgorithm (Graph<String,Double> graph, ArrayList<String> vertices, String start)
    throws IllegalAccessException
  {
    Graph<String,Double> min = new Graph<>(true);
    PriorityQueue<Double,String> queue = new PriorityQueue<>("min");
    HashMap<String,String> relation = new HashMap<>();

    for(String v: vertices)
    {
      double key = 0;
      if(v == start)
        key = 0;
      else
        key = Double.MAX_VALUE;

      relation.put(v,null);
      queue.insert(key,v);
    }

    while(!queue.empty())
    {
      double currentKey = queue.peek();
      String current = queue.extractFirst();
      min.addVertex(current);
      ArrayList<String> neighbors = graph.getNeighbors(current);

      for(String neighbor : neighbors)
      {
        if(!min.hasVertex(neighbor) && queue.contains(neighbor)
         && graph.getEdgeWeight(current,neighbor) < queue.getKey(queue.searchElem(neighbor)))
        {
          queue.modifyKey(graph.getEdgeWeight(neighbor,current), neighbor);
          relation.put(neighbor,current);
        }
      }

      String currFather = relation.get(current);
      if(currFather!=null)
        min.addUndirectEdge(current, currFather, currentKey);
    }
    return min;
  }

  public static void main(String[] args) throws IllegalAccessException,IOException
  {
    if(args.length < 1)
      throw new IOException("Usage: PrimUsage <filepath>");
    Graph<String, Double> graph = new Graph<>(true);
    try
    {
      FileReader reader = new FileReader(args[0]);
      BufferedReader bf = new BufferedReader(reader);

      String line;
      boolean endOfFile = false;
      while(true)
      {
        line = bf.readLine();
        endOfFile = (line == null);
        if (endOfFile)
          break;
        String[] values = line.split(",");
        if (values.length == 3)
        {
          Double distance = Double.parseDouble(values[2]);
          String v1 = values[0];
          String v2 = values[1];

          if(!graph.hasVertex(v1))
            graph.addVertex(v1);
          if(!graph.hasVertex(v2))
            graph.addVertex(v2);
          graph.addUndirectEdge(v1, v2, distance);
        }
      }
      bf.close();
      reader.close();
    }
    catch(IOException ex)
    {
        System.out.println("Error: " + ex.getMessage());
    }

    Graph<String, Double> minForest = primAlg(graph);
    System.out.println("ORIGINAL GRAPH: \n");
    printInfo(graph);

    double mainWeigth = 0;
    int mainEdges = 0;
    int mainVertices = 0;

    System.out.println("MINIMUM GRAPH: \n");
    mainWeigth = (getWeight(minForest)/2)/1000;
    mainEdges = minForest.totalEdges()/2;
    mainVertices = minForest.getVertices().size();

    System.out.println("Vertices: "+ mainVertices);
    System.out.println("Edges: "+ mainEdges + " (expected: 18637)");
    System.out.println("Weight: "+ mainWeigth + " Km (expected: 89939,913)");

  }

}
