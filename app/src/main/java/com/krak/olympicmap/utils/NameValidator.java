package com.krak.olympicmap.utils;

public class NameValidator {
    public static boolean isValidUserName(String name){
        // Имя содержит только буквы/цифры/подчёркивание, и его длина >= 3
        return name.matches("\\w{3,}");
    }
}
