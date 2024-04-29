package flight;

import java.io.Serializable;

public class Flight implements Serializable{
	
	private String flightName;       // 항공편
	private int flightStartYear;     // 출발 년도(Year)
	private int flightStartMonth;    // 출발 월(Month)
	private int flightStartDay;      // 출발 날(day)
	private String startPoint;       // 출발지
	private String destination;		 // 도착지
	private String airlineName;		 // 항공회사 이름
	
	public Flight () { }
	
	public Flight(String flightName, int flightStartYear, int flightStartMonth, 
		int flightStartDay, String startPoint, String destination,String airlineName) {
			super();
			this.flightName = flightName;
			this.flightStartYear = flightStartYear;
			this.flightStartMonth = flightStartMonth;
			this.flightStartDay = flightStartDay;
			this.startPoint = startPoint;
			this.destination = destination;
			this.airlineName = airlineName;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public int getFlightStartYear() {
		return flightStartYear;
	}

	public void setFlightStartYear(int flightStartYear) {
		this.flightStartYear = flightStartYear;
	}

	public int getFlightStartMonth() {
		return flightStartMonth;
	}

	public void setFlightStartMonth(int flightStartMonth) {
		this.flightStartMonth = flightStartMonth;
	}

	public int getFlightStartDay() {
		return flightStartDay;
	}

	public void setFlightStartDay(int flightStartDay) {
		this.flightStartDay = flightStartDay;
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

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	
	@Override
	public String toString() {
		return "항공사 : " + airlineName + "\n항공편 : " + flightName + "\n출발 년도 : " + flightStartYear +
				"\n출발 월 : " + flightStartMonth + "\n출발 날 : " + flightStartDay + "\n출발지 : " + startPoint +
				"\n도착지 : " + destination;
	}
	
}
