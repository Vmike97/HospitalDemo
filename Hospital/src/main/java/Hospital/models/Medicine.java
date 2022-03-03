package Hospital.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int medId;
	private String name;
	private String effect;
	private int inStock;
	
	public Medicine(){
	}

	public Medicine(int medId, String name, String effect) {
		this.medId = medId;
		this.name = name;
		this.effect = effect;
		this.inStock = 0;
	}
	
	public Medicine(int medId, String name, String effect, int inStock) {
		this(medId, name, effect);
		this.inStock = inStock;
	}

	public int getMedId() {
		return medId;
	}

	public void setMedId(int medId) {
		this.medId = medId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	@Override
	public String toString() {
		return "Medicine [medId=" + medId + ", name=" + name + ", effect=" + effect + ", inStock=" + inStock + "]";
	}

}
