package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        //choice user makes
        int choice = -1;

        //ticket bought once a time
        int seatRow = 0;
        int seatColumn =0;

        //variables for statistics
        int purchasedTickets = 0;
        int totalSeats=0;
        int income = 0;
        int totalIncome = 0;


        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int column = scanner.nextInt();

        //preset of seats
        String[][] seats = presetSeats(row,column);

        //update totalSeats
        totalSeats = row*column;

        //calculate and update total income
        totalIncome = calTotalIncome(row,column);   //increase in usability since we can use seatprice method

        while(choice!=0){

            System.out.println();
            System.out.println("1. Show the seats\n" +
                                "2. Buy a ticket\n" +
                                "3. Statistics\n" +
                                "0. Exit");

            //gets user's choice input
            choice = scanner.nextInt();

            System.out.println();


            switch (choice){
                case 1:
                    //prints current state of seats
                    print(seats);
                    break;
                case 2:
                    boolean properPurchase = false;
                    while(!properPurchase) {
                        //2. buy a ticket
                        System.out.println("Enter a row number:");
                        seatRow = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatColumn = scanner.nextInt();

                        if (wrongCheck(row,column,seatRow,seatColumn)) {
                            System.out.println("Wrong input!");
                            continue;

                        }

                        if(alreadyCheck(seats, seatRow,seatColumn)) {
                            System.out.println("That ticket has already been purchased!");
                            continue;
                        }

                        //update stat for tickets
                        purchasedTickets++;
                        //update stat for income
                        income += seatPrice(seatRow,seatColumn,row, column);
                        //prints ticket price
                        System.out.printf("Ticket price: $%d",seatPrice(seatRow,seatColumn,row, column));
                        //update seats status
                        seats[seatRow-1][seatColumn-1] = "B";
                        //update flag
                        properPurchase = true;
                        }
                    break;
                case 3:
                    printStat(purchasedTickets, income, totalIncome, totalSeats);
                case 0:
                    break;

            }//switch

        }//while




    }

    private static boolean alreadyCheck(String[][] seats, int seatRow, int seatColumn) {

        if (seats[seatRow-1][seatColumn-1].equals("B")){
            return true;
        }
        return false;

    }

    private static boolean wrongCheck(int row, int column, int seatRow, int seatColumn) {

        if (seatRow<=row && seatColumn <= column) {
            return false;
        }
        return true;

    }

    private static void printStat(int purchasedTickets, int income, int totalIncome, int totalSeats) {

        System.out.printf("Number of purchased tickets: %d\n",purchasedTickets);
        System.out.printf("Percentage: %.2f%s\n",(double)purchasedTickets/totalSeats*100,"%");
        //(above) this line just made me struggle : type cast needs to be done because int/int automatically results in int
        //also \ right after % makes % to escape... so had to make additional string
        System.out.printf("Current income: $%d\n",income);
        System.out.printf("Total income: $%d\n",totalIncome);

    }

    private static int calTotalIncome(int row, int column) {

        int totalIncome = 0;

        for (int i = 1; i<= row; i++){
            for (int j=1; j <= column; j++){
                totalIncome += seatPrice(i,j,row,column);

            }
        }

        return totalIncome;

    }

    private static void print(String[][] seats) {

        String header = "";

        for (int i=0; i< seats[0].length; i++){
            header += " "+(i+1);
        }

        header = " "+header;



        String body = "";

        for (int i=0; i < seats.length; i++){
            body += (i+1);
            for (int j=0; j< seats[i].length; j++){
                body += " " + seats[i][j];
            }
            body += "\n";
        }

        String result = header + "\n" + body;


        System.out.println("Cinema:");
        System.out.println(result);



    }

    private static String[][] presetSeats(int row, int column) {

        String[][] seats = new String[row][column];

        for (int i=0; i<row; i++) {
            for (int j=0; j<column; j++){
                seats[i][j] = "S";
            }
        }
        return seats;

    }





    private static int seatPrice(int seatRow, int seatColumn, int row, int column) {


        if (!(row*column>60)){
//            System.out.println("total seat is small, so 10 dollar");
            return 10;
        }else {
            if (seatRow <= (row/2)){
//                System.out.println("front seat, so 10 dollar");
                return 10;
            }

        }
//        System.out.println("back seat, so 8 dollar");
        return 8;

    }




}