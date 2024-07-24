package assignment.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Getter
@Setter
public class Node {
  private String id;
  private String name;
  private Map<String, String> additionalInfo ;
  private Set<Node> parents;
  private Set<Node> children;

  public Node(String nodeId, String nodeName) {
    setId(nodeId);
    setName(nodeName);
    additionalInfo = new TreeMap<>();
    parents = new HashSet<>();
    children = new HashSet<>();
  }
}
