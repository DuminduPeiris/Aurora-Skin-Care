import Services.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class AppointmentService extends Service {
    Scanner scanner = new Scanner(System.in);
    Clinic clinic = new Clinic();

    public void createAppointment(){
        try {
    boolean paid =  confirm("You need to pay Rs.500 for make an Appointment, Confirm?");
    if (!paid){
        return;
    }
            String nic = question("Enter NIC");
            String name = question("Enter Name: ");
            String email = question("Enter Email:");
            String phoneNumber = question("Enter Phone Number:");

            Patient patient = new Patient(nic, name, email, phoneNumber);

            // Choose dermatologist

            int docChoice =  askNumber("Choose Dermatologist (1: Dr. Samarasinghe  2: Dr. Kevin): ");
            Dermatologist dermatologist = clinic.getDermatologists().get(docChoice - 1);

            // Choose treatment
            System.out.println();
            List<Treatment> treatments = clinic.getTreatments();
            int choice = options("Available Treatments:","Choose Treatment: ",treatments.stream().map(i->i.getName()).toList());
            Treatment treatment = treatments.get(choice - 1);

            // Choose appointment day
            LocalDateTime appointmentDay = getAppointmentDate(dermatologist);

            // Make appointment
            clinic.makeAppointment(patient, appointmentDay, dermatologist, treatment);





        } catch (Exception e) {

            print("Appointment not created Successfully!!!");

        }
    }

    private LocalDateTime getAppointmentDate(Dermatologist dermatologist){

        List<Appointment> appointments = clinic.getAppointments();

        LocalDate appointmentDate = checkDate("Please enter a appointment date (YYYY-MM-DD) ? (Available in MONDAY, WEDNESDAY, FRIDAY, SATURDAY)");
        DayOfWeek day = appointmentDate.getDayOfWeek();
        List<String> availableTimes = dermatologist.getSchedule().get(day);
        if (availableTimes == null || availableTimes.isEmpty()){

            System.out.println("Dermatologists not available on selected date");
            return getAppointmentDate(dermatologist);
        }
        List<Appointment> usedSlots = appointments.stream().filter(i-> i.getDay().toLocalDate().equals(appointmentDate)).toList();
        List<String> usedTimeSlots = usedSlots.stream().map(i-> i.getDay().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm"))).toList();
        availableTimes=availableTimes.stream().filter(i-> !usedTimeSlots.contains(i)).toList();

        int time = options("Available Times", "Please Select a Available Time",availableTimes);
        String appointmentTime = availableTimes.get(time-1);

        return LocalDateTime.of(appointmentDate, LocalTime.parse(appointmentTime));
    }

    public void viewAppointments(){
        try {
            LocalDate appointmentDate = checkDate("Please enter the Appointment Date (YYYY-MM-DD) ");
            clinic.viewAppointmentsByDay(appointmentDate);

        } catch (Exception e) {

        }
    }

    // Search Appointments
    public void searchAppointments(){
        System.out.print("Enter Appointment ID or Patient Name to search: ");
        String searchTerm = scanner.nextLine();
        Appointment appointment = clinic.searchAppointment(searchTerm);
        if (appointment != null) {
            System.out.println(appointment.getDetails());
        } else {
            System.out.println("Appointment not found.");
        }
    }
    //Edit Appointment
    public void updateAppointment(){

        String appointmentId = question("Please Enter the Appointment Id");
        List<Appointment> appointments = clinic.getAppointments();
        Appointment appointment = appointments.stream().filter(i->i.getAppointmentId().equals(appointmentId)).findFirst().orElse(null);
        if(appointment == null){
            print("No such Appointment detail found!!!");
            return;
        }
        if(confirm("DO you want to reschedule the Appointment")){
            this.rescheduleAppointment(appointment);
        }
        Patient patient = appointment.getPatient();
        if(confirm("DO you want to Change the Patient Name")){
            patient.setName(question("Please enter your Patient Name: "));
            appointment.setPatient(patient);
        }
        if(confirm("DO you want to Change the Patient Email")){
            patient.setEmail(question("Please enter your Patient Email: "));
            appointment.setPatient(patient);
        }
        if(confirm("DO you want to Change the Patient NIC")){
            patient.setNic(question("Please enter your Patient NIC: "));
            appointment.setPatient(patient);
        }
        if(confirm("DO you want to Change the Patient Phone Number")){
            patient.setPhoneNumber(question("Please enter your Patient Phone Number: "));
            appointment.setPatient(patient);
        }

        print("Appointment Updated Successfully!!!");

    }

    private void rescheduleAppointment(Appointment appointment){
        LocalDateTime appointmentDate = getAppointmentDate(appointment.getDermatologist());
        appointment.setDay(appointmentDate);
        print("Appointment Rescheduled Successfully!!!");

    }

    public void genarateInvoice (){

        String appointmentId = question("Please Enter the Appointment Id");
        List<Appointment> appointments = clinic.getAppointments();

        Appointment appointment = appointments.stream().filter(i->i.getAppointmentId().equals(appointmentId)).findFirst().orElse(null);
        if(appointment == null){
            print("No such Appointment detail found!!!");
            return;
        }

        int invoiceId = appointments.indexOf(appointment)+1;
        print("Invoice");

        print("Invoice ID: " +invoiceId );
        print("Patient Name: " +appointment.getPatient().getName());
        print("Treatment Price: " + appointment.getTreatment().getPrice());
        print("Registration Fee: " + appointment.getTreatment().getRegistrationFee());
        print("Total Fee: " + appointment.getTreatment().total());
        print("Tax (2.5%) Amount: " + appointment.getTreatment().calTax());
        print("Grand Total with Tax : " + appointment.getTreatment().calculateTotalWithTax());

    }

    public void exit(){
        System.out.println("Thank you for using the clinic system.");
        scanner.close();
    }

}
