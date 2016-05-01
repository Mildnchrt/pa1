package converter;

public final class UnitFactory {
	private static final UnitFactory INSTANCE = new UnitFactory();
	
	private UnitFactory() { }
	
	public static UnitFactory getInstance() {
		return INSTANCE;
	}
	
	public UnitType[] getUnitTypes() {
		return UnitType.values();
	}
	
	public Unit[] getUnits( UnitType ut ) {
		if ( ut == UnitType.Length ) {
			return Length.values();
		}
		else if ( ut == UnitType.Area ) {
			return Area.values();
		}
		else if ( ut == UnitType.Weight ) {
			return Weight.values();
		}
		return Time.values();
	}
}
