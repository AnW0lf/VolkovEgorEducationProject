package org.spbstu.volkovem.task2;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private String fileName;
    private String word = "";
    private boolean regex = false;
    private boolean invert = false;
    private boolean ignoreRegister = false;

    Grep(String[] commands) {
        for (String command : commands) {
            if(Objects.equals(command, "null")) continue;
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
                    else if (Objects.equals(word, "")) word = command;
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

    LinkedList<String> search() {
        LinkedList<String> result = new LinkedList<>();
        String content = read();

        String patternString = regex ? word: String.format("(\\Q%s\\E)", word);
        if (ignoreRegister) patternString = String.format("(?i)%s", patternString);
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
        return "File Path: " + fileName + "\n" +
                "Word/Pattern: " + word + "\n" +
                "Regex: " + regex + "\n" +
                "Invert: " + invert + "\n" +
                "Ignore Register: " + ignoreRegister + "\n";
    }
}
