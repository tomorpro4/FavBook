package model;

import dao.categorylistDAO;

public class CategorySearchOrCreatLogic {

	public Category CategorySearchOrCreat(Category category) {
		categorylistDAO categorylistDAO = new categorylistDAO();
		category = categorylistDAO.searchCategoryByName(category);
		if(category.getCategoryId()==0) {
			category = categorylistDAO.insertCategory(category);
		}
		return category;
	}

}
