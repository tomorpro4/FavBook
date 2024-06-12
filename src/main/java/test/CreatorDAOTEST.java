package test;

import dao.creatorlistDAO;
import model.Creator;

public class CreatorDAOTEST {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Creator creator = new Creator(1);
		
		creatorlistDAO creatorlistDAO = new creatorlistDAO();
		creator = creatorlistDAO.searchCreatorById(creator);
		System.out.println(creator.getCreatorId());
		System.out.println(creator.getCreatorName());
	}

}
