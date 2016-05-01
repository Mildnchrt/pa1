package converter;
/**
 * A difinition of common units length.
 * @author Nutcharueta Sihirunwong 5810545866
 *
 */
public enum Area implements Unit{
	SQUAREMETER( "Sqr. Meter", 1.0 ),
	SQUAREKILOMETER( "Sqr. Kilometer", 1.0E6 ),
	SQUARECENTIMETER( "Sqr. Centimeter", 1.0E-4 ),
	SQUAREMILE( "Sqr. Mile", 2.59E6 ),
	SQUAREYARD( "Sqr. Yard", 0.8361 ),
	SQUAREFOOT( "Sqr. Foot", 0.0929 ),
	SQUAREINCH( "Sqr. Inch", 6.45E-4 ),
	HECTARE( "Hectare", 10000.0 ),
	ACRE( "Acre", 4046.86 ),
	RAI( "Rai", 1600.0 );
	
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
	 * @param name is a string name of area unit
	 * @param value is multiplier to convert to square meter 
	 */
	Area(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * public properties of the enum members
	 */
	public double getValue() { return value; }
	public String toString() { return name; }
}
