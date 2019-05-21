package util;

public enum Color {

    EXITO("\u001B[32m"),
    ERROR("\u001B[31m"),
    DEFAULT("\u001B[0m"),
    GREEN("\033[32;1m"),
    BLUE("\033[36m"),
    ANSI_RED("\u001B[31m"),
    ANSI_BLUE_BACKGROUND("\033[0;104m"),
    BLUE_BOLD("\033[1;34m"),
    GREEN_BOLD_BRIGHT("\033[1;92m"),
    BLUE_BOLD_BRIGHT("\033[1;94m"),
    GREE_ANDER("\"\\033[4;32m\""),
    
    DARK_BLUE("\u001B[34m");

    String color;

    private Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }

}
