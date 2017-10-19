public static void writeSquares(int n) {
    if (n < 1) {
        throw new IllegalArgumentException();
    }
    if (n == 1) {
        // base case
        System.out.print("1");
    } else {
        int printSquare = n * n;
        if (n % 2 == 0) {
            writeSquares(n - 1);
            System.out.print( ", " + printSquare);
        } else {
            System.out.print(printSquare + ", ");
            writeSquares(n - 1);
        }
    }
}