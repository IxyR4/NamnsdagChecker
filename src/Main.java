//Imports a lot of funny stufflings and stuff and more stuff. (The hashmap is not the type of 420/hash you are thinking about)
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;



public class Main {




    //Creates a HashMap
    private static final HashMap<String, String> myNamesdayMap = new HashMap<>();

    public static final String NAMNSDAGAR_TXT = "namnsdagar.txt";
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws IOException, URISyntaxException {
        boolean on = true;
        while(on) {

            printFirstMessage();
            prepareNamesdayMap();

            printDecisionUI();
            String decision = getInputString().toLowerCase();

            if (decision.equals("namn")) {

                printNameUI();
                String namn = getInputString().toLowerCase();

                String datum = nameToDate(namn);
                //If the "namn" (name) could not be found in "namnsdagar.txt", it will throw a failure code.
                if (datum == null) {
                    printNameFailcode();
                } else {
                    printResult(datum, namn);
                }

            }   //This is for exiting. Because no-one like stopping the code up by the right corner manually ;)
            else if (decision.equals("exit")) {
                exit();
            } else if (decision.equals("list")) {
                list();
            } else if (decision.equals("dag")) {
                //If name is not true, then there is only date left to choose.
                System.out.println("Skriv in en månad. T.ex Juli");

                String månadNamn = getInputString().toLowerCase();
                int månadNr = calculateMonth(månadNamn);
                System.out.println("Skriv in vilken dag i nummer");
                int datum = getInputInt();

                String checkDate = datum + "/" + månadNr + "/" + 2019;

                if (!DateChecker.valDOB(checkDate)) {
                    printInvalidNumber();
                } else {
                    System.out.println("Denna person har namnsdag den " + datum + " i " + månadNamn + ":");
                    System.out.println(myNamesdayMap.get(datum + " " + månadNr));
                }
            } //Ends decision "day"
            else {
                printDecisionFailcode();
            }

            comfyDelay();
        }
    }

    //The main function ends here



    //Functions and such are starting here

    //UI FUNCTIONS
    private static void pr
    private static void printInvalidNumber(){
        System.out.println("------------------------------------------");
        System.out.println("ERROR: Detta nummret finns inte i månaden.");
        System.out.println("------------------------------------------");
    }
    private static void printDecisionFailcode() {
        System.out.println("Du valde varken dag eller namn. Startar om processen.");
    }

    private static void printDecisionUI() {
        System.out.println("-----------------------------------------------");
        System.out.println("| Skriv ifall det ska vara [namn] eller [dag]: |");
        System.out.println("-----------------------------------------------");
        System.out.println("");
    }

    private static void printNameUI() {
        System.out.println("--------------------------------");
        System.out.println("| Skriv in namn. T.ex Emil |");
        System.out.println("--------------------------------");
    }

    private static void printNameFailcode() {
        System.out.println("---------------------------------");
        System.out.println("|!|FELKOD: Namn hittades inte |!|");
        System.out.println("---------------------------------");
    }
    
    private static void printResult(String datum, String namn){

        System.out.println("----------------------------------");
        System.out.println("| " + datum + " är när " + namn + " har namnsdag |");
        System.out.println("----------------------------------");
    }

    //BACKEND FUNCTIONS

    private static void prepareNamesdayMap() {
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

            myNamesdayMap.put(day, name);
        }


    }

    private static String nameToDate(String namn) {
        for (String key:myNamesdayMap.keySet()) {
            if(myNamesdayMap.get(key).toLowerCase().contains(namn)) {
                return key;
            }
        }

        return null;

    }

    public static void comfyDelay(){
        //This is a delay to make everything a bit more comfy :)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printDots() {
        for (int dotCount = 0; dotCount < 3; dotCount++) {
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void list() throws URISyntaxException, IOException {

        System.out.println("Ange vilken månad i listan du vill se. T.ex November");

        String month = getInputString();
        int monthNumber = calculateMonth(month);
        List<String> lines;
        switch (monthNumber) {

            case 1:
                lines = Files.readAllLines(Paths.get("Months/January"));
                break;
            case 2:
                lines = Files.readAllLines(Paths.get("Months/February"));
                break;
            case 3:
                lines = Files.readAllLines(Paths.get("Months/Mars"));
                break;
            case 4:
                lines = Files.readAllLines(Paths.get("Months/April"));
                break;
            case 5:
                lines = Files.readAllLines(Paths.get("Months/May"));
                break;
            case 6:
                lines = Files.readAllLines(Paths.get("Months/June"));
                break;
            case 7:
                lines = Files.readAllLines(Paths.get("Months/July"));
                break;
            case 8:
                lines = Files.readAllLines(Paths.get("Months/August"));
                break;
            case 9:
                lines = Files.readAllLines(Paths.get("Months/September"));
                break;
            case 10:
                lines = Files.readAllLines(Paths.get("Months/October"));
                break;
            case 11:
                lines = Files.readAllLines(Paths.get("Months/November"));
                break;
            case 12:
                lines = Files.readAllLines(Paths.get("Months/December"));
                break;

            default:
                lines = Collections.emptyList();
                break;


        }
            for (String s : lines) {
                System.out.println(s);
            }

    }

    public static String getInputString() {
        String input = SCANNER.next().toLowerCase();
        return input;
    }

    public static void exit() {
        System.exit(0);
    }

    public static int getInputInt() {
        String input = SCANNER.next();
        return Integer.parseInt(input);
    }

    private static int calculateMonth(String month) {
        // TODO: Få in en månad som string, retunera vilken nummer den månaden har
        // Om månaden inte finns, retunera -1
        int monthNumber = 0;

        switch (month) {

            case "januari":
                monthNumber = 1;
                break;

            case "februari":
                monthNumber = 2;
                break;

            case "mars":
                monthNumber = 3;
                break;

            case "april":
                monthNumber = 4;
                break;

            case "maj":
                monthNumber = 5;
                break;

            case "juni":
                monthNumber = 6;
                break;

            case "juli":
                monthNumber = 7;
                break;

            case "augusti":
                monthNumber = 8;
                break;

            case "september":
                monthNumber = 9;
                break;

            case "oktober":
                monthNumber = 10;
                break;

            case "november":
                monthNumber = 11;
                break;

            case "december":
                monthNumber = 12;
                break;

            default:
                System.out.println("Kunde inte hitta månad " + month);
                printDots();
                System.out.println(" ");
                exit();
                return -1;
            }
        return monthNumber;
        }

    }
    
//TODO: Gör funktonen list.
