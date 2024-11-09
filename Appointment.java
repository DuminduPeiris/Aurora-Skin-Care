import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private String appointmentId;
    private LocalDateTime day;
    private Patient patient;
    private Dermatologist dermatologist;
    private Treatment treatment;

    public Appointment(String appointmentId, LocalDateTime day, Patient patient, Dermatologist dermatologist, Treatment treatment) {
        this.appointmentId = appointmentId;
        this.day = day;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.treatment = treatment;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public Patient getPatient() {
        return patient;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getDetails() {
        String appointmentDate = day.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"));
        return String.format("Appointment ID: %s\nDate & Time:  %s\nPatient Name : %s\nNIC: %s\nDermatologist: %s\nTreatment: %s\nRegistration Fee: %s\nTax 2.5 Amount: %s\nGrand Total: LKR %.2f",
                appointmentId, appointmentDate, patient.getName(),patient.getNic(), dermatologist.getName(), treatment.getName(), treatment.getRegistrationFee(),treatment.calTax(),treatment.calculateTotalWithTax());

    }

    public LocalDateTime getDay() {
        return day;
    }
}
