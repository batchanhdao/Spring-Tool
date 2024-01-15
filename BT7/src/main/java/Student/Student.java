package Student;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Student {
	@Id
	private String id;
	private String name;
	private String dob;
	private String department;
	private int approved;
}
