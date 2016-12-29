/**
 * 
 */
package de.rakuten.ecommerce.base.tree.manager;

import java.util.LinkedHashSet;
import java.util.Set;

import de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager;
import de.rakuten.ecommerce.base.tree.exception.CannotDeleteNonLeafNodes;
import de.rakuten.ecommerce.base.tree.exception.CyclicHierarchyDetected;
import de.rakuten.ecommerce.base.tree.model.TreeNode;

/**
 * Assumptions: - No batch create/update is allowed (i.e. You can't create a
 * full tree or subtree, only one node at time)
 * 
 * @author Mina
 *
 */
public abstract class AbstractTreeNodeManager<Node extends TreeNode<Node>> extends AbstractBusinessEntityManager<Node> {

	@Override
	protected void doBeforePersist(Node node, boolean isNew) {
		if (node.getParentId() != null) {
			// Ensure parent exists, inject it in the entity
			Node parent = read(node.getParentId());
			if (!isNew) {
				// Node already exists in DB, new nodes cannot make
				// cycles.
				checkForPotentialCycle(node, parent);
			}
			node.setParent(parent);
		}
	}

	/**
	 * @param node
	 * @param parent
	 */
	protected void checkForPotentialCycle(Node node, Node parent) {
		Set<Node> pathToMostParent = new LinkedHashSet<>();
		Node cursor = parent;
		do {
			pathToMostParent.add(cursor);
			cursor = cursor.getParent();
		} while (cursor != null);
		if (pathToMostParent.contains(node)) {
			throw new CyclicHierarchyDetected(node.getId(), parent.getId());
		}
	}

	@Override
	protected void doBeforeDelete(Node node) {
		if (node.getChildren() != null && !node.getChildren().isEmpty()) {
			throw new CannotDeleteNonLeafNodes(node.getId());
		}
	}
}
