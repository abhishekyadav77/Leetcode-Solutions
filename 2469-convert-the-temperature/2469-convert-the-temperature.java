class Solution {
    public double[] convertTemperature(double celsius) {
        double kelvin=0;
        double fahr=0;
        double[] ans = new double[2];
        kelvin= celsius+273.15;
        fahr= celsius *1.80+32.00;
        return new double[]{kelvin, fahr};
    }
}