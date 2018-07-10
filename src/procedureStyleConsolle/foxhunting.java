package procedureStyleConsolle;/*Порядок игры примерно таков:

На поле (обычно 10x10} случайным неизвестным для игрока образом расставляются восемь «лис».
Игрок задаёт своё положение, введя координаты. В ответ он получает количество «лис», которое пеленгуется из его нынешнего местоположения.
Это число указывает, сколько лис расположено в одной вертикали, горизонтали и диагоналях с указанной клеткой[1].
Если местоположение игрока совпало с положением «лисы», она считается найденной.
Игра продолжается, пока не будут найдены все «лисы».*/

import java.util.Random;
import java.util.Scanner;

public class foxhunting {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    //
    public static final int SIZE_MAP = 10;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_FOX = 'f';
    public static final char DOT_SHOT = 's';
    public static final int QTY_FOX = 8;
    public static char[][] map;
    public static char[][] mapFox;
    public static int resFox;
    public static int qtyStep=0;
    //
    public static void main (String[] args) {
        initMap();
        initFox();
        printBoard();
        printMap();
        do {
            stepPlayer();
            printBoard();
            printMap();
        } while (resFox>0);
    }

    public static void initMap (){
        map = new char[SIZE_MAP][SIZE_MAP];
        for (int i=0; i<SIZE_MAP; i++) {
            for (int j=0; j<SIZE_MAP; j++) {
                map[i][j] = DOT_EMPTY;            }
        }
    }

    public static void initFox(){
        mapFox = new char[SIZE_MAP][SIZE_MAP];
        int size = SIZE_MAP-1;
        resFox = QTY_FOX;
        for (int i=1; i<=QTY_FOX; i++) {
            while (true) {
                int x = rand.nextInt(size);
                int y = rand.nextInt(size);
                if (mapFox[x][y] != DOT_FOX) {
                    mapFox[x][y] = DOT_FOX;
                    break;
                }
            }

        }
    }

    public static void printMap() {
        for (int i=0; i<=SIZE_MAP; i++){
            System.out.printf("%3s", i);
        }
        System.out.println();
        for (int i=0; i<SIZE_MAP; i++){
            System.out.printf("%3s", (i+1));
            for (int j=0; j<SIZE_MAP; j++){
                System.out.printf("%3s", map[i][j]);
            }
            System.out.println();
        }
    }

    public static void printBoard() {
        System.out.println("Количество лис-"+resFox+" Количество шагов-"+qtyStep);

    }

    public static void stepPlayer(){
        int x;
        int y;
        do {
            System.out.println("Введите свой ход в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isValidStep(x,y));
        qtyStep ++;
        calculateFox(x,y);
    }

    public static boolean isValidStep (int x, int y) {
        if (x>=0 & x<SIZE_MAP & y>=0 & y<SIZE_MAP) {
            if (map[x][y]== DOT_EMPTY){
                return (true);
            }
        }
        return (false);
    }

    public static void calculateFox(int x, int y) {
        int fox=0;
        if (mapFox[x][y] == 'f') {
            map[x][y] = DOT_SHOT;
            resFox --;
        }else {
            for (int i = 0; i<SIZE_MAP; i++) {
                if (mapFox[i][y] == 'f') {
                    fox++;
                }
            }
            for (int i = 0; i<SIZE_MAP; i++) {
                if (mapFox[x][i] == 'f') {
                    fox++;
                }
            }
            int st = x < y ? x : y;
            int dx = x-st;
            int dy = y-st;
            for (int i=dx, j=dy; i<SIZE_MAP & j<SIZE_MAP; i++, j++){
                if (mapFox[i][j] == 'f') {
                    fox++;
                }
            }

            dy = y + x;
            dx = 0;

            for (int i=dx, j=dy; i<0; i++, j--) {
                if (mapFox[i][j] == 'f') {
                    fox++;
                }
            }
            /*
            System.out.println(dx);
            System.out.println(dy);*/
            String str = ""+fox;
            map[x][y] = str.charAt(0);
        }
    }
}
