public class Treatment {
    private String name;
    private double price; 
    private double registrationFee;

    public Treatment(String name, double price, double registrationFee) {
        this.name = name;
        this.price = price;
        this.registrationFee = registrationFee;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public double getRegistrationFee() {
        return registrationFee;
    }
    public double calTax(){
        return total()*2.5/100;
    }
    public double calculateTotalWithTax() {
        return Math.round((total() + calTax()));
    }
    public double total(){
        return price+registrationFee;
    }
}
