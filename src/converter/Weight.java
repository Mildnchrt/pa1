package converter;
/**
 * A difinition of common units weight.
 * @author Nutcharueta Sihirunwong5810545866
 *
 */
public enum Weight implements Unit{
	KILOGRAM( "Kilogram", 1.0 ),
	TONNE( "Tonne", 1000 ),
	GRAM( "Gram", .001 ),
	MILLGRAM( "Milligram", 1.0E-6 ),
	MICROGRAM( "Microgram", 1.0E-9 ),
	OUNCE( "Ounce", 0.0283 ),
	POUND( "Pound", 0.4536 ),
	STONE( "Stone", 6.3502 );
	
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
	 * @param name is  string name of weight unit
	 * @param value is multiplier to convert to kilogram
	 */
	Weight(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * public properties of the enum members
	 */
	public double getValue() { return value; }
	public String toString() { return name; }
}
