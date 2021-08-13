// Note: taken from book Sec 8.3.2.

class IllegalArgumentException extends Exception {

    private String problem;

    IllegalArgumentException(String problem) {
        this.problem = problem;
    }

    public String toString() {
        return "IllegalArgumentException: " + problem;
    }
}

class CustomException {
    // Returns the larger of the two roots of the quadratic equation
    // a*x*x + b*x + c = 0, provided it has any roots.
    public static double root( double a, double b, double c ) throws IllegalArgumentException {

        if (a == 0) {
            throw new IllegalArgumentException("a can't be zero.");
        }
        else {
            double disc = b*b - 4*a*a;

            if (disc < 0)
                throw new IllegalArgumentException("Discriminant < zero.");

            return (-b + Math.sqrt(disc)) / (2*a);
        }
    }

    public static void main(String[] args) {
        try {
            root(0, 5, 6);
        }
        catch (Exception e) {
            // This case catches every kind of exception
            System.out.println(e);
        }

        try {
            root(0, 5, 6);
        }
        catch (IllegalArgumentException e) {
            // This catches only IllegalArgumentExceptions
            System.out.println(e);
        }

        try {
            root(1, 0, 3);
        }
        catch (IllegalArgumentException e) {
            // This catches only IllegalArgumentExceptions
            System.out.println(e);
        }
    }
}
