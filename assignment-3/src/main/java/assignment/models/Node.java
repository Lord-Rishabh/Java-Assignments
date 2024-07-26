package assignment.models;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a node in a graph with its attributes and relationships.
 */
@Getter
@Setter
public class Node {

  private String id;
  private String name;
  private Map<String, String> additionalInfo;
  private Set<Node> parents;
  private Set<Node> children;

  /**
   * Constructs a Node with the specified ID and name.
   * @param nodeId   the ID of the node
   * @param nodeName the name of the node
   */
  public Node(String nodeId, String nodeName) {
    this.id = nodeId;
    this.name = nodeName;
    this.additionalInfo = new TreeMap<>();
    this.parents = new HashSet<>();
    this.children = new HashSet<>();
  }
}
