package task2;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    String fileName;
    String word = "";
    boolean regex = false;
    boolean invert = false;
    boolean ignoreRegister = false;

    public Grep(String[] commands) {
        for (String command : commands) {
            if(command == "null") continue;
            switch (command) {
                case "[-r]":
                    regex = true;
                    break;
                case "[-v]":
                    invert = true;
                    break;
                case "[-i]":
                    ignoreRegister = true;
                    break;
                default:
                    Pattern pattern = Pattern.compile("^.+\\.txt$");
                    Matcher matcher = pattern.matcher(command);
                    if (matcher.matches()) fileName = command;
                    else if (word == "") word = command;
                    else word += " " + command;
                    break;
            }
        }
    }

    private String read() {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(fileName)) {
            int i;
            while ((i = reader.read()) != -1) {
                char c = (char) i;
                sb.append(c);
            }
            return sb.toString().replace("\n", " ");
        } catch (IOException ex) {
            return "";
        }
    }

    public LinkedList<String> search() {
        LinkedList<String> result = new LinkedList<>();
        String content = read();

        String patternString = regex ? word: "(\\Q" + word + "\\E)";
        if (ignoreRegister) patternString = "(?i)" + patternString;
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        if (result.size() == 0) result.add("Not found...");
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("File Path: " + fileName + "\n");
        sb.append("Word/Pattern: " + word + "\n");
        sb.append("Regex: " + regex + "\n");
        sb.append("Invert: " + invert + "\n");
        sb.append("Ignore Register: " + ignoreRegister + "\n");
        return sb.toString();
    }
}
