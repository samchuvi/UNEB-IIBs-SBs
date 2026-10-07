/*
 * BIT2115 - IIBs & SBs (Instance Initialization Blocks & Static Blocks)
 * UNEB Candidate Registration System
 *
 * This program shows:
 *  1. Static block  -> runs ONCE for the whole application (loads exam settings)
 *  2. Instance block -> runs EVERY time a Student object is created (sets defaults)
 *  3. The order:  static block  ->  instance block  ->  constructor
 */

class Student {

    // ---------- STATIC (class-level) variables: shared by ALL students ----------
    static int examYear;
    static double registrationFee;
    static String gradingPolicy;
    static int totalRegistered;

    // ---------- INSTANCE (object-level) variables: each student has their own ----------
    String name;
    String school;
    String indexNumber;
    String registrationStatus;
    String examCentre;

    // ================= STATIC BLOCK =================
    // Runs only ONCE, when the Student class is first loaded into memory.
    // Used to load the general exam settings from the "central configuration".
    static {
        System.out.println(">>> [STATIC BLOCK] Loading UNEB examination settings...");
        examYear = 2026;
        registrationFee = 165000;          // UGX
        gradingPolicy = "Grades D1 - F9";
        totalRegistered = 0;
        System.out.println("    Exam Year        : " + examYear);
        System.out.println("    Registration Fee : UGX " + String.format("%,.0f", registrationFee));
        System.out.println("    Grading Policy   : " + gradingPolicy);
        System.out.println(">>> [STATIC BLOCK] Settings loaded. (This runs only ONE time)\n");
    }

    // ================= INSTANCE INITIALIZATION BLOCK =================
    // Runs EVERY time a new Student object is created, just BEFORE the constructor.
    // Used to give every new student default values.
    {
        totalRegistered++;
        registrationStatus = "PENDING";
        examCentre = "Not yet assigned";
        System.out.println("  -> [INSTANCE BLOCK] Setting default values for new candidate #" + totalRegistered);
        System.out.println("     Status = " + registrationStatus + ", Centre = " + examCentre);
    }

    // ================= CONSTRUCTOR =================
    // Runs AFTER the instance block. Sets the details specific to this student.
    Student(String name, String school, String centre) {
        System.out.println("  -> [CONSTRUCTOR] Registering " + name + " from " + school);
        this.name = name;
        this.school = school;
        this.indexNumber = "U" + examYear + "/" + String.format("%03d", totalRegistered);
        this.examCentre = centre;
        this.registrationStatus = "REGISTERED";
        System.out.println("     Registration complete!\n");
    }

    void displayDetails() {
        System.out.println("  Index No : " + indexNumber);
        System.out.println("  Name     : " + name);
        System.out.println("  School   : " + school);
        System.out.println("  Centre   : " + examCentre);
        System.out.println("  Status   : " + registrationStatus);
        System.out.println("  Fee Paid : UGX " + String.format("%,.0f", registrationFee) + " | Exam Year: " + examYear);
        System.out.println("  ----------------------------------------");
    }
}

public class UNEBRegistration {

    // Static block of the main class - runs first, before main() starts
    static {
        System.out.println("=== [MAIN CLASS STATIC BLOCK] UNEB Registration System starting up ===\n");
    }

    public static void main(String[] args) {
        System.out.println("=== [MAIN METHOD] Starting candidate registration ===\n");

        // The first time we use Student, its static block runs (only once)
        Student s1 = new Student("Nakato Sarah", "Gayaza High School", "Centre 101 - Gayaza");
        Student s2 = new Student("Okello James", "St. Mary's College Kisubi", "Centre 205 - Kisubi");
        Student s3 = new Student("Akello Grace", "Mt. St. Mary's Namagunga", "Centre 310 - Namagunga");

        System.out.println("=== REGISTERED CANDIDATES ===");
        s1.displayDetails();
        s2.displayDetails();
        s3.displayDetails();

        System.out.println("Total candidates registered: " + Student.totalRegistered);
        System.out.println("\nNOTICE: The static block ran ONCE, but the instance block");
        System.out.println("and constructor ran " + Student.totalRegistered + " times (once per student).");
        System.out.println("Order: STATIC BLOCK -> INSTANCE BLOCK -> CONSTRUCTOR");
    }
}