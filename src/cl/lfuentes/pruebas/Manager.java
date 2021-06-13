package cl.lfuentes.pruebas;

public class Manager {
    public static void main(String[] args) {
        try {
            AfipClient.invoke();
        } catch (Exception e) {
    System.out.println("Exception: "+e);
        }
    }
}
