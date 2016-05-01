package converter;
/**
 * A difinition of common units time.
 * @author Nutcharueta Sihirunwong 5810545866
 *
 */
public enum Time implements Unit{
	HOUR( "Hour", 60 ),
	MINUTE( "Minute", 1 ),
	SECOND( "Second", (1.0/60) );
	
	/**
	 * name of this unit
	 */
	public final String name;
	/**
	 * multiplier to convert this unit to std unit
	 */
	public final double value;
	
	/**
	 * private constructor for enum members
	 * @param name is a string name of time unit
	 * @param value is multiplier to convert to minute
	 */
	Time(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * public properties of the enum members
	 */
	public double getValue() { return value; }
	public String toString() { return name; }
}
