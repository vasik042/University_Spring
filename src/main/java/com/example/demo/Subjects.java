package com.example.demo;

public enum Subjects {
    UKRAINIAN ("Українська мова"),
    MATH ("Математика"),
    HISTORY ("Історія"),
    BIOLOGY ("Біологія"),
    GEOGRAPHY ("Географія"),
    PHYSICS ("Фізика"),
    CHEMISTRY ("Хімія"),
    ENGLISH ("Англійська мова"),
    SPANISH ("Іспанська мова"),
    GERMAN ("Німецька мова"),
    FRENCH ("Французька мова");

    private String ukrName;

    Subjects(String ukrName) {
        this.ukrName = ukrName;
    }

    public String getUkrName() {
        return ukrName;
    }
}
