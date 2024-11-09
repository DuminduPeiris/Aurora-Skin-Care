package Services;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Service {
     final Scanner scanner;

    public Service() {
        this.scanner = new Scanner(System.in);

    }
    public String question(String question){
        System.out.println(question);
        return this.scanner.next();
    }

    public int options(String question,String confirm, List<String> options ){
        System.out.println(question);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.println(confirm);

       return this.scanner.nextInt();

    }

    public boolean confirm(String question){
        System.out.println(question);
        System.out.println("1 : Yes     2: No ");
        int confirm = this.scanner.nextInt();
        if(confirm == 1){
            return true;
        }
        else if (confirm == 2)
        {
            return false;
        }
        return false;
    }

    public int askNumber(String question){
        System.out.println(question);
        return this.scanner.nextInt();
    }

    public LocalDate checkDate(String question) {
        try {
            System.out.println(question);
            String date = this.scanner.next();
            return LocalDate.parse(date);

        } catch (DateTimeParseException e) {
            System.out.println("date format is invalid, Please enter valid date! ");
            return checkDate(question);

        }
    }

    public void print(String text){
        System.out.println(text);
    }





}
