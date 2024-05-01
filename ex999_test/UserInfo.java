package ex999_test;

public class UserInfo {
	private String id;
	private String name;
	private String number;
	private String airline;
	private String airplane;
	private String destination;
	private String date;
	private int people;
	
	public UserInfo () { }
	
	// 이름, 전화번호 정보 가져오는 코드 구현시 삭제할 것(임시)
	public UserInfo (String id) {
		this.id = id;
	}
	
	public UserInfo (String id, String name, String number) {
		this.id = id;
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}
	
}
