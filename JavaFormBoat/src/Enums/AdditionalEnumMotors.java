package Enums;
public enum AdditionalEnumMotors {

	oneMotors,

	twoMotors,

	threeMotors;

	public static AdditionalEnumMotors definitionEnumMotors(int countMotors) {
		 if (countMotors == 1) {
             return oneMotors;
         }
         if (countMotors == 2) {
        	 return twoMotors;
         }
         if (countMotors == 3) {
             return threeMotors;
         }
         return null;
	}
}