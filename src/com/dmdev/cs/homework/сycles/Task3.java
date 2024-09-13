package com.dmdev.cs.homework.сycles;

/**
 * Программист Ваня сразу после окончания курсов dmdev устроился в IT компанию на позицию Junior Java Developer с зарплатой 600$ в месяц.
 * Ему обещали, что будут поднимать зарплату на 400$ каждые 6 месяцев.
 * 300$ в месяц Ваня тратит на еду и развлечения.
 * 10% от зарплаты Ваня ежемесячно переводит на счет брокера, чтобы инвестировать в акции с доходностью 2% в месяц.
 * <p>
 * Посчитать, сколько Ваня будет иметь средств на своем счету и на счету брокера за 3 года и 2 месяца.
 */
public class Task3 {
    public static void main(String[] args) {
        getFundsInYourAccount(7, 5);
        getFundsInYourAccount(38, 10);
    }

    private static void getFundsInYourAccount(int endMonth, double userPercent) { //зарплата с учетом повышения
        double salary = 600;
        double life = 300;
        double savedMoneyForMonth;
        double savedMoney = 0;
        double reminderBrokerAccount = 0;
        double income = 0;

        if (endMonth <= 0) {
            System.out.println("Месяц должен быть положительным числом");
        } else if (userPercent < 0) {
            System.out.println("Процент должен быть не меньше 0");
        } else {
            for (int month = 1; month <= endMonth; month++) {
                if (month % 7 == 0) {                           // повышенную зарплату получаем на седьмой месяц
                    salary = salary + 400;
                }
                savedMoneyForMonth = salary - life - getPercentFromSalary(salary, userPercent);
                savedMoney = savedMoney + savedMoneyForMonth;
                reminderBrokerAccount += getPercentFromSalary(salary, userPercent);
                income += getPercentBrokerAccount(reminderBrokerAccount);
            }
            System.out.println("Сбережения на личном счету: " + savedMoney);
            System.out.println("Сбережения на брокерском счету: " + income);
        }
    }

    private static double getPercentFromSalary(double salary, double percent) { // сумма которую кладу и процент который я кладу
        return salary * percent * 0.01;
    }

    private static double getPercentBrokerAccount(double sum) {
        return sum * 0.02;
    }
}