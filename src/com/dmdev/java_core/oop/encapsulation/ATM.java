package com.dmdev.java_core.oop.encapsulation;

public class ATM {
    private final static Integer TEN = 10;
    private final static Integer TWENTY = 20;
    private final static Integer FIFTY = 50;
    private Integer numberOf10;
    private Integer numberOf20;
    private Integer numberOf50;

    ATM(Integer numberOf10, Integer numberOf20, Integer numberOf50) {
        this.numberOf10 = numberOf10;
        this.numberOf20 = numberOf20;
        this.numberOf50 = numberOf50;
    }

    public int addDenomination10(int numberOfBills) {
        this.numberOf10 = this.numberOf10 + numberOfBills;
        return this.numberOf10;
    }

    public int addDenomination20(int numberOfBills) {
        this.numberOf20 = this.numberOf20 + numberOfBills;
        return this.numberOf20;
    }

    public int addDenomination50(int numberOfBills) {
        this.numberOf50 = this.numberOf50 + numberOfBills;
        return this.numberOf50;
    }

    public boolean isOperationWithdrawMoneySuccess(int sum) {
        int sumMoneyInATM = (this.numberOf10 * TEN) + (this.numberOf20 * TWENTY) + (this.numberOf50 * FIFTY);

        if (sum > sumMoneyInATM) {
            System.out.println("Недостаточно средств в банкомате");
            return false;
        } else if (sum < 10) {
            System.out.println("Некорректно введеное значение");
            return false;
        } else {
            int currentNumberOf50 = this.numberOf50;
            int currentNumberOf20 = this.numberOf20;
            int counter50 = 0;
            int counter20 = 0;

            int remainderOf50 = Math.floorDiv(sum, FIFTY);
            while (currentNumberOf50 > 0 && remainderOf50 > counter50) {
                currentNumberOf50--;
                counter50++;
            }
            sum = sum - counter50 * FIFTY;

            int remainderOf20 = Math.floorDiv(sum, TWENTY);
            while (currentNumberOf20 > 0 && remainderOf20 > counter20) {
                currentNumberOf20--;
                counter20++;
            }
            sum = sum - counter20 * TWENTY;

            int remainderOf10 = sum / TEN;
            if (this.numberOf10 - remainderOf10 < 0) {
                System.out.println("Отсутствуют необходимые купюры");
                return false;
            } else {
                this.numberOf10 = this.numberOf10 - remainderOf10;

                System.out.println("Количество выданных купюр номиналом 50: " + counter50);
                System.out.println("Количество выданных купюр номиналом 20: " + counter20);
                System.out.println("Количество выданных купюр номиналом 10: " + remainderOf10);
                setNumberOf50(currentNumberOf50);
                setNumberOf20(currentNumberOf20);
            }
        }
        return true;
    }

    public Integer getNumberOf10() {
        return numberOf10;
    }

    public void setNumberOf10(int numberOf10) {
        this.numberOf10 = numberOf10;
    }

    public Integer getNumberOf20() {
        return this.numberOf20;
    }

    public void setNumberOf20(int numberOf20) {
        this.numberOf20 = numberOf20;
    }

    public Integer getNumberOf50() {
        return this.numberOf50;
    }

    public void setNumberOf50(int numberOf50) {
        this.numberOf50 = numberOf50;
    }
}

