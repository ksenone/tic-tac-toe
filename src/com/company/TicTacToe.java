package com.company;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] field = createAnEmptyGrid();
        printTheField(field);
        boolean flag = true;
        char moveValue;
        while(analyzeTheField(field)) {
            moveValue = changeMoveValue(flag);
            flag = !flag;

            while (!doTheMove(field, moveValue));
            printTheField(field);
        }
    }

    static boolean doTheMove(char[][] field, char moveValue) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        int input1;
        int input2;
        try {
            input1 = scanner.nextInt();
            input2 = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if (input1 < 1 || input1 > 3 || input2 < 1 || input2 > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if (field[input1 - 1][input2 - 1] == 'X' || field[input1 - 1][input2 - 1] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        field[input1 - 1][input2 - 1] = moveValue;
        return true;
    }

    static char[][] createAnEmptyGrid() {
        char[][] grid = {{' ', ' ', ' '},
                         {' ', ' ', ' '},
                         {' ', ' ', ' '}};
        return grid;
    }

    static char changeMoveValue(boolean flag) {
        if (flag) {
            return 'X';
        } else {
            return 'O';
        }
    }

    static void printTheField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static boolean analyzeTheField(char[][] field) {
        int sumDiagonal1 = 0;
        int sumDiagonal2 = 0;
        int counterX = 0;
        int counterO = 0;
        int counterTriplets = 0;
        for (int i = 0; i < 3; i++) {
            int sumRow = 0;
            int sumColumn = 0;
            sumDiagonal1 += field[i][i];
            sumDiagonal2 += field[i][2 - i];

            for (int j = 0; j < 3; j++) {
                sumRow += field[i][j];
                sumColumn += field[j][i];

                if (field[i][j] == 'X') {
                    counterX++;
                } else if (field[i][j] == 'O') {
                    counterO++;
                }
            }

            if (sumRow == 264 || sumColumn == 264) {
                counterTriplets += 264;
            } else if (sumRow == 237 || sumColumn == 237) {
                counterTriplets += 237;
            }
        }

        if (sumDiagonal1 == 264 || sumDiagonal2 == 264) {
            counterTriplets += 264;
        } else if (sumDiagonal1 == 237 || sumDiagonal2 == 237) {
            counterTriplets += 237;
        }

        return showStateOfTheGame(counterTriplets, counterO, counterX);
    }

    static boolean showStateOfTheGame(int counterTriplets, int counterO, int counterX) {
        if (counterTriplets == 264) {
            System.out.println("X wins");
            return false;
        } else if (counterTriplets == 237) {
            System.out.println("O wins");
            return false;
        }

        if (Math.abs(counterO - counterX) > 1 || counterTriplets != 0) {
            //System.out.println("Impossible");
            return true;
        }

        if (counterO + counterX == 9) {
            System.out.println("Draw");
            return false;
        } else {
            //System.out.println("Game not finished");
            return true;
        }
    }
}
