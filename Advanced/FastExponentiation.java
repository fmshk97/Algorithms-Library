package Advanced;
/* Calculates constant ^ power & (constant ^ power) % mod in O(log(y)) */

class FastExponentiation {
	public double pow(double constant, double power) { 	/* When power is negative, answer will be a double value */
		if (power == 0 || constant == 1) {
			return 1d;
		}
		if (power == 1) {
			return constant;
		}
		if (constant == 0) {
			return 0d;
		}
		boolean isNegativeX = (constant < 0)? true: false;
		boolean isNegativeY = (power < 0)? true: false;
		if (isNegativeX) {
			constant *= -1;
		}
		if (isNegativeY) {
			power *= -1;
		}
		boolean isResultNegative = (isNegativeX && power % 2 == 1)? true: false;
		double result = 1d;
		while (power > 0) {
			if (power % 2 == 1) {
				result *= constant;
				power--;
			}
			constant *= constant;
			power /= 2;
		}
		if (isNegativeY) {
			result = 1 / result;
		}
		return isResultNegative? (-1 * result): result;
	}
	
	public long pow(long constant, long power) {		/* constant ^ power when power is positive */
		if (power == 0 || constant == 1) {
			return 1l;
		}
		if (power == 1) {
			return constant;
		}
		if (constant == 0 || power < 0) {
			return 0l;
		}
		boolean isNegativeX = (constant < 0)? true: false;
		if (isNegativeX) {
			constant *= -1;
		}
		boolean isResultNegative = (isNegativeX && power % 2 == 1)? true: false;
		long result = 1l;
		while (power > 0) {
			if (power % 2 == 1) {
				result *= constant;
				power--;
			}
			constant *= constant;
			power /= 2;
		}
		return isResultNegative? (-1 * result): result;
	}
	
	public double pow(double constant, double power, long mod) {
		if (power == 0 || constant == 1) {
			return 1d;
		}
		if (power == 1) {
			return constant;
		}
		if (constant == 0) {
			return 0d;
		}
		boolean isNegativeX = (constant < 0)? true: false;
		boolean isNegativeY = (power < 0)? true: false;
		if (isNegativeX) {
			constant *= -1;
		}
		if (isNegativeY) {
			power *= -1;
		}
		boolean isResultNegative = (isNegativeX && power % 2 == 1)? true: false;
		double result = 1d;
		constant %= mod;
		while (power > 0) {
			if (power % 2 == 1) {
				result = (result * constant) % mod;
				power--;
			}
			constant = (constant * constant) % mod;
			power /= 2;
		}
		if (isNegativeY) {
			result = 1 / result;
		}
		return isResultNegative? (result + mod): result;
	}
	
	public long pow(long constant, long power, long mod) {
		if (power == 0 || constant == 1) {
			return 1l;
		}
		if (power == 1) {
			return constant;
		}
		if (constant == 0 || power < 0) {
			return 0l;
		}
		boolean isNegativeX = (constant < 0)? true: false;
		if (isNegativeX) {
			constant *= -1;
		}
		boolean isResultNegative = (isNegativeX && power % 2 == 1)? true: false;
		long result = 1l;
		constant %= mod;
		while (power > 0) {
			if (power % 2 == 1) {
				result = (result * constant) % mod;
				power--;
			}
			constant = (constant * constant) % mod;
			power /= 2;
		}
		return isResultNegative? (mod + result): result;
	}
}
