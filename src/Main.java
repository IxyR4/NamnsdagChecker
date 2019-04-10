import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {


    private static final HashMap<String, String> myNamnsdagMap = new HashMap<>();
    public static final String NAMNSDAGAR_TXT = "namnsdagar.txt";

    public static void main(String[] args) {

        prepareNamnsDagMap();

        //This string is the total date, used for checking if the date entered is valid
        String dob;

        System.out.println("-----------------------------------------------");
        System.out.println("| Skriv ifall det ska vara [namn] eller [dag]: |");
        System.out.println("-----------------------------------------------");
        System.out.println("");

        Scanner scanner = new Scanner(System.in);

        //Get their input as a String
        String decision = scanner.next();

        if (decision.equals("namn")) {

            System.out.println("--------------------------------");
            System.out.println("| Skriv in ett namn. T.ex Emil |");
            System.out.println("--------------------------------");

            Scanner namnInput = new Scanner(System.in);
            String namn = namnInput.nextLine().toLowerCase();
            String datum = nameToDate(namn);

            if (datum == null) {

                System.out.println("---------------------------------");
                System.out.println("|!|FELKOD: NAMN HITTADES INTE |!|");
                System.out.println("---------------------------------");
            } else {
                System.out.println("----------------------------------");
                System.out.println("| " + datum + " är när " + namn + " har namnsdag |");
                System.out.println("----------------------------------");
            }


        }   //This is for exiting. Because no-one like stopping it manually.
            else if (decision.equals("exit")){
                System.exit(0);
        }


        else if (decision.equals("dag")) {
            //If name is not true, then there is only date left to choose.
            System.out.println("Skriv in en månad i nummer");

            String månad = scanner.next();
            System.out.println("Du valde månad nummer " + månad);


            String date = null;
            switch (månad) {
                case "1":
                    System.out.println("Du valde Januari");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "2":
                    System.out.println("Du valde Februari");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "3":
                    System.out.println("Du valde Mars");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "4":
                    System.out.println("Du valde April");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "5":
                    System.out.println("Du valde Maj");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "6":
                    System.out.println("Du valde Juni");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "7":
                    System.out.println("Du valde Juli");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "8":
                    System.out.println("Du valde Augusti");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "9":
                    System.out.println("Du valde September");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "10":
                    System.out.println("Du valde Oktober");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "11":
                    System.out.println("Du valde November");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                case "12":
                    System.out.println("Du valde December");
                    System.out.println("Skriv in ett vilken dag i nummer");
                    date = scanner.next();
                    break;
                default:
                    System.out.println("Denna månaden finns inte");
                    break;
            }
            int datum = Integer.parseInt(date);
            System.out.println("Denna person har namnsdag den " + datum + " i " + månad + ":");

            dob = datum + "/" + månad + "/" + 2019;

            if(DateChecker.valDOB(dob)) {
                //Number verified
            }
            else {
                System.out.println("Detta nummret finns inte i månaden. Var vänlig starta om processen. :)");
            }

            System.out.println(myNamnsdagMap.get(datum + " " + månad));


        } //Avslutar dag

        //Felkod
        else {
            System.out.println("Du valde varken dag eller namn. Var vänlig kör om processen.");
        }

        //This is a delay to make everything a bit more comfy :)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Main.main(null);

    }
    private static void prepareNamnsDagMap() {
        List<String> namnsdagar = null;

        try {
            namnsdagar = Files.readAllLines(Paths.get(NAMNSDAGAR_TXT));
        }
        catch (IOException e) {
            System.out.print("ERROR: COULD NOT READ FILE " + NAMNSDAGAR_TXT);
            System.exit(1);
        }
        for(String rad:namnsdagar){
            String[] a= rad.split("\\s+");

            String[] daysList = {a[0], a[1]};
            String day = String.join(" ", daysList);

            String name = "";
            for(int i = 2; i < a.length; i++)
                name += a[i];

            myNamnsdagMap.put(day, name);
        }


    }

    private static String nameToDate(String namn) {
        for (String key:myNamnsdagMap.keySet()) {
            if(myNamnsdagMap.get(key).toLowerCase().contains(namn)) {
                return key;
            }
        }

        return null;

    }
}


//dag 1-31
//månad 1-12




//Zacke was here :)