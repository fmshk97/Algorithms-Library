/* Calculates x^y in O(log(y)) */

class FastExponentiation {
	public double exp(double x, double y) { 	/* When y is negative, answer will be a double value */
		if (y == 0 || x == 1) {
			return 1d;
		}
		if (y == 1) {
			return x;
		}
		if (x == 0) {
			return 0d;
		}
		boolean isNegativeX = (x < 0)? true: false;
		boolean isNegativeY = (y < 0)? true: false;
		if (isNegativeX) {
			x *= -1;
		}
		if (isNegativeY) {
			y *= -1;
		}
		boolean isResultNegative = (isNegativeX && y % 2 == 1)? true: false;
		double result = 1d;
		while (y > 0) {
			if (y % 2 == 1) {
				result *= x;
				y--;
			}
			x *= x;
			y /= 2;
		}
		if (isNegativeY) {
			result = 1 / result;
		}
		return isResultNegative? (-1 * result): result;
	}
	
	public long exp(long x, long y) {		/* x^y when y is positive */
		if (y == 0 || x == 1) {
			return 1l;
		}
		if (y == 1) {
			return x;
		}
		if (x == 0 || y < 0) {
			return 0l;
		}
		boolean isNegativeX = (x < 0)? true: false;
		if (isNegativeX) {
			x *= -1;
		}
		boolean isResultNegative = (isNegativeX && y % 2 == 1)? true: false;
		long result = 1l;
		while (y > 0) {
			if (y % 2 == 1) {
				result *= x;
				y--;
			}
			x *= x;
			y /= 2;
		}
		return isResultNegative? (-1 * result): result;
	}
}
