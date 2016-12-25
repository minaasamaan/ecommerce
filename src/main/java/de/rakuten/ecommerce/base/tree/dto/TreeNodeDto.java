package de.rakuten.ecommerce.base.tree.dto;

import de.rakuten.ecommerce.base.dto.AbstractDto;

public abstract class TreeNodeDto<TND extends TreeNodeDto<TND>> extends AbstractDto {

	private static final long serialVersionUID = 1L;

	private Long parentId;

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
