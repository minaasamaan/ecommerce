package de.rakuten.ecommerce.base.tree.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import de.rakuten.ecommerce.base.model.AbstractEntity;

/**
 * Parent class for tree hierarchy implementation. However, this basic
 * implementation has been adopted for simplicity, a more efficient approach
 * would be adopting the Nested Set Model,
 * {@link https://en.wikipedia.org/wiki/Nested_set_model}
 * 
 * @author Mina
 *
 * @param <TN>
 */
@MappedSuperclass
public abstract class TreeNode<TN extends TreeNode<TN>> extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "parent_id", insertable = false, updatable = false)
	private Long parentId;

	@ManyToOne
	private TN parent; // fetch type left to the default "EAGER", because it's
						// future requirement to get path up to root. So, eager
						// fetching here is more efficient.

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
