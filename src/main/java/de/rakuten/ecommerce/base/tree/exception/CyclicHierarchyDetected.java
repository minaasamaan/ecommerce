/**
 * 
 */
package de.rakuten.ecommerce.base.tree.exception;

import org.springframework.http.HttpStatus;

import de.rakuten.ecommerce.base.exception.ApplicationException;

/**
 * @author Mina
 *
 */
public class CyclicHierarchyDetected extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long nodeId;
	private Long parentId;

	/**
	 * @param httpStatus
	 * @param message
	 */
	public CyclicHierarchyDetected(Long nodeId, Long parentId) {
		super(HttpStatus.CONFLICT, "Cyclic hierarchy detected, cannot assign entity with id= " + nodeId
				+ ", to parent entity with id= " + parentId);
		setNodeId(nodeId);
		setParentId(parentId);
	}

	/**
	 * @return the nodeId
	 */
	public Long getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId
	 *            the nodeId to set
	 */
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
