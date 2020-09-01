public class Main_PID {
    public static void main(String[] args) {
        double output = 0; //sa zicem ca asta e cuplul motorului
        double currentValue = 25; // valoarea pe care noi am masurat-o
        PID pid_controller = new PID(30, 1.5, 0, 0.023);
        int i = 0;
        while(i < 30) {
            System.out.println("############");
            System.out.println("Incercare:" + i + "\n");
            System.out.println("Point:" + pid_controller.getPoint());
            System.out.println("Current Value:" + currentValue);
            System.out.println("Output:" + output);
            System.out.println("Remaining:" + String.valueOf(pid_controller.getPoint() - currentValue));
            output = pid_controller.getOutput(.2, currentValue); //calculam valoarea output-ului, sa zicem ca 1 este o unitate de timp care a trecut
            currentValue += output * 1.3 + (Math.random()-.5) * 3; //calculam valoarea curenta(care va fi calculata in real , dar aici am luat ceva random pentru overshoot factor)
            i++;
        }
    }
}
