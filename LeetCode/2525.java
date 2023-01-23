class Q {
    public static void main(String[] args) {
        int length = 2909;
        int width = 3968;
        int height = 3272;
        int mass = 727;
        System.out.print(categorizeBox(length, width, height, mass));
    }
    public static String categorizeBox(int length, int width, int height, int mass) {
        String type;
        boolean heavy = false;
        boolean bulky = false;
        long volume = (long)length * (long)width * (long)height;
        if(length >= 10000 || height >= 10000 || width >= 10000 || volume >= 1000000000){
            bulky = true;
        }

        if(mass >= 100)
            heavy = true;
        return (bulky) ?
                ((heavy) ? "Both" : "Bulky" ) :
                ((heavy) ? "Heavy": "Neither");
    }

}