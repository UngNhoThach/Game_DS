/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplechoiceserver;

import java.util.Scanner;


public class ServerFunction implements Runnable {

    @Override
    public void run() {
        String exit = "";
        Scanner bai = new Scanner(System.in);
        do {
            System.out.println("input function: ");
            String c = bai.nextLine();
            String[] mang;
            mang = c.split(":");
            int num = c.replaceAll("[^:]", "").length();
            if (mang[0].equals("UserStatistic") && num == 0) {
                UserStatistic();
            } else if (mang[0].equals("help") && num == 0) {
                help();
            } else if (mang[0].equals("exit") && num == 0) {
                exit = "exit";
            } else {
                System.out.println("wrong syntax: ");
            }
        } while (!exit.equals("exit"));

    }

    public void UserStatistic() {
        int onlineuser = 0;
        for (Worker worker : Server.workers) {
            if (!worker.myName.equals("anonymos")) {
                onlineuser++;
            }
        }
        System.out.println("số lượng user đang online là: " + onlineuser);
    }

    public void help() {
        System.out.println("--Để thống kê số lượng user ta dùng UserStatistic");
        System.out.println("--Để thay đổi thời gian/trận và sô/trận dùng ...");
    }

    public String checksqlerror(String ex) {
        System.out.println(ex);
        if (ex.contains("Violation of PRIMARY KEY constraint")) {
            return "key duplicate";
        }
        if (ex.contains("Invalid object name")) {
            return "unexist table";
        }
        if (ex.contains("The result set has no current row")) {
            return "table has no data";
        }
        return "something wrong";
    }

}
