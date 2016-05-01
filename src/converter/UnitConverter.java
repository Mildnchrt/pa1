package converter;

public class UnitConverter {
	/**
	 * convert value(amount) from fromUnit to  toUnit
	 * @param amount value from textField
	 * @param fromUnit unit of amount
	 * @param toUnit unit that user want
	 * @return value in unit toUnit
	 */
	public double converter (double amount, Unit fromUnit, Unit toUnit) {
		return amount * fromUnit.getValue() / toUnit.getValue();
	}
	/**
	 * return value unit from unit type
	 * @return 
	 */
	public Unit[] getUnits( UnitType ut ) {
		return UnitFactory.getInstance().getUnits( ut );
	}
}
