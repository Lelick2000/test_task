package project.constants;

public enum Urls {
    BASE_URL("https://yandex.ru/");


    private final String text;

    Urls(final String text) {
        this.text = text;
    }

    public String getValue() {
        return text;
    }
}