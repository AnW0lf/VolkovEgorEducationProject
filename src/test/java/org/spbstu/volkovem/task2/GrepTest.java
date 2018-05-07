package org.spbstu.volkovem.task2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GrepTest {
    private Grep grep;

    private void createFileIfNotExists(String path) throws IOException {
        File input = new File(path);
        if(input.createNewFile()) {
            FileWriter writer = new FileWriter(path);
            writer.write("New file \"input.txt\"");
            writer.close();
        }
    }

    @Test
    void readExists() throws IOException {
        String path = "input.txt";
        createFileIfNotExists(path);
        grep = new Grep("",  false, false, false);
        assertFalse(grep.read(new File(path)) == null);
    }

    @Test
    void readNotExists() {
        String emptyPath = "";
        grep = new Grep("", false, false, false);
        assertThrows(FileNotFoundException.class, () -> grep.read(new File(emptyPath)));
    }

    @Test
    void searchExists() throws IOException {
        String path = "input.txt";
        String word = "New";
        createFileIfNotExists(path);
        grep = new Grep(word, false, false, false);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("New file \"input.txt\"");
        assertEquals(expected, grep.search(grep.read(new File(path))));
    }

    @Test
    void searchNotExists() throws IOException {
        String path = "input.txt";
        String word = "QwErTy";
        createFileIfNotExists(path);
        grep = new Grep(word, false, false, false);
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(expected, grep.search(grep.read(new File(path))));
    }

    @Test
    void searchExistsIgnoreRegister() throws IOException {
        String path = "input.txt";
        String word = "new";
        createFileIfNotExists(path);
        grep = new Grep(word, false, true, false);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("New file \"input.txt\"");
        assertEquals(expected, grep.search(grep.read(new File(path))));
    }

    @Test
    void searchNotExistsIgnoreRegister() throws IOException {
        String path = "input.txt";
        String word = "qweryutu";
        createFileIfNotExists(path);
        grep = new Grep(word, false, true, false);
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(expected, grep.search(grep.read(new File(path))));
    }

    @Test
    void searchExistsRegex() throws IOException {
        String path = "input.txt";
        String word = "\"[\\w]+\\.[\\w]+\"";
        createFileIfNotExists(path);
        grep = new Grep(word, true, false, false);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("New file \"input.txt\"");
        assertEquals(expected, grep.search(grep.read(new File(path))));
    }

    @Test
    void searchNotExistsRegex() throws IOException {
        String path = "input.txt";
        String word = "\"[\\d]+\\.[\\d]+\"";
        createFileIfNotExists(path);
        grep = new Grep(word, true, false, false);
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(expected, grep.search(grep.read(new File(path))));
    }

    @Test
    void searchExistsInvert() throws IOException {
        String path = "input.txt";
        String word = "New";
        createFileIfNotExists(path);
        grep = new Grep(word, false, false, true);
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(expected, grep.search(grep.read(new File(path))));
    }

    @Test
    void searchNotExistsInvert() throws IOException {
        String path = "input.txt";
        String word = "QwErTy";
        createFileIfNotExists(path);
        grep = new Grep(word, false, false, true);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("New file \"input.txt\"");
        assertEquals(expected, grep.search(grep.read(new File(path))));
    }

    @Test
    void run() throws IOException {
        String path = "input.txt";
        String word = "New";
        createFileIfNotExists(path);
        grep = new Grep(word, false, false, false);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("New file \"input.txt\"");
        assertEquals(expected, grep.run(path));
    }
}
