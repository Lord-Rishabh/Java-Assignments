package assignment.controller;

import assignment.controller.options.*;

import java.util.Map;

public class AddMenuOptions {

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
