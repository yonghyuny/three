package ex999_test;

public class Member {
	
    private String name;
    private String id;
    private String password;
    private String phoneNumber;

    
    public Member () { }

    public Member(String name, String id, String password, String phoneNumber) {
    	this.name = name;
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
        }
    

    public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
    public String toString() {
    	return "이름: " + name + ", 아이디: " + id + ", 비밀번호: " + password + ", 전화번호: "+ phoneNumber;
    }

	
}
