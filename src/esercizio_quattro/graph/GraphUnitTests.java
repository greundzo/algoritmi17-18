package graph;

import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import java.util.ArrayList;
import org.junit.Test;
import java.util.Collection;
import prim.*;

/**
 *@author ShyGuy
 */

public class GraphUnitTests
{

  private String v1, v2, v3, v4, v5;
  Graph<String,Double> graph;

  @Before
  public void create()
  {
    v1 = new String ("Roma");
  	v2 = new String ("Torino");
  	v3 = new String ("Firenze");
  	v4 = new String ("Napoli");
  	v5 = new String ("Venezia");
	  graph = new Graph<>(true);
  }

  @Test
  public void addVertexHasVertex()
  {
	  graph.addVertex(v3);
	  assertTrue(graph.hasVertex(v3));
	  assertFalse(graph.hasVertex(v1));
  }

  @Test
  public void addEdgeHasEdge()
  {
	  graph.addVertex(v1);
	  graph.addVertex(v2);
	  graph.addVertex(v3);
	  graph.addUndirectEdge(v1,v3,50.24);
	  assertTrue(graph.hasEdge(v1,v3));
	  assertFalse(graph.hasEdge(v1,v2));
  }

  @Test
  public void getWeight()
  {
	  graph.addVertex(v2);
	  graph.addVertex(v3);
	  graph.addVertex(v4);
	  graph.addVertex(v1);
	  graph.addUndirectEdge(v1,v3,50.24);
	  graph.addUndirectEdge(v1,v4,10.24);
	  graph.addUndirectEdge(v2,v4,510.24);
	  assertTrue(50.24 == graph.getEdgeWeight(v1,v3));
	  assertFalse(50.24 == graph.getEdgeWeight(v1,v4));
  }

  @Test
  public void getNeighborsTest()
  {
    graph.addVertex(v1);
    graph.addVertex(v3);
    graph.addVertex(v4);
    graph.addVertex(v2);
    graph.addVertex(v5);
    graph.addDirectEdge(v1,v3,50.24);
    graph.addDirectEdge(v1,v2,10.24);
    graph.addDirectEdge(v1,v5,510.24);
    graph.addDirectEdge(v1,v4,510.24);
    ArrayList<String> adjexp = new ArrayList<>();
    adjexp.add(v5);
    adjexp.add(v3);
    adjexp.add(v4);
    adjexp.add(v2);
    ArrayList<String> adjact = new ArrayList<>();
    adjact = graph.getNeighbors(v1);
    assertEquals(adjexp, adjact);
  }

 @Test
 public void totalEdgesTest()
 {
   graph.addVertex(v1);
   graph.addVertex(v3);
   graph.addVertex(v4);
   graph.addVertex(v2);
   graph.addDirectEdge(v1,v3,50.24);
   graph.addDirectEdge(v1,v4,10.24);
   graph.addDirectEdge(v1,v2,510.24);
   assertTrue(3 == graph.totalEdges());
 }

 @Test
 public void getVerticesTest()
 {
   graph.addVertex(v1);
   graph.addVertex(v3);
   graph.addVertex(v4);
   graph.addVertex(v2);
   ArrayList<String> exp = new ArrayList<>();
   exp.add(v1);
   exp.add(v3);
   exp.add(v4);
   exp.add(v2);
   assertEquals(exp, graph.getVertices());
 }
}
