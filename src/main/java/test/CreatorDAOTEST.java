package test;

import dao.creatorlistDAO;
import model.Creator;

public class CreatorDAOTEST {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		insertCreatorTest();
	}
	
	
	public static void findCreatorNameByIdTest() {
		Creator creator = new Creator(1);
		creatorlistDAO creatorlistDAO = new creatorlistDAO();
		creator = creatorlistDAO.searchCreatorById(creator);
		System.out.println(creator.getCreatorId());
		System.out.println(creator.getCreatorName());
	}
	
	public static void findCreatorIdByNameTest() {
		Creator creator = new Creator("Creator_AAA");
		creatorlistDAO creatorlistDAO = new creatorlistDAO();
		creator = creatorlistDAO.searchCreatorByName(creator);
		System.out.println(creator.getCreatorId());
		System.out.println(creator.getCreatorName());
	}
	
	public static void insertCreatorTest() {
		Creator creator = new Creator("Creator_L");
		creatorlistDAO creatorlistDAO = new creatorlistDAO();
		creator = creatorlistDAO.insertCreator(creator);
		System.out.println(creator.getCreatorId());
		System.out.println(creator.getCreatorName());
	}

}
