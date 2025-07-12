package Hospital_Management1;

public class Patient {
	int id;
	String name;
	String disease;
	int age;

	public Patient(int id, String name, String diseases, int age) {
		this.id = id;
		this.name = name;
		disease = diseases;
		this.age = age;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Patient)) return false;

		Patient patient = (Patient) o;
		return age == patient.age &&
				name.equalsIgnoreCase(patient.name) &&
				disease.equalsIgnoreCase(patient.disease);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDisease() {
		return disease;
	}

	public int getAge() {
		return age;
	}

	//For Decoration Purpose Only
	public String toTableRow() {
		return String.format("| %-4d | %-14s | %-14s | %-4d |\n", id, name, disease, age);
	}
}
