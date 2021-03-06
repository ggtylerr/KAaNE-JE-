/**
 * Unfair Cipher, started work on 7:30 AM 5/31/2019
 *
 * FINALLY finished ver 1 on 12:11 PM 6/6/2019
 *
 * Version 0.1
 */

import be.pcl.swing.ImprovedFormattedTextField;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

public class unfairCipher {
    static JFrame f;
    private static char[][] charTable;
    private static Point[] positions;
    public static void module() {
        System.out.println("[UNFAIR CIPHER]");
        f = new JFrame("KAaNE [UNFAIR CIPHER]");
        ImageIcon icon = new ImageIcon("imgs/icons/Unfair Cipher.png");
        f.setIconImage(icon.getImage());
        // Edgework
        File configFile = new File("config.properties");
        Properties props = new Properties();
        try {
            FileReader reader = new FileReader(configFile);
            props.load(reader);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        // Text Fields
        JTextField encryptMsg = new JTextField();
        encryptMsg.setBounds(3,5,290,20);
        encryptMsg.setDocument(new JTextFieldLimit(40));
        NumberFormat integerNumberInstance = NumberFormat.getIntegerInstance();
        ImprovedFormattedTextField moduleIDTB = new ImprovedFormattedTextField(integerNumberInstance,0);
        moduleIDTB.setBounds(5,30,50,20);
        ImprovedFormattedTextField strikeTB = new ImprovedFormattedTextField(integerNumberInstance,0);
        strikeTB.setBounds(5,50,50,20);
        // Labels
        JLabel moduleIDLabel = new JLabel("Module ID");
        moduleIDLabel.setBounds(60,30,100,20);
        JLabel strikeLabel = new JLabel("Strikes");
        strikeLabel.setBounds(60,50,100,20);
        // Button
        JButton button = new JButton("OK");
        button.setBounds(200,50,75,20);
        // Output Text Area
        JTextArea output = new JTextArea();
        output.setBounds(5,75,285,100);
        output.setEditable(false);
        output.setLineWrap(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        output.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,1,1,1)));
        // Add everything
        f.add(encryptMsg); f.add(moduleIDTB); f.add(strikeTB);
        f.add(moduleIDLabel); f.add(strikeLabel); f.add(button);
        f.add(output);
        // Define JFrame
        f.setLayout(null);
        f.setSize(300,225);
        f.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
        // Logic
        button.addActionListener((ActionEvent e) -> {
            System.out.println("Ah shit...here we go again.");
            // Serial Number
            String sn1 = props.getProperty("sn1");
            String sn2 = props.getProperty("sn2");
            String sn3 = props.getProperty("sn3");
            String sn4 = props.getProperty("sn4");
            String sn5 = props.getProperty("sn5");
            String sn6 = props.getProperty("sn6");
            String sn = sn1 + sn2 + sn3 + sn4 + sn5 + sn6;
            // Module ID
            String moduleID = moduleIDTB.getText();
            // Port Plates
            String plates = props.getProperty("plates");
            // Battery Holders
            String batteryHolders = props.getProperty("batteryHolders");
            System.out.println("====CALCULATING KEYS====");
            // Key A
            String keyA = keyA(sn4,sn5,sn,moduleID,plates,batteryHolders);
            // Key B
            String keyB = keyB();
            // Key C
            System.out.println("====KEY C====");
            String keyC = playfair(keyB,keyA,true);
            System.out.println("Key C - " + keyC);
            // MSG
            String msg = encryptMsg.getText();
            // STEP 1
            System.out.println("====OFFSET CALC====");
            int offset = 0;
            // Port Types
            String dvidStr = props.getProperty("dvid");
            String parallelStr = props.getProperty("parallel");
            String ps2Str = props.getProperty("ps2");
            String rj45Str = props.getProperty("rj45");
            String serialStr = props.getProperty("serial");
            String stereoRCAStr = props.getProperty("stereoRCA");
            if (dvidStr.equals("1")) offset -= 2;
            if (parallelStr.equals("1")) offset -= 2;
            if (ps2Str.equals("1")) offset -= 2;
            if (rj45Str.equals("1")) offset -= 2;
            if (serialStr.equals("1")) offset -= 2;
            if (stereoRCAStr.equals("1")) offset -= 2;
            System.out.println("Offset after port types: " + offset);
            // Port Plate
            offset += Integer.parseInt(plates);
            System.out.println("Offset after port plates: " + offset);
            // Serial Number
            if (string.isVowel(sn1)) offset -= 2;
            else if (!string.isDigit(sn1)) offset += 1;
            if (string.isVowel(sn2)) offset -= 2;
            else if (!string.isDigit(sn2)) offset += 1;
            if (string.isVowel(sn3)) offset -= 2;
            else if (!string.isDigit(sn3)) offset += 1;
            if (string.isVowel(sn4)) offset -= 2;
            else if (!string.isDigit(sn4)) offset += 1;
            if (string.isVowel(sn5)) offset -= 2;
            else if (!string.isDigit(sn5)) offset += 1;
            if (string.isVowel(sn6)) offset -= 2;
            else if (!string.isDigit(sn6)) offset += 1;
            System.out.println("Offset after SN: " + offset);
            // Indicators
            int bobInt = Integer.parseInt(props.getProperty("bob"));
            int carInt = Integer.parseInt(props.getProperty("car"));
            int clrInt = Integer.parseInt(props.getProperty("clr"));
            int frkInt = Integer.parseInt(props.getProperty("frk"));
            int frqInt = Integer.parseInt(props.getProperty("frq"));
            int indInt = Integer.parseInt(props.getProperty("ind"));
            int msaInt = Integer.parseInt(props.getProperty("msa"));
            int nsaInt = Integer.parseInt(props.getProperty("nsa"));
            int sigInt = Integer.parseInt(props.getProperty("sig"));
            int sndInt = Integer.parseInt(props.getProperty("snd"));
            int trnInt = Integer.parseInt(props.getProperty("trn"));
            int bobLitInt = Integer.parseInt(props.getProperty("bobLit"));
            int carLitInt = Integer.parseInt(props.getProperty("carLit"));
            int clrLitInt = Integer.parseInt(props.getProperty("clrLit"));
            int frkLitInt = Integer.parseInt(props.getProperty("frkLit"));
            int frqLitInt = Integer.parseInt(props.getProperty("frqLit"));
            int indLitInt = Integer.parseInt(props.getProperty("indLit"));
            int msaLitInt = Integer.parseInt(props.getProperty("msaLit"));
            int nsaLitInt = Integer.parseInt(props.getProperty("nsaLit"));
            int sigLitInt = Integer.parseInt(props.getProperty("sigLit"));
            int sndLitInt = Integer.parseInt(props.getProperty("sndLit"));
            int trnLitInt = Integer.parseInt(props.getProperty("trnLit"));
            int bobUnlitInt = (bobInt == 1) && (bobLitInt == 0) ? 1 : 0;
            int carUnlitInt = (carInt == 1) && (carLitInt == 0) ? 1 : 0;
            int clrUnlitInt = (clrInt == 1) && (clrLitInt == 0) ? 1 : 0;
            int frkUnlitInt = (frkInt == 1) && (frkLitInt == 0) ? 1 : 0;
            int frqUnlitInt = (frqInt == 1) && (frqLitInt == 0) ? 1 : 0;
            int indUnlitInt = (indInt == 1) && (indLitInt == 0) ? 1 : 0;
            int msaUnlitInt = (msaInt == 1) && (msaLitInt == 0) ? 1 : 0;
            int nsaUnlitInt = (nsaInt == 1) && (nsaLitInt == 0) ? 1 : 0;
            int sigUnlitInt = (sigInt == 1) && (sigLitInt == 0) ? 1 : 0;
            int sndUnlitInt = (sndInt == 1) && (sndLitInt == 0) ? 1 : 0;
            int trnUnlitInt = (trnInt == 1) && (trnLitInt == 0) ? 1 : 0;
            offset = (bobUnlitInt == 1) ? offset - 2 : (bobLitInt == 1) ? offset + 2 : offset;
            offset = (carUnlitInt == 1) ? offset - 2 : (carLitInt == 1) ? offset + 2 : offset;
            offset = (clrUnlitInt == 1) ? offset - 2 : (clrLitInt == 1) ? offset + 2 : offset;
            offset = (frkUnlitInt == 1) ? offset - 2 : (frkLitInt == 1) ? offset + 2 : offset;
            offset = (frqUnlitInt == 1) ? offset - 2 : (frqLitInt == 1) ? offset + 2 : offset;
            offset = (indUnlitInt == 1) ? offset - 2 : (indLitInt == 1) ? offset + 2 : offset;
            offset = (msaUnlitInt == 1) ? offset - 2 : (msaLitInt == 1) ? offset + 2 : offset;
            offset = (nsaUnlitInt == 1) ? offset - 2 : (nsaLitInt == 1) ? offset + 2 : offset;
            offset = (sigUnlitInt == 1) ? offset - 2 : (sigLitInt == 1) ? offset + 2 : offset;
            offset = (sndUnlitInt == 1) ? offset - 2 : (sndLitInt == 1) ? offset + 2 : offset;
            offset = (trnUnlitInt == 1) ? offset - 2 : (trnLitInt == 1) ? offset + 2 : offset;
            System.out.println("Offset after Indicators: " + offset);
            // Batteries
            int batteriesTotalInt = Integer.parseInt(props.getProperty("batteriesTotal"));
            offset -= batteriesTotalInt;
            if (batteriesTotalInt == 0) offset += 10;
            System.out.println("Offset after Batteries: " + offset);
            // No ports
            if (dvidStr.equals("0") && parallelStr.equals("0") && ps2Str.equals("0") && rj45Str.equals("0") && serialStr.equals("0") && stereoRCAStr.equals("0")) offset *= 2;
            System.out.println("Offset after checking no ports: " + offset);
            // Modules
            int modulesInt = Integer.parseInt(props.getProperty("modules"));
            offset = (modulesInt > 30) ? offset / 2 : offset;
            System.out.println("Offset Final - " + offset);
            // Decrypt using caesar
            System.out.println("====CIPHERING====");
            String afterCaesar = caesarDecode(msg,offset);
            System.out.println("After Caesar Decryption: " + afterCaesar);
            // Decrypt twice with playfair
            String afterPFkeyC = playfair(keyC,afterCaesar, false);
            System.out.println("After Key C Playfair: " + afterPFkeyC);
            String finalMSG = playfair(keyA,afterPFkeyC,false);
            System.out.println("After Key A Playfair: " + finalMSG);
            // Execute Instructions
            String[] splitMSG = splitToThree(finalMSG); // split to three for easier reading
            splitMSG = fixYourselfDammit(splitMSG); // fix itself
            System.out.println("Fixed up: " + splitMSG[0] + " " + splitMSG[1] + " " + splitMSG[2] + " " + splitMSG[3]);
            String strikes = strikeTB.getText();
            boolean bobSkip = bobLitInt == 1 && batteriesTotalInt == 2;
            // Grab instructions
            String[] instructions = generateInstructions(splitMSG,Integer.parseInt(moduleID),Integer.parseInt(strikes),bobSkip);
            for (int a = 0;a<instructions.length;a++) {
                String curr = instructions[a];
                System.out.println(curr);
                if (a == 0) {
                    output.setText(curr);
                } else {
                    output.append("\n" + curr);
                }
            }
        });
    }
    private static String keyA(String sn4, String sn5, String sn, String moduleID, String plates, String batteryHolders) {
        String output = "";
        System.out.println("====KEY A====");
        System.out.println("SN: " + sn);
        // STEP 2
        int sn1INT = 0;
        int sn2INT = 0;
        int sn3INT = 0;
        int sn4INT = 0;
        int sn5INT = 0;
        int sn6INT = 0;
        if (!string.isDigit(String.valueOf(sn.charAt(0)))) sn1INT = sn.charAt(0) - 'A' + 1; else sn1INT = Integer.parseInt(String.valueOf(sn.charAt(0)));
        if (!string.isDigit(String.valueOf(sn.charAt(1)))) sn2INT = sn.charAt(1) - 'A' + 1; else sn2INT = Integer.parseInt(String.valueOf(sn.charAt(1)));
        if (!string.isDigit(String.valueOf(sn.charAt(2)))) sn3INT = sn.charAt(2) - 'A' + 1; else sn3INT = Integer.parseInt(String.valueOf(sn.charAt(2)));
        if (!string.isDigit(String.valueOf(sn.charAt(3)))) sn4INT = sn.charAt(3) - 'A' + 1; else sn4INT = Integer.parseInt(String.valueOf(sn.charAt(3)));
        if (!string.isDigit(String.valueOf(sn.charAt(4)))) sn5INT = sn.charAt(4) - 'A' + 1; else sn5INT = Integer.parseInt(String.valueOf(sn.charAt(4)));
        if (!string.isDigit(String.valueOf(sn.charAt(5)))) sn6INT = sn.charAt(5) - 'A' + 1; else sn6INT = Integer.parseInt(String.valueOf(sn.charAt(5)));
        System.out.println("Ints: " + sn1INT + ", " + sn2INT + ", " + sn3INT + ", " + sn4INT + ", " + sn5INT + ", " + sn6INT);
        int digitSingleInt = 0;
        if (sn1INT >= 20) {
            String digitSingleString = String.valueOf(sn2INT) + String.valueOf(sn3INT) + String.valueOf(sn4INT) + String.valueOf(sn5INT) + String.valueOf(sn6INT);
            digitSingleInt = Integer.parseInt(digitSingleString);
        }
        else {
            String digitSingleString = String.valueOf(sn1INT) + String.valueOf(sn2INT) + String.valueOf(sn3INT) + String.valueOf(sn4INT) + String.valueOf(sn5INT) + String.valueOf(sn6INT);
            digitSingleInt = Integer.parseInt(digitSingleString);
        }
        System.out.println("Step 2 string: " + digitSingleInt);
        // STEP 3
        if (string.isVowel(sn4) || string.isVowel(sn5)) {
            digitSingleInt /= 10;
        }
        System.out.println("Step 3 string: " + digitSingleInt);
        // STEP 4
        String digitConverted = hexConvert(digitSingleInt);
        System.out.println("Step 4 string: " + digitConverted);
        // STEP 5
        /**
        String[] digitConvertedArray = digitConverted.split("");
        List<String> convertAgain = new ArrayList<String>();
        for (int index = 0; index!=digitConvertedArray.length-1; index += 1) {
            int index2 = index + 1;
            String check = digitConvertedArray[index] + digitConvertedArray[index2];
            try {
                int checkInt = Integer.parseInt(check);
                if (checkInt >= 10 && checkInt <= 26) {
                    char chr = (char)(checkInt+'A'-1);
                    convertAgain.add(String.valueOf(chr));
                }
                else {
                    checkInt = Integer.parseInt(digitConvertedArray[index]);
                    char chr = (char)(checkInt+'A'-1);
                    convertAgain.add(String.valueOf(chr));
                }
            } catch (NumberFormatException e) {
                if (digitConvertedArray[index].equals("0")) {
                    convertAgain.add("");
                }
                else if (string.isDigit(digitConvertedArray[index])) {
                    int checkInt = Integer.parseInt(digitConvertedArray[index]);
                    char chr = (char)(checkInt+'A'-1);
                    convertAgain.add(String.valueOf(chr));
                }
                else {
                    convertAgain.add(digitConvertedArray[index]);
                }
            }
            System.out.println("Step 5 iteration " + (index + 1) + ": " + convertAgain.toArray()[index]);
        }
        String lastChr = digitConvertedArray[digitConvertedArray.length-1];
        int index = digitConvertedArray.length;
        if (lastChr.equals("0")) {
            convertAgain.add("");
        }
        else if (string.isDigit(lastChr)) {
            int checkInt = Integer.parseInt(lastChr);
            char chr = (char)(checkInt+'A'-1);
            convertAgain.add(String.valueOf(chr));
        } else {
            convertAgain.add(lastChr);
        }
        System.out.println("Step 5 iteration " + index + ": " + convertAgain.toArray()[index-1]);
        convertAgain.removeAll(Arrays.asList("",null));
        System.out.println("Step 5 String: " + Arrays.toString(convertAgain.toArray()).toUpperCase());
        **/
        List<String> convertAgain = new ArrayList<String>();
        String pleaseWork = digitConverted.replace("26", "Z").replace("25", "Y").replace("24", "X").replace("23", "W").replace("22", "V").replace("21", "U").replace("20", "T").replace("19", "S")
                .replace("18", "R").replace("17", "Q").replace("16", "P").replace("15", "O").replace("14", "N").replace("13", "M").replace("12", "L").replace("11", "K")
                .replace("10", "I").replace("9", "I").replace("8", "H").replace("7", "G").replace("6", "F").replace("5", "E").replace("4", "D")
                .replace("3", "C").replace("2", "B").replace("1", "A").replace("0", "");
        convertAgain.add(pleaseWork);
        System.out.println("Step 5 String: " + pleaseWork);
        // STEP 6
        System.out.println("Module ID - " + moduleID);
        System.out.println("Plates - " + plates);
        System.out.println("Battery Holders - " + batteryHolders);
        String moduleIDconv = "";
        String platesconv = "";
        String batteryHoldersconv = "";
        if (!moduleID.equals("0")) moduleIDconv = String.valueOf((char)(moduleID.charAt(0) + 48));
        if (!plates.equals("0")) platesconv = String.valueOf((char)(plates.charAt(0) + 48));
        if (!batteryHolders.equals("0")) batteryHoldersconv = String.valueOf((char)(batteryHolders.charAt(0) + 48));
        convertAgain.add(moduleIDconv);
        convertAgain.add(platesconv);
        convertAgain.add(batteryHoldersconv);
        convertAgain.removeAll(Arrays.asList("",null));
        output = Arrays.toString(convertAgain.toArray()).toUpperCase();
        // Remove extra characters
        String regx = "[], ";
        char[] ca = regx.toCharArray();
        for (char c : ca) {
            output = output.replace(""+c, "");
        }
        System.out.println("KEY A - " + output);
        return output;
    }
    private static String keyB () {
        System.out.println("====KEY B====");
        // Array of Arrays
        String[][] possibilities = {
                {"ABDA","FEV","DBHC","BLD","DBIE","AFEF","AFCG","CQH","DEAI","FEAA","EFAB","DECC"},
                {"ABDB","FEW","DBHD","BLE","DBIF","AFEG","AFCH","CQI","DEAA","FEAB","EFAC","DECD"},
                {"ABDC","FEX","DBHE","BLF","DBIG","AFEH","AFCI","CQA","DEAB","FEAC","EFAD","DECE"},
                {"ABDD","FEY","DBHF","BLG","DBIH","AFEI","AFCA","CQB","DEAC","FEAD","EFAE","DECF"},
                {"ABDE","FEZ","DBHG","BLH","DBII","AFEA","AFCB","CQC","DEAD","FEAE","EFAF","DED"},
                {"ABDF","FEBG","DBHH","BLI","DBIA","AFEB","AFCC","CQD","DEAE","FEAF","EFB","DEDA"},
                {"ABDG","FEBH","DBHI","BLA","DBIB","AFEC","AFCD","CQE","DEAF","FET","EFBA","DEDB"}
        };
        // Grab current day + month
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int month = calendar.get(Calendar.MONTH);
        // Get indexes
        int i1 = 0;
        int i2 = 0;
        switch (day) {
            case Calendar.MONDAY: i1 = 0; break;
            case Calendar.TUESDAY: i1 = 1; break;
            case Calendar.WEDNESDAY: i1 = 2; break;
            case Calendar.THURSDAY: i1 = 3; break;
            case Calendar.FRIDAY: i1 = 4; break;
            case Calendar.SATURDAY: i1 = 5; break;
            case Calendar.SUNDAY: i1 = 6; break;
        }
        switch (month) {
            case Calendar.JANUARY: i2 = 0; break;
            case Calendar.FEBRUARY: i2 = 1; break;
            case Calendar.MARCH: i2 = 2; break;
            case Calendar.APRIL: i2 = 3; break;
            case Calendar.MAY: i2 = 4; break;
            case Calendar.JUNE: i2 = 5; break;
            case Calendar.JULY: i2 = 6; break;
            case Calendar.AUGUST: i2 = 7; break;
            case Calendar.SEPTEMBER: i2 = 8; break;
            case Calendar.OCTOBER: i2 = 9; break;
            case Calendar.NOVEMBER: i2 = 10; break;
            case Calendar.DECEMBER: i2 = 11; break;
        }
        // output
        String output = possibilities[i1][i2];
        System.out.println("Day Index: " + i1 + ", Month Index: " + i2);
        System.out.println("KEY B: " + output);
        return output;
    }
    private static String playfair(String key, String plain, boolean encode) {
        System.out.println("Playfair Inputs: Key - " + key + " PlainText - " + plain);
        createTable(key,true);
        if (encode) return encode(prepareText(plain,true));
        else return decode(prepareText(plain,true));
    }
    private static String hexConvert (int a) {
        System.out.println("CONVERTING TO HEX...");
        String output = "";
        boolean continuing = true;
        List<String> hexRemainder = new ArrayList<String>();
        int iteration = 0;
        int b = a;
        while (continuing) {
            // Divide by 16
            b = a;
            int remainder = a % 16;
            a /= 16;
            if (b != 0) {
                // Convert remainder to Hex
                hexRemainder.add(Integer.toHexString(remainder));
                iteration += 1;
                System.out.println("Iteration " + iteration + ": " + Integer.toHexString(remainder));
            }
            else continuing = false;
        }
        Collections.reverse(hexRemainder);
        String[] hexArray = hexRemainder.toArray(new String[0]);
        output = Arrays.toString(hexArray);
        // remove unnecessary characters
        String regx = "[], ";
        char[] ca = regx.toCharArray();
        for (char c : ca) {
            output = output.replace(""+c, "");
        }
        // remove leading zeros (just in case)
        output.replaceFirst("^0+(?!$)", "");
        return output;
    }
    private static String[] splitToThree (String s) {
        List<String> outputList = new ArrayList<String>();
        int b = 3;
        for (int a=0; a<s.length();a+=3) {
            outputList.add(s.substring(a,b));
            b += 3;
        }
        String[] output = new String[outputList.size()];
        outputList.toArray(output);
        return output;
    }
    private static String[] generateInstructions(String[] msg, int moduleID, int strikes, boolean bobSkip) {
        List<String> outputList = new ArrayList<String>();
        int colorPress = 0;
        String lastAdd = "";
        boolean lastAddCLR = false;
        // Surround in for statement for repeats
        for (int a=0;a<msg.length;a++) {
            String curr = msg[a];
            // PCR
            if (curr.equals("PCR")) {outputList.add("[PCR] Press Red"); lastAdd = "Press Red"; colorPress++; lastAddCLR = true;}
            // PCG
            if (curr.equals("PCG")) {outputList.add("[PCG] Press Green"); lastAdd = "Press Green"; colorPress++; lastAddCLR = true;}
            // PCB
            if (curr.equals("PCB")) {outputList.add("[PCB] Press Blue"); lastAdd = "Press Blue"; colorPress++; lastAddCLR = true;}
            // SUB
            if (curr.equals("SUB")) {
                outputList.add("[SUB] Press Outer when the seconds on the timer match");
                lastAdd = "Press Outer when the seconds on the timer match";
            }
            // MIT
            if (curr.equals("MIT")) {
                int merc = moduleID + colorPress + (a + 1);
                int mercmod = merc % 10;
                outputList.add("[MIT] Press Inner when last digit is " + mercmod);
                lastAdd = "Press Inner when the last digit is " + mercmod;
            }
            // PRN
            if (curr.equals("PRN")) {
                if (isPrime(moduleID % 20)) {
                    outputList.add("[PRN %20] Press Inner");
                    lastAdd = "Press Inner";
                }
                else {
                    outputList.add("[PRN !20] Press Outer");
                    lastAdd = "Press Outer";
                }
            }
            // CHK
            if (curr.equals("CHK")) {
                if (isPrime(moduleID % 20)) {
                    outputList.add("[CHK %20] Press Outer");
                    lastAdd = "Press Outer";
                }
                else {
                    outputList.add("[CHK !20] Press Inner");
                    lastAdd = "Press Inner";
                }
            }
            // BOB
            if (curr.equals("BOB")) {
                outputList.add("[BOB] Press Inner");
                lastAdd = "Press Inner";
                if (bobSkip) {
                    // If Lit BOB + 2 batteries, then insta skip the rest.
                    String[] output = new String[outputList.size()];
                    outputList.toArray(output);
                    return output;
                }
            }
            // REP/EAT
            if (curr.equals("REP") || curr.equals("EAT")) {
                if (a == 0) {
                    outputList.add("[REP/EAT #1] Press Inner");
                    lastAdd = "Press Inner";
                }
                else {
                    outputList.add("[REP/EAT] " + lastAdd);
                    if (lastAddCLR) colorPress++;
                }
            }
            // STR/IKE
            if (curr.equals("STR") || curr.equals("IKE")) {
                colorPress++;
                String button = strikes % 3 == 0 ? "Red" : strikes % 3 == 1 ? "Green" : "Blue";
                outputList.add("[STR/IKE] Press " + button);
                lastAdd = "Press " + button;
            }
        }
        String[] output = new String[outputList.size()];
        outputList.toArray(output);
        return output;
    }
    private static String[] fixYourselfDammit(String[] msg) {
        // Fix any 'X's
        for (int a=0; a<msg.length;a+=1) {
            String current = msg[a];
            // probably one of the worst ways to do this, but fuck it
            switch (current) {
                // PCR
                case "XCR": case "PXR": msg[a] = "PCR"; break;
                // PCG
                case "XCG": case "PXG": msg[a] = "PCG"; break;
                // PCB
                case "XCB": case "PXB": msg[a] = "PCB"; break;
                // SUB
                case "XUB": case "SXB": case "SUX": msg[a] = "SUB"; break;
                // MIT
                case "XIT": case "MXT": case "MIX": msg[a] = "MIT"; break;
                // PRN
                case "XRN": case "PXN": case "PRX": msg[a] = "PRN"; break;
                // CHK
                case "XHK": case "CXK": case "CHX": msg[a] = "CHK"; break;
                // BOB
                case "XOB": case "BXB": case "BOX": msg[a] = "BOB"; break;
                // REP
                case "XEP": case "RXP": case "REX": msg[a] = "REP"; break;
                // EAT
                case "XAT": case "EXT": case "EAX": msg[a] = "EAT"; break;
                // STR
                case "XTR": case "SXR": case "STX": msg[a] = "STR"; break;
                // IKE
                case "XKE": case "IXE": case "IKX": msg[a] = "IKE"; break;
            }
        }
        return msg;
    }
    // Credit to Rosetta Code for the Playfair Cipher code
    private static String prepareText(String s, boolean changeJtoI) {
        s = s.toUpperCase().replaceAll("[^A-Z]", "");
        return changeJtoI ? s.replace("J", "I") : s.replace("Q", "");
    }
    private static void createTable(String key, boolean changeJtoI) {
        charTable = new char[5][5];
        positions = new Point[26];

        String s = prepareText(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ", changeJtoI);

        int len = s.length();
        for (int i = 0, k = 0; i < len; i++) {
            char c = s.charAt(i);
            if (positions[c - 'A'] == null) {
                charTable[k / 5][k % 5] = c;
                positions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
    }
    private static String encode(String s) {
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < sb.length(); i += 2) {

            if (i == sb.length() - 1)
                sb.append(sb.length() % 2 == 1 ? 'X' : "");

            else if (sb.charAt(i) == sb.charAt(i + 1))
                sb.insert(i + 1, 'X');
        }
        return codec(sb, 1);
    }
    private static String decode(String s) {
        return codec(new StringBuilder(s), 4);
    }
    private static String codec(StringBuilder text, int direction) {
        // edited slightly to match maca's code
        int len = text.length();
        for (int i = 0; i < len; i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int row1 = positions[a - 'A'].y;
            int row2 = positions[b - 'A'].y;
            int col1 = positions[a - 'A'].x;
            int col2 = positions[b - 'A'].x;

            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;
            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;
            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }

            text.setCharAt(i, charTable[row1][col1]);
            text.setCharAt(i + 1, charTable[row2][col2]);
        }
        return text.toString();
    }
    // Credit to the Rosetta Code again
    private static String caesarDecode(String enc, int offset) {
        return caesarEncode(enc, 26-offset);
    }
    private static String caesarEncode(String enc, int offset) {
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26 ));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26 ));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }
    // Credit to Mkyong for the prime check code
    private static boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}
