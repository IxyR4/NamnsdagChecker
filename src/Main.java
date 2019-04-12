//Imports a lot of funny stufflings and stuff and more stuff. (The hashmap is not the type of 420/hash you are thinking about)
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
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

    private static final int ERROR = -1;

    public static void main(String[] args) throws IOException, URISyntaxException {
        boolean on = true;
        boolean isPrinted = false;
        while(on) {

            printFirstMessage();
            prepareNamesdayMap();

            printDecisionUI();
            String decision = getInputString().toLowerCase();

            switch (decision) {
                case "namn": {

                    printNameUI();
                    String namn = getInputString().toLowerCase();

                    String datum = nameToDate(namn);
                    //If the "namn" (name) could not be found in "namnsdagar.txt", it will throw a failure code.
                    if (datum == null) {
                        printNameFailcode();
                    } else {
                        printResult(datum, namn);
                    }

                    break;
                }
                case "exit":
                    exit();
                    break;
                case "list":
                    list();

                    break;
                case "help":
                    printHelp();
                    break;
                case "unicorn":
                    printUnicorn();
                    break;
                case "dag": {
                    //If name is not true, then there is only date left to choose.
                    System.out.println("Skriv in en månad. T.ex Juli");

                    String månadNamn = getInputString().toLowerCase();
                    int månadNr = calculateMonth(månadNamn);
                    System.out.println("Skriv in vilken dag i nummer");
                    int datum = getInputInt();

                    String checkDate = datum + "/" + månadNr + "/" + 2019;
                    if(datum == ERROR) {
                        System.out.println("Du måste skriva ett nummer.");
                    }
                    else if (!DateChecker.valDOB(checkDate)) {
                        printInvalidNumber();
                    } else {
                        System.out.println("Denna person har namnsdag den " + datum + " i " + månadNamn + ":");
                        System.out.println(myNamesdayMap.get(datum + " " + månadNr));
                    }
                    break;
                }
                default:
                    printDecisionFailcode();
                    break;
            }

            printDots();
        }
    }

    //The main function ends here



    //Functions and such are starting here

    //UI FUNCTIONS
    private static void printFirstMessage() {
        int i = 1;
        if (i == 1) {
            System.out.println(" ");
            System.out.println("_______________________________________________________");
            System.out.println("Välkommen till mitt namnsdag-program! Skrivet i Java! |");
            System.out.println("Dessa funktioner finns att välja mellan:              |");
            System.out.println("---------------------------------------------         |");
            System.out.println("|[namn] [dag] [list] [exit] [help] [unicorn]|         |");
            System.out.println("---------------------------------------------         |");
            System.out.println("Ha så kul! :D                                         |");
            System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
            i = 0;
        }
        else {
            System.out.println("SYSTEM ERROR IN FUNCTION printFirstMessage, EXITING PROGRAM");
            exit();
        }
    }

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

    private static void printUnicorn() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("                           ___________ _");
        System.out.println(" \\/                    __/   .::::.-'-(/-/)");
        System.out.println("                    _/:  .::::.-' .-'\\/\\_`*******          __ (_))");
        System.out.println("       \\/          /:  .::::./   -._-.  d\\|               (_))_(__))");
        System.out.println("                    /: (\"\"\"\"/    '.  (__/||           (_))__(_))--(__))");
        System.out.println("                  \\::).-'  -._  \\/ \\\\/\\|");
        System.out.println("              __ _ .-'`)/  '-'. . '. |  (i_O");
        System.out.println("          .-'      \\       -'      '\\|");
        System.out.println("     _ _./      .-'|       '.  (    \\\\                         % % %");
        System.out.println("  .-'   :      '_  \\         '-'\\  /|/      @ @ @             % % % %");
        System.out.println(" /      )\\_      '- )_________.-|_/^\\      @ @ @@@           % %\\/% %");
        System.out.println(" (   .-'   )-._-:  /        \\(/\\'-._ `.     @|@@@@@            ..|........");
        System.out.println("  (   )  _//_/|:  /          `\\()   `\\_\\     |/_@@             )'-._.-._.-");
        System.out.println("   ( (   \\()^_/)_/             )/      \\\\    /                /   /");
        System.out.println("    )  _.-\\\\.\\(_)__._.-'-.-'-.//_.-'-.-.)\\-'/._              /");
        System.out.println(".-.-.-'   _o\\ \\\\\\     '::'   (o_ '-.-' |__\\'-.-;~ ~ ~ ~ ~ ~~/   /\\");
        System.out.println("          \\ /  \\\\\\__          )_\\    .:::::::.-'\\          '- - -|");
        System.out.println("     :::''':::::^)__\\:::::::::::::::::'''''''-.  \\                '- - - ");
        System.out.println("    :::::::  '''''''''''   ''''''''''''':::. -'\\  \\");
        System.out.println("_____':::::_____________________________________\\__\\______________________\n");
        System.out.println("");
    }

    private static void printNameFailcode() {
        System.out.println("------------------------------------------------");
        System.out.println("Tyvvär men namnet du skrev in har ingen namnsdag");
        System.out.println("------------------------------------------------");
    }

    private static void printHelp() {
        System.out.println("_________________________________________________________________________________");
        System.out.println("You are a amazing person and i hope that your day is great!");
        System.out.println("And don't listen to the negative people, they don't deserve to make you feel bad!");
        System.out.println("Now go listen to some music and drink some water. (or watch memes ;))");
        System.out.println("_________________________________________________________________________________");
        comfyDelay();
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
        try {
            String input = SCANNER.next();
            return Integer.parseInt(input);
        }
        catch (NumberFormatException error) {
            return ERROR;
        }
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

//Code written by Sebastian Besic. @HiQ-Karlskrona 2019-04-08 to 2019-04-12
//Project given to me by Klas Nyström. Thanks HiQ for the amazing adventure for this "PRAO". It was fun and very educating.
//For contact, find IxyR4 on gitHub, or try to track me down ;)
