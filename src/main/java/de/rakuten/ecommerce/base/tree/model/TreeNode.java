package de.rakuten.ecommerce.base.tree.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import de.rakuten.ecommerce.base.model.AbstractEntity;

@MappedSuperclass
public abstract class TreeNode<TN extends TreeNode<TN>> extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "parent_id", insertable = false, updatable = false)
	private Long parentId;

	@ManyToOne
	private TN parent;

	@OneToMany(mappedBy = "parent")
	private Set<TN> children;

	public TN getParent() {
		return parent;
	}

	public void setParent(TN parent) {
		this.parent = parent;
	}

	public Set<TN> getChildren() {
		return children;
	}

	public void setChildren(Set<TN> children) {
		this.children = children;
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
