import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TetrisGame {
    public static void main(String[] args) {
        // 初始化游戏
        int[][] board = new int[21][11];  // 游戏面板
        int[] currentBlock = null;        // 当前方块
        int score = 0;                    // 玩家得分
        boolean gameOver = false;         // 游戏结束标志
        int moved;

        // 随机生成第一个方块
        currentBlock = generateBlock();
        // 游戏循环
        while (!gameOver) {
            // 输出游戏面板和当前方块
            printBoard(board, currentBlock);
            // 玩家移动方块
            moved=moveBlock(board, currentBlock);
            // 判断是否有完整的行
            if(moved==1) {
            	checkAndClear(board);
            	currentBlock = generateBlock();   // 生成新方块，进入下一个循环
            }else {}              				  //未发生接触，直接进入下一个循环        
        }
        System.out.println("游戏结束！得分：" + score);
    }

    /**
     * 随机生成一个方块
     */ 
    private static int[] generateBlock() {
        int[] block = new int[8];
        Random random = new Random();
        int type = random.nextInt(7);  // 随机生成方块的类型

        // 根据方块类型生成方块
        switch (type) {
            case 0:  // I型方块
                block[0] = 0; block[1] = 3;
                block[2] = 0; block[3] = 4;
                block[4] = 0; block[5] = 5;
                block[6] = 0; block[7] = 6;
                break;
            case 1:  // J型方块
                block[0] = 0; block[1] = 4;
                block[2] = 0; block[3] = 5;
                block[4] = 0; block[5] = 6;
                block[6] = 1; block[7] = 6;
                break;
            case 2:  // L型方块
            	block[0] = 0; block[1] = 4;
                block[2] = 0; block[3] = 5;
                block[4] = 0; block[5] = 6;
                block[6] = 1; block[7] = 4;
                break;
            case 3:  // O型方块
                block[0] = 0; block[1] = 5;
                block[2] = 0; block[3] = 6;
                block[4] = 1; block[5] = 5;
                block[6] = 1; block[7] = 6;
                break;
            case 4:  // S型方块
            	block[0] = 0; block[1] = 5;
                block[2] = 0; block[3] = 6;
                block[4] = 1; block[5] = 4;
                block[6] = 1; block[7] = 5;
                break;
            case 5:  // T型方块
                block[0] = 0; block[1] = 4;
                block[2] = 0; block[3] = 5;
                block[4] = 0; block[5] = 6;
                block[6] = 1; block[7] = 5;
                break;
            case 6:  // Z型方块
            	block[0] = 0; block[1] = 5;
                block[2] = 0; block[3] = 6;
                block[4] = 1; block[5] = 6;
                block[6] = 1; block[7] = 7;
                break;
        }

        return block;
    }

    /**
      输出游戏面板和当前
     */
    private static void printBoard(int[][] board, int[] currentBlock) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                // 判断当前位置是否为当前方块的一部分
                if (Arrays.equals(new int[]{currentBlock[0], currentBlock[1]}, new int[]{i, j})
                        || Arrays.equals(new int[]{currentBlock[2], currentBlock[3]}, new int[]{i, j})
                        || Arrays.equals(new int[]{currentBlock[4], currentBlock[5]}, new int[]{i, j})
                        || Arrays.equals(new int[]{currentBlock[6], currentBlock[7]}, new int[]{i, j})) {
                    System.out.print("* ");
                } else {
                    System.out.print(board[i][j] == 0 ? "- " : "# ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 玩家移动方块
     */
    private static int moveBlock(int[][] board, int[] currentBlock) {
        Scanner scanner = new Scanner(System.in);
        int moved = 0;

        // 不断循环直到方块移动
        while (moved==0) {
            System.out.print("请输入移动方向（上下左右）：");
            String direction = scanner.next();

            // 根据输入的方向移动方块
            switch (direction) {
                case "w":
                    currentBlock[0] -= 1;
                    currentBlock[2] -= 1;
                    currentBlock[4] -= 1;
                    currentBlock[6] -= 1;
                    //System.out.println("yes");
                    break;
                case "s":
                    currentBlock[0] += 1;
                    currentBlock[2] += 1;
                    currentBlock[4] += 1;
                    currentBlock[6] += 1;
                    //System.out.println("yes");
                    break;
                case "a":
                    currentBlock[1] -= 1;
                    currentBlock[3] -= 1;
                    currentBlock[5] -= 1;
                    currentBlock[7] -= 1;
                    //System.out.println("yes");
                    break;
                case "d":
                    currentBlock[1] += 1;
                    currentBlock[3] += 1;
                    currentBlock[5] += 1;
                    currentBlock[7] += 1;
                    //System.out.println("yes");
                    break;
                default:
                    System.out.println("输入有误，请重新输入！");
                    continue;
            }

            // 判断方块是否超出边界或与其他方块重叠
            if (currentBlock[0] < 0 || currentBlock[2] < 0||currentBlock[4] < 0 || currentBlock[6] < 0
                    || currentBlock[1] < 0 || currentBlock[3] < 0 || currentBlock[5] < 0 || currentBlock[7] < 0
                    || currentBlock[1] >= 10 || currentBlock[3] >= 10 || currentBlock[5] >= 10 || currentBlock[7] >= 10) {
                System.out.println("移动无效，请重新输入！");
                // 恢复方块位置
                switch (direction) {
                    case "w":
                        currentBlock[0] += 1;
                        currentBlock[2] += 1;
                        currentBlock[4] += 1;
                        currentBlock[6] += 1;
                       // System.out.println("no");
                        break;
                    case "s":
                        currentBlock[0] -= 1;
                        currentBlock[2] -= 1;
                        currentBlock[4] -= 1;
                        currentBlock[6] -= 1;
                       // System.out.println("no");
                        break;
                    case "a":
                        currentBlock[1] += 1;
                        currentBlock[3] += 1;
                        currentBlock[5] += 1;
                        currentBlock[7] += 1;
                       // System.out.println("no");
                        break;
                    case "d":
                        currentBlock[1] -= 1;
                        currentBlock[3] -= 1;
                        currentBlock[5] -= 1;
                        currentBlock[7] -= 1;
                        //System.out.println("no");
                        break;
                }
               // moved=1;	
            } else if(board[currentBlock[0]][currentBlock[1]] != 0             //重叠
                    || board[currentBlock[2]][currentBlock[3]] != 0
                    || board[currentBlock[4]][currentBlock[5]] != 0
                    || board[currentBlock[6]][currentBlock[7]] != 0) {
            	System.out.println("重叠");
                // 恢复方块位置
                switch (direction) {
                    case "w":
                        currentBlock[0] += 1;
                        currentBlock[2] += 1;
                        currentBlock[4] += 1;
                        currentBlock[6] += 1;
                       // System.out.println("no");
                        break;
                    case "s":
                        currentBlock[0] -= 1;
                        currentBlock[2] -= 1;
                        currentBlock[4] -= 1;
                        currentBlock[6] -= 1;
                       // System.out.println("no");
                        break;
                    case "a":
                        currentBlock[1] += 1;
                        currentBlock[3] += 1;
                        currentBlock[5] += 1;
                        currentBlock[7] += 1;
                       // System.out.println("no");
                        break;
                    case "d":
                        currentBlock[1] -= 1;
                        currentBlock[3] -= 1;
                        currentBlock[5] -= 1;
                        currentBlock[7] -= 1;
                        //System.out.println("no");
                        break;
                }
                board[currentBlock[0]][currentBlock[1]]=1;
                board[currentBlock[2]][currentBlock[3]]=1;
                board[currentBlock[4]][currentBlock[5]]=1;
                board[currentBlock[6]][currentBlock[7]]=1;
            	moved=1;	
            }
            else if(currentBlock[0] >= 20 || currentBlock[2] >= 20 || currentBlock[4] >= 20 || currentBlock[6] >= 20) { //触底
            	System.out.println("触底");
                // 恢复方块位置
                switch (direction) {
                    case "w":
                        currentBlock[0] += 1;
                        currentBlock[2] += 1;
                        currentBlock[4] += 1;
                        currentBlock[6] += 1;
                        break;
                    case "s":
                        currentBlock[0] -= 1;
                        currentBlock[2] -= 1;
                        currentBlock[4] -= 1;
                        currentBlock[6] -= 1;
                        break;
                    case "a":
                        currentBlock[1] += 1;
                        currentBlock[3] += 1;
                        currentBlock[5] += 1;
                        currentBlock[7] += 1;
                        break;
                    case "d":
                        currentBlock[1] -= 1;
                        currentBlock[3] -= 1;
                        currentBlock[5] -= 1;
                        currentBlock[7] -= 1;
                        break;
                }	
                board[currentBlock[0]][currentBlock[1]]=1;
                board[currentBlock[2]][currentBlock[3]]=1;
                board[currentBlock[4]][currentBlock[5]]=1;
                board[currentBlock[6]][currentBlock[7]]=1;
            	moved=1;	
            }
            else {
                moved = 2;
            }
        }
        return moved;
    }

    /**
     * 检查是否有完整的行并进行消除
     */
    private static boolean checkAndClear(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // 从下往上扫描每一行
        for (int i = rows - 1; i >= 0; i--) {
            boolean rowIsFull = true;
            // 判断该行是否全部有方块
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 0) {
                    rowIsFull = false;
                    break;
                }
            }
            // 如果该行全部有方块，则进行消除
            if (rowIsFull) {
                // 将该行上面的所有方块下移一行
                for (int k = i - 1; k >= 0; k--) {
                    for (int j = 0; j < cols; j++) {
                        board[k + 1][j] = board[k][j];
                    }
                }
                // 将该行清空
                for (int j = 0; j < cols; j++) {
                    board[0][j] = 0;
                }
                i++; // 消除一行后，下一行变成当前行
                
                return true;
            }
        }
		return false;
    }
 
    
}
