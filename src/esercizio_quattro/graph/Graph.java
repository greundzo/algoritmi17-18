package graph;
import java.util.*; //HashMap, Collection

/**
 *@author ShyGuy
 *@author Greundzo
 */
public class Graph<T,E>
{

	private HashMap<T,HashMap<T,E>> vertexList;
	private ArrayList<T> nodes;
	boolean undirected;

/**
 * Constructor
 */
	public Graph(boolean undirected)
	{
		this.undirected = undirected;
		this.nodes = new ArrayList<>();
		this.vertexList = new HashMap<>();
	}

/**
 *@param vertex: the vertex to add into the graph;
 *@return: true if i successfull
 */
	public boolean addVertex(T vertex)
  {
    if(this.hasVertex(vertex))
      return false;
		nodes.add(vertex);
    vertexList.put(vertex,new HashMap<>());
    return true;
  }

/**
 *@param vertex1: source vertex
 *@param vertex2: destination vertex
 *@param weight: edge weight
 *@return: true if successfull
 */
  public boolean addDirectEdge(T vertex1, T vertex2, E weight)
  {
    if(this.hasEdge(vertex1, vertex2))
      return false;
    vertexList.get(vertex1).put(vertex2, weight);
    return true;
  }

/** Method addDirectEdge is called two times because Graph is undirected
 *@param vertex1: first vertex
 *@param vertex2: second vertex
 *@return: true is successfull
 */
  public boolean addUndirectEdge(T vertex1, T vertex2, E weight)
  {
		if(!this.undirected)
			return false;
		else
			return this.addDirectEdge(vertex1, vertex2, weight) && this.addDirectEdge(vertex2, vertex1, weight);
  }

/**
 *@param vertex: vertex I want to know if it's in the graph
 *@return: true if found
 */
  public boolean hasVertex(T vertex)
  {
    return vertexList.containsKey(vertex);
  }

/**
 *@param vertex1: first vertex
 *@param vertex2: second vertex
 *@return: true if found
 */
  public boolean hasEdge(T vertex1, T vertex2)
  {
    return this.hasVertex(vertex1) &&
         	 this.hasVertex(vertex2) &&
         	 vertexList.get(vertex1).containsKey(vertex2);
  }

/**
 *@param vertex1: first vertex
 *@param vertex2: second vertex
 *@return: edge weight if found
 */
  public E getEdgeWeight(T vertex1, T vertex2)
  {
  	if(!this.hasEdge(vertex1, vertex2))
      return null;
    return vertexList.get(vertex1).get(vertex2);
  }

/**
 *@return: ArrayList of Vertices, corrispondent to all vertices in the Graph
 */
  public ArrayList<T> getVertices()
  {
		return this.nodes;
  }

/**
 *@param vertex: source vertex
 *@return: all the adjiacents vertices
 */
  public ArrayList<T> getNeighbors(T vertex)
  {
		ArrayList<T> adj = new ArrayList<>();
		if((!vertexList.containsKey(vertex)) || vertexList.get(vertex).isEmpty())
			return adj;
		Set<T> c = vertexList.get(vertex).keySet();
		for(T v: c)
			adj.add(v);
		return adj;
  }

/**
 *@return: total edges in the Graph
 */
	public int totalEdges()
	{
		int edges = 0;
		for(T vertex : this.getVertices())
		{
			for(T vertex2 : this.getNeighbors(vertex))
				edges++;
		}
		return edges;
	}
}
