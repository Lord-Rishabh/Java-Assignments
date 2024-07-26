package assignment.controller;

import assignment.controller.options.AddNewDependency;
import assignment.controller.options.AddNewNode;
import assignment.controller.options.DeleteDependency;
import assignment.controller.options.DeleteNode;
import assignment.controller.options.GetAncestors;
import assignment.controller.options.GetChildren;
import assignment.controller.options.GetDescendants;
import assignment.controller.options.GetParents;

import java.util.Map;

/**
 * Adds menu options to a provided menu.
 */
public class AddMenuOptions {

  /**
   * Adds options to the provided menu options map.
   * @param menuOptions    the map of menu options
   * @param nodeController the controller managing the nodes
   */
  public static void addOptions(Map<Integer, Runnable> menuOptions, NodeController nodeController) {
    menuOptions.put(1, () -> GetParents.option(nodeController));
    menuOptions.put(2, () -> GetChildren.option(nodeController));
    menuOptions.put(3, () -> GetAncestors.option(nodeController));
    menuOptions.put(4, () -> GetDescendants.option(nodeController));
    menuOptions.put(5, () -> DeleteDependency.option(nodeController));
    menuOptions.put(6, () -> DeleteNode.option(nodeController));
    menuOptions.put(7, () -> AddNewDependency.option(nodeController));
    menuOptions.put(8, () -> AddNewNode.option(nodeController));
    menuOptions.put(9, () -> System.exit(0));
  }
}
