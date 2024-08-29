import assignment.controller.NodeController;
import assignment.models.Node;
import assignment.utils.Validations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestValidations {

  private NodeController nodeController;

  @BeforeEach
  void setUp() {
    nodeController = new NodeController();
    Node node1 = new Node("1", "one");
    Node node2 = new Node("2", "two");
    Node node3 = new Node("3", "three");

    nodeController.addNode("1", node1);
    nodeController.addNode("2", node2);
    nodeController.addNode("3", node3);
  }

  @Test
  void testValidGetNodeEmptyNodeId() {
    Assertions.assertFalse(Validations.validGetNode(nodeController, ""));
  }

  @Test
  void testValidGetNodeNodeDoesNotExist() {
    Assertions.assertFalse(Validations.validGetNode(nodeController, "4"));
  }

  @Test
  void testValidGetNodeNodeExists() {
    Assertions.assertTrue(Validations.validGetNode(nodeController, "1"));
  }

  @Test
  void testValidAddNodeEmptyNodeId() {
    Assertions.assertFalse(Validations.validAddNode(nodeController, ""));
  }

  @Test
  void testValidAddNodeNodeAlreadyExists() {
    Assertions.assertFalse(Validations.validAddNode(nodeController, "1"));
  }

  @Test
  void testValidAddNodeNodeDoesNotExist() {
    Assertions.assertTrue(Validations.validAddNode(nodeController, "4"));
  }
}
