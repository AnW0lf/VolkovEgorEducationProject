package org.spbstu.volkovem.task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private boolean ignoreRegister, invert, isRegex;
    private String fileName, regex;
    private List<String> list;

    public Grep(String fileName,
                String regex,
                boolean isRegex,
                boolean ignoreRegister,
                boolean invert) {

        this.isRegex = isRegex;
        this.ignoreRegister = ignoreRegister;
        this.invert = invert;
        this.regex = regex;
        this.fileName = fileName;
    }

    public void run() throws FileNotFoundException {
        File input = new File(fileName);
        ArrayList<String> lines = read(input);
        list = search(lines);
    }

    public ArrayList<String> getResult() {
        return new ArrayList<>(list);
    }

    private ArrayList<String> read(File file) throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.lines().filter(s -> !s.isEmpty()).forEachOrdered(result::add);
        return result;
    }

    private ArrayList<String> search(ArrayList<String> lines) {
        ArrayList<String> result = new ArrayList<>();

        String patternString = isRegex ? regex : String.format("(\\Q%s\\E)", regex);
        if (ignoreRegister) patternString = String.format("(?i)%s", patternString);
        Pattern pattern = Pattern.compile(patternString);
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if ((matcher.find() && !invert) || (!matcher.find() && invert)) {
                result.add(line);
            }
        }
        if (result.isEmpty()) result.add("Not found...");
        return result;
    }

    @Override
    public String toString() {
        return String.format("Invert statement : %s\nIgnore words register : %s\n" +
                "Word is regex : %s\nWord/Regex : %s\nInput file name : %s",
                invert, ignoreRegister, isRegex, regex, fileName);
    }
}