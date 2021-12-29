package Doctor;

public class Doctor {
	private int DocID;
	private String last;
	private String first;
	private String email;
	private String specialization;
	
	public Doctor(int DocID, String last, String first, String email,
			String specialization) {
		super();
		this.DocID = DocID;
		this.last = last;
		this.first = first;
		this.email = email;
		this.specialization = specialization;
	}

	public int getDocID() {
		return DocID;
	}

	public void setDocID(int DocID) {
		this.DocID = DocID;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return String
				.format("Doctor [DocID=%s, last=%s, first=%s, email=%s, specialization=%s]",
						DocID, last, first, email, specialization);
	}
	
	
		
}


