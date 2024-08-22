package com.test.webflux.algoritmos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ComparatorPlayers {

    public static void main(String[] args) {

    }

    class Player implements{
        String name;
        int score;

        Player(String name, int score){
            this.name = name;
            this.score = score;
        }
    }

    class Checker implements Comparator {

        @Override
        public int compare(Player o1, Player o2) {
            return o1.name
        }
    }

    class Solution {

        public void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();

            Player[] player = new Player[n];
            Checker checker = new Checker();

            for(int i = 0; i < n; i++){
                player[i] = new Player(scan.next(), scan.nextInt());
            }
            scan.close();

            Arrays.sort(player, checker);
            for(int i = 0; i < player.length; i++){
                System.out.printf("%s %s\n", player[i].name, player[i].score);
            }
        }
    }
}
