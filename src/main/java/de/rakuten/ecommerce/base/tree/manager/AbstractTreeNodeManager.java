/**
 * 
 */
package de.rakuten.ecommerce.base.tree.manager;

import de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager;
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
			// Ensure parent exists
			read(node.getParentId());
		}
	}

	@Override
	protected void doBeforeDelete(Node node) {
		if (node.getParent() != null) {
			// Ensure parent exists
			Node parent = read(node.getParent().getId());
			parent.getChildren().addAll(node.getChildren());
			update(parent);
		}
	}
}
