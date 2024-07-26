import assignment.controller.NodeController;
import assignment.controller.graph.GraphCycleDetector;
import assignment.models.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGraphCycleDetector {

  @Test
  public void testCycleDetectionWithoutCycle() {
    NodeController nodeController = new NodeController();
    Node node1 = new Node("1", "one");
    Node node2 = new Node("2", "two");
    Node node3 = new Node("3", "three");

    nodeController.addDependency(node1, node2);
    nodeController.addDependency(node2, node3);

    Assertions.assertFalse(GraphCycleDetector.cycleExist(node2, node3));
  }

  @Test
  public void testCycleDetectionWithCycle() {
    NodeController nodeController = new NodeController();
    Node node1 = new Node("1", "one");
    Node node2 = new Node("2", "two");
    Node node3 = new Node("3", "three");

    nodeController.addDependency(node1, node2);
    nodeController.addDependency(node2, node3);
    nodeController.addDependency(node3, node3);

    Assertions.assertTrue(GraphCycleDetector.cycleExist(node3, node1));
  }
}
