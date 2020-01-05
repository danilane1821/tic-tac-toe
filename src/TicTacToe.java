import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions =  new ArrayList<>();
    static ArrayList<Integer> cpuPositions =  new ArrayList<>();

    public static void main (String[] args) {

       char [][] gameBoard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};


       printGameBoard(gameBoard);


        //continues to ask for a position
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");
            int playerPos = scan.nextInt();

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)){
                System.out.println("position taken.. pick another");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard,playerPos, "player");

//            this makes sure we check winner and result after each play
            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                 cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard,cpuPos, "cpu");

            printGameBoard(gameBoard);

            // this makes sure we check winner and result after each play
            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

        }



    }

//    takes in two dimensional array and prints it

    public static void printGameBoard (char [][] gameBoard) {
        for(char[] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

//    takes in the position you chose and adds it to game board

    public static void placePiece (char [][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winCon = new ArrayList<List>();
        winCon.add(topRow);
        winCon.add(middleRow);
        winCon.add(bottomRow);
        winCon.add(leftCol);
        winCon.add(midCol);
        winCon.add(rightCol);
        winCon.add(cross1);
        winCon.add(cross2);

        for(List l : winCon){
            if(playerPositions.containsAll(l)){
                return " Congratulations, You Won!!!";
            }else if(cpuPositions.containsAll(l)){
                return  "Sorry.. CPU Won.";
            }else if(playerPositions.size() + cpuPositions.size() == 9){
                return "TIE!!!";
            }
        }

        return "";
    }
}
