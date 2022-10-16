package demo.homework;

public class Parser {
    public String parse(String firstLine) {
        String[] parts = firstLine.split(" ");
        System.out.println(firstLine);
        return parts[1];
    }
}
