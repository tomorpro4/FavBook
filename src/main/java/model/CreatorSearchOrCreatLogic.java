package model;

import dao.creatorlistDAO;

public class CreatorSearchOrCreatLogic {

	public Creator CreatorSearchOrCreat(Creator creator) {
		creatorlistDAO creatorlistDAO = new creatorlistDAO();
		creator = creatorlistDAO.searchCreatorByName(creator);
		if(creator.getCreatorId()==0) {
			creator = creatorlistDAO.insertCreator(creator);
		}
		return creator;
	}

}
