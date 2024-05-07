package ex999_test;

public class ReservationInfo {
	private String date;
	private String startPoint = "인천";
	private String destination;
	private int countPeople;
	
	private String airline;
	private String airplane;
	
	public ReservationInfo (String date, String destination, int countPeople) {
		this.date = date;
		this.destination = destination;
		this.countPeople = countPeople;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getCountPeople() {
		return countPeople;
	}

	public void setCountPeople(int countPeople) {
		this.countPeople = countPeople;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getAirplane() {
		return airplane;
	}

	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}


	
}
