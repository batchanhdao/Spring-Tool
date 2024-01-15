package House;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class House {
	@Id
	private String id;
	private String address;
	private int area;
	private String type;
	private int approved;
	
}
