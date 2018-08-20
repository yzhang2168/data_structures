package playground;

public class Array2D {
    /**
     * trace: sum of all diagonal numbers
     * */
    public static int trace(int[][] m) {
    	if (m.length != m[0].length) {
    		throw new java.lang.Error("Input matrix is not squared.");
    	}
    	
    	int trace = 0;
    	for (int i = 0; i < m.length; i++) {
    		trace += m[i][i]; 	
    		}
    	return trace;
    }
    
    /**
     * element-wise summation
     * */
    public static int[][] sum2Matrices(int[][] a, int[][] b) {
    	if (a.length != b.length || a[0].length != b[0].length) {
    		throw new java.lang.Error("Cannot sum two matrices of different sizes.");
    	}
    	
    	int m = a.length;
    	int n = a[0].length;
    	int[][] result = new int[m][n];
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			result[i][j] = a[i][j] + b[i][j];
    		}
    	}
    	return result;
    }
    
    
    public static void main(String[] args) {
    	int[][] matrix = {{1, 3, 4}, {3, 4, 6}, {3, -2, -1}};
    	System.out.println(trace(matrix));
    	
    	int[][] m1 = {{3, 2, 1}, {3, 4, 4}, {3, -2, -1}};
    	int[][] m2 = {{-1, 0, -1}, {4, 2, 1}, {-4, 6, 9}};
    	int[][] result = sum2Matrices(m1, m2);
    	Util.printArray(result);
    }
    
}
