import Services.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Clinic extends Service {
    private List<Dermatologist> dermatologists;
    private List<Treatment> treatments;
    private List<Appointment> appointments;
    private int appointmentCounter;

    public Clinic() {
        this.dermatologists = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.appointmentCounter = 1; 

        
        dermatologists.add(new Dermatologist("Dr. Samarasinghe"));
        dermatologists.add(new Dermatologist("Dr. Kevin"));

        
        treatments.add(new Treatment("Acne Treatment - Rs 2750.00", 2750.00, 500.00));
        treatments.add(new Treatment("Skin Whitening Rs. 7650.00", 7650.00, 500.00));
        treatments.add(new Treatment("Mole Removal Rs. 3850.00 ", 3850.00, 500.00));
        treatments.add(new Treatment("Laser Treatment Rs. 12500.00", 12500.00, 500.00));
    }

    public List<Dermatologist> getDermatologists() {
        return dermatologists;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void makeAppointment(Patient patient, LocalDateTime day, Dermatologist dermatologist, Treatment treatment) {

        String appointmentId = "" + appointmentCounter++;
        Appointment appointment = new Appointment(appointmentId, day, patient, dermatologist, treatment);
        appointments.add(appointment);
        System.out.println("Appointment booked successfully! " );
        System.out.println("-".repeat(35));

        print(appointment.getDetails());
    }

    public void viewAppointmentsByDay(LocalDate day) {
        List<Appointment> filterAppointments = appointments.stream().filter(t -> t.getDay().toLocalDate().equals(day)).toList();
        for(Appointment appointment: filterAppointments){
            print(appointment.getDetails());
            print("-".repeat(10));
        }
    }
    public Appointment searchAppointment(String searchTerm) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equalsIgnoreCase(searchTerm) ||
                appointment.getDetails().contains(searchTerm)) {
                return appointment;
            }
        }
        return null;
    }


}
