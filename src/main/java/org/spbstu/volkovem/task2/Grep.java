package org.spbstu.volkovem.task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private boolean ignoreRegister, invert, isRegex;
    private String regex;

    Grep(String regex,
         boolean isRegex,
         boolean ignoreRegister,
         boolean invert) {

        this.isRegex = isRegex;
        this.ignoreRegister = ignoreRegister;
        this.invert = invert;
        this.regex = regex;
    }

    ArrayList<String> run(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = read(new File(filePath));
        return search(lines);
    }

    ArrayList<String> read(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> result = new ArrayList<>();
        reader.lines().forEachOrdered(result::add);
        return result;
    }

    ArrayList<String> search(ArrayList<String> lines) {
        ArrayList<String> result = new ArrayList<>();

        String patternString = isRegex ? regex : String.format("(\\Q%s\\E)", regex);
        if (ignoreRegister) patternString = String.format("(?i)%s", patternString);
        Pattern pattern = Pattern.compile(patternString);
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (invert && !matcher.find() || !invert && matcher.find()) {
                result.add(line);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("Invert statement : %s\nIgnore words register : %s\n" +
                        "Word is regex : %s\nWord/Regex : %s",
                invert, ignoreRegister, isRegex, regex);
    }
}
