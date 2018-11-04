/* Calculates matrix ^ power in O(log(power)). Matrix must be of size n x n. */

class MatrixExponentiation {
	public long[][] pow(long input[][], long power) {
		long result[][] = new long[input.length][input[0].length];
		if (power == 0) {
			for (int i = 0; i < result.length; i++) {
				result[i][i] = 1;
			}
			return result;
		}
		if (power == 1) {
			return input;
		}
		result = input;
		power--;
		while (power > 0) {
			if (power % 2 == 1) {
				result = multiplyMatrix(result, input);
				power--;
			}
			input = multiplyMatrix(input, input);
			power /= 2;
		}
		return result;
	}
	public long[][] pow(long input[][], long power, long mod) {
		long result[][] = new long[input.length][input[0].length];
		if (power == 0) {
			for (int i = 0; i < result.length; i++) {
				result[i][i] = 1;
			}
			return result;
		}
		if (power == 1) {
			return input;
		}
		result = input;
		power--;
		while (power > 0) {
			if (power % 2 == 1) {
				result = multiplyMatrix(result, input, mod);
				power--;
			}
			input = multiplyMatrix(input, input, mod);
			power /= 2;
		}
		return result;
	}
	private long[][] multiplyMatrix(long matrix1[][], long matrix2[][]) {
		long result[][] = new long[matrix1[0].length][matrix2.length];
		
	    	for (int i = 0; i < matrix1[0].length; i++) {
	            for (int j = 0; j < matrix1[0].length; j++) {
	            	result[i][j] = 0;
	            	for (int k = 0; k < matrix1[0].length; k++) {
	            	    result[i][j] = result[i][j] + (matrix1[i][k] * matrix2[k][j]);
	            	}
	            }
	    	}
		return result;
	}
	private long[][] multiplyMatrix(long matrix1[][], long matrix2[][], long mod) {
		long result[][] = new long[matrix1[0].length][matrix2.length];
		
	    	for (int i = 0; i < matrix1[0].length; i++) {
	            for (int j = 0; j < matrix1[0].length; j++) {
	            	result[i][j] = 0;
	            	for (int k = 0; k < matrix1[0].length; k++) {
	            	    result[i][j] = (result[i][j] + (matrix1[i][k] * matrix2[k][j])) % mod;
	            	}
	            }
	    	}
		return result;
	}
}
