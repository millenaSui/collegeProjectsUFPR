public class Leitor {
    public float leNumeroFloat() {
        return Float.parseFloat(System.console().readLine());
    }

    public int leNumeroInt() {
        return Integer.parseInt(System.console().readLine());
    }

    public double leNumeroDouble() {
        return Double.parseDouble(System.console().readLine());
    }
    public String leString() {
        return System.console().readLine();
    }
}
