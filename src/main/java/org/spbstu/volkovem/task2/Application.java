package org.spbstu.volkovem.task2;

import org.apache.commons.cli.*;

import java.io.FileNotFoundException;

public class Application {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        Option invert = new Option("v", "invert", false
                , "Инвертирует условие фильтрации(Возвращает только то, что не соответствует)");

        Option ignoreRegister = new Option("i", "ignoreRegister"
                , false, "Игнорирует регистр слов");

        Option regex = new Option("r", "regex"
                , false, "входное слово является Regex'ом");

        Options options = new Options();
        options.addOption(invert);
        options.addOption(ignoreRegister);
        options.addOption(regex);

        CommandLineParser parser = new DefaultParser();
        CommandLine lines = parser.parse(options, args);

        String word = args[args.length - 2];
        String fileName = args[args.length - 1];

        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        if (lines.hasOption("v")) System.out.println("Инвертирование условия.");
        if (lines.hasOption("i")) System.out.println("Игнорирование регистров слов.");
        if (lines.hasOption("r")) System.out.println("Вместо слова задано регулярное выражение: " + word);
        else System.out.println("Задано слово для поиска: " + word);
        System.out.println("Файл для чтения: " + fileName);
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

        Grep grep = new Grep(fileName, word, lines.hasOption("r"), lines.hasOption("i"), lines.hasOption("v"));
        grep.run();
        
        for (String line: grep.getResult()) {
            System.out.println(line);
        }
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

        System.exit(0);
    }
}
