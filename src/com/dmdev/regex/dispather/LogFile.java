package com.dmdev.regex.dispather;

public class LogFile {
    private String number;
    private String dateTime;
    private String numberPhone;

    public String writeToLogFile() {
        return number + ", " + dateTime + ", " + numberPhone;
    }

    public String getNumber() {
        return number;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "LogFile{" +
                "number='" + number + '\'' +
                ", date='" + dateTime + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                '}';
    }
}
