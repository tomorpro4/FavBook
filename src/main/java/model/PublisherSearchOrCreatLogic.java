package model;

import dao.publisherlistDAO;

public class PublisherSearchOrCreatLogic {

	public  Publisher PublisherSearchOrCreat(Publisher publisher) {
		publisherlistDAO publisherlistDAO = new publisherlistDAO();
		publisher = publisherlistDAO.searchPublisherByName(publisher);
		if(publisher.getPublisherId()==0) {
			publisher = publisherlistDAO.insertPublisher(publisher);
		}
		return publisher;
	}

}
