import java.util.*;

public class Scedule {
    ArrayList<Flight> flights = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Scedule() {
      //Data By AI
        Passenger p1 = new Passenger("Max", "Muster", null);
        Passenger p2 = new Passenger("Anna", "Schneider", null);
        Passenger p3 = new Passenger("Luca", "Rossi", null);
        Passenger p4 = new Passenger("Sofia", "Meier", null);

        ArrayList<Passenger> passengersTA = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));
        Flight flightTurkishairlines = new Flight(
                "Turkish Airlines",
                "2025-09-15T14:30",
                "Gaziantep",
                "27AC",
                passengersTA
        );

        Passenger p5 = new Passenger("Liam", "O'Connor", null);
        Passenger p6 = new Passenger("Elena", "Petrova", null);
        Passenger p7 = new Passenger("Omar", "Hassan", null);
        ArrayList<Passenger> passengersPG = new ArrayList<>(Arrays.asList(p5, p6, p7));
        Flight flightPegasus = new Flight("Pegasus Airlines", "2025-09-16T09:45", "Istanbul", "PC456", passengersPG);

        Passenger p8 = new Passenger("Sofia", "Martinez", null);
        Passenger p9 = new Passenger("David", "Miller", null);
        Passenger p10 = new Passenger("Fatma", "Aydin", null);
        Passenger p11 = new Passenger("Chen", "Wei", null);
        ArrayList<Passenger> passengersSX = new ArrayList<>(Arrays.asList(p8, p9, p10, p11));
        Flight flightSunExpress = new Flight("SunExpress", "2025-09-17T18:20", "Antalya", "XQ789", passengersSX);

        Passenger p12 = new Passenger("Mehmet", "Demir", null);
        Passenger p13 = new Passenger("Julia", "Fischer", null);
        Passenger p14 = new Passenger("Carlos", "Silva", null);
        ArrayList<Passenger> passengersAJ = new ArrayList<>(Arrays.asList(p12, p13, p14));
        Flight flightAnadolu = new Flight("AnadoluJet", "2025-09-18T12:10", "Ankara → Trabzon", "AJ321", passengersAJ);


        flights.add(flightTurkishairlines);
        flights.add(flightPegasus);
        flights.add(flightSunExpress);
        flights.add(flightAnadolu);
    }

    public void registerUser() {
        String flightNumber;
        boolean check = false;
        int placement = 0;

        do {
            System.out.println("Which flight would you like to register for?");
            System.out.println("Please enter a valid flight number ");
            flightNumber = sc.next();
            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getFlightNumber().equalsIgnoreCase(flightNumber)) {
                    check = true;
                    placement = i;
                    break;
                }
            }
            if (!check) {
                System.out.println("Flight not found. Try again.");
            }
        } while (!check);

        System.out.println("Please enter your firstname ");
        String firstName = sc.next();
        System.out.println("Please enter your lastname ");
        String lastName = sc.next();
        System.out.println("Please enter your email ");
        String email = sc.next();
        Passenger passenger = new Passenger(firstName, lastName, email);
        flights.get(placement).getPassengers().add(passenger);
        MailVerification mv = new MailVerification();
        mv.sendVerificationMail(email, flights.get(placement));

        System.out.println("Registration successful for flight " + flights.get(placement).getFlightNumber());
    }

    public void showAll() {
        for (Flight f : flights) {
            System.out.println("Airlines: " + f.getAirLines() +
                    "\nDestination: " + f.getDestination() +
                    "\nFlight number: " + f.getFlightNumber() +
                    "\nDeparture: " + f.getDeparture());
            System.out.println("PASSENGER LIST:");
            for (Passenger p : f.getPassengers()) {
                System.out.println(p.getFirstName() + " " + p.getLastName());
            }
            System.out.println("-------------------------");
        }
    }

    public void removePassenger() {
        boolean check = false;
        do {
            System.out.println("Please enter a valid flight number");
            String flightNumber = sc.next();
            System.out.println("Please enter the passenger name ");
            String passengerName = sc.next();

            for (Flight f : flights) {
                if (f.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                    Iterator<Passenger> it = f.getPassengers().iterator(); //By AI to prevent exceptions
                    while (it.hasNext()) {
                        Passenger p = it.next();
                        if (p.getFirstName().equalsIgnoreCase(passengerName)) {
                            System.out.println("Passenger: " + p.getFirstName() + " " + p.getLastName() +
                                    "\nFor flight: " + f.getFlightNumber() + " is cancelled!");
                            it.remove();
                            check = true;
                            break;
                        }
                    }
                }
            }
        } while (!check);
    }

    public void searchForpassenger() {
        System.out.println("Please enter the firstname");
        String name = sc.next();
        System.out.println("Please enter the lastname");
        String lastName = sc.next();
        boolean check = false;

        for (Flight f : flights) {
            for (Passenger p : f.getPassengers()) {
                if (p.getFirstName().equalsIgnoreCase(name) && p.getLastName().equalsIgnoreCase(lastName)) {
                    System.out.println("Passenger: " + name);
                    System.out.println("For flight: " + f.getFlightNumber());
                    check = true;
                    break;
                }
            }
        }

        if (!check) {
            System.out.println("No flight with that passenger was found");
        }
    }
}
