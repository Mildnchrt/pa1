package converter;
/**
 * A difinition of common units length.
 * @author mildnchrt
 *
 */
public enum Length implements Unit{
	/*Deifine the member of enumration
	 * name = a string name of unit,
	 * value = multiplier to convert to meter
	 */
	METER( "Meter", 1.0 ) ,
	FOOT( "Foot", 0.3048 ),
	MILE( "Mile", 1609.344 ),
	KILOMETER( "Kilometer", 1000 ),
	CENTEMETER( "Centemeter", 0.01 ),
	WA( "Wa", 2.0),
	LIGHTYEAR("Light-year", 9460730472580800.0);
	
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
	 * @param name
	 * @param value
	 */
	Length(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * public properties of the enum members
	 */
	public double getValue() { return value; }
	public String toString() { return name; }
}
