package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Bot tactics for game
 */

public class Bot {

    /**
     * tactics for 1 and 2 game part
     */
    private String tacticsPart1, tacticsPart2;

    /**
     * coordinates of the game field
     */
    private int x, y, sizeTable, value, pitchStroke;
    private int[][] table;
    int move;
    String oneDirection;
    int q, w, c, v;

    public Bot(String tacticsPart1, String tacticsPart2, int x, int y) {
        int c = 0;
        int v = 0;
        this.tacticsPart1 = tacticsPart1;
        this.tacticsPart2 = tacticsPart2;
        this.x = x;
        this.y = y;
        sizeTable = 10;
        this.table = new int[sizeTable][sizeTable];
        table[0][0] = 1;
        table[9][9] = -1;
        move = 0;
        value = 1;
        pitchStroke = 1;
        oneDirection = "Up";
    }


    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setTacticsPart1(String tactics, int z) {
        tacticsPart1 = tactics;
        if (tacticsPart1.equals("Diagonal")) {
            value = 1;
            pitchStroke = 1;
            oneDirection = "Up";
        }
        if (tactics.equals("Snake")) {
            value = 1;
            pitchStroke = 1;
            oneDirection = "Down";
        }
        if (tactics.equals("Around")) {
            value = sizeTable - 1;
            pitchStroke = sizeTable - 1;
            oneDirection = "Right";
        }
        if (tacticsPart1.equals("Row by Row")) {
            value = sizeTable - 1;
            pitchStroke = sizeTable - 1;
            oneDirection = "Right";
        }
    }


    public void setTacticsPart2(String tactics) {
        tacticsPart2 = tactics;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int c) {
        move = c;
    }

    public String getTacticsPart1() {
        return tacticsPart1;
    }

    /**
     * @param a
     * @param s
     * @param z
     * @param playerMove
     * @return true if the tactic has worked, and false another
     */
    public int gameBotPart1(int a, int s, int z, int playerMove) {
        int check = 0;
        if (table[a][s] != -z) {// проверить на полное заполнение
            table[a][s] = -z;
        }
        if (tacticsPart1.equals("Diagonal")) {
            check = diagonal(a, s, z, playerMove);
        }
        if (tacticsPart1.equals("Snake")) {
            check = snake(a, s, z, playerMove);

        }
        if (tacticsPart1.equals("Around")) {
            check = around(a, s, z, playerMove);

        }
        if (tacticsPart1.equals("Row by Row")) {
            check = rowByRow(a, s, z, playerMove);

        }
        if (tacticsPart1.equals("Mirroring")) {
            x = sizeTable - a - 1;
            y = sizeTable - s - 1;
        }
        if (tacticsPart1.equals("Hot filling")) {
            check = hotFilling(a, s, z, playerMove);
        }
        table[x][y] = z;
        return check;
    }

    private int hotFilling(int a, int s, int z, int playerMove) {
        int q = x;
        int w = y;
//        if(z == -1) {
//            if (oneDirection.equals("UpLeftAbove")) {
//                if (table[x + z][y] != 0 && y != 0) {
//                    w = y + z;
//                } else if (y != 0) {
//                    q = x + z;
//                } else {
//                    q = x - z;
//                    oneDirection = "DownLeftAbove";
//                }
//            } else if (oneDirection.equals("DownLeftAbove")) {
//                if (table[x][y - z] != 0 && x != sizeTable - 1) {
//                    q = x - z;
//                } else if (x != sizeTable - 1) {
//                    w = y - z;
//                } else {
//                    w = y + z;
//                    oneDirection = "UpLeftAbove";
//                }
//            } else if (oneDirection.equals("UpLeftBelow")) {
//                if (table[x][y + z] != 0 && x != 0) {
//
//                    q = x + z;
//                } else if (x != 0) {
//                    w = y + z;
//                } else {
//                    w = y - z;
//                    oneDirection = "DownRightBelow";
//                }
//            } else if (oneDirection.equals("DownRightBelow")) {
//                if (table[x - z][y] != 0 && y != sizeTable - 1) {
//                    w = y - z;
//                } else if (y != sizeTable - 1) {
//                    q = x - z;
//                } else {
//                    q = x + z;
//                    oneDirection = "UpLeftBelow";
//                }
//            }

//            if (oneDirection.equals("UpRightAbove")) {
//                if (table[x - z][y] != 0 && y != sizeTable - 1) {
//                    w = y + z;
//                } else if (y != 0) {
//                    q = x + z;
//                } else {
//                    q = x - z;
//                    oneDirection = "DownLeftAbove";
//                }
//            } else if (oneDirection.equals("DownLeftAbove")) {
//                if (table[x][y - z] != 0 && x != sizeTable - 1) {
//                    q = x - z;
//                } else if (x != sizeTable - 1) {
//                    w = y - z;
//                } else {
//                    w = y + z;
//                    oneDirection = "UpLeftAbove";
//                }
//            } else if (oneDirection.equals("UpLeftBelow")) {
//                if (table[x][y + z] != 0 && x != 0) {
//
//                    q = x + z;
//                } else if (x != 0) {
//                    w = y + z;
//                } else {
//                    w = y - z;
//                    oneDirection = "DownRightBelow";
//                }
//            } else if (oneDirection.equals("DownRightBelow")) {
//                if (table[x - z][y] != 0 && y != sizeTable - 1) {
//                    w = y - z;
//                } else if (y != sizeTable - 1) {
//                    q = x - z;
//                } else {
//                    q = x + z;
//                    oneDirection = "UpLeftBelow";
//                }
//            }
//        }
//        else {
//            if (oneDirection.equals("UpLeftAbove")) {
//                if (table[x + z][y] != 0 && y != sizeTable - 1) {
//                    w = y + z;
//                } else if (y != sizeTable - 1) {
//                    q = x + z;
//                } else {
//                    q = x - z;
//                    oneDirection = "DownLeftAbove";
//                }
//            } else if (oneDirection.equals("DownLeftAbove")) {
//                if (table[x][y - z] != 0 && x != 0) {
//                    q = x - z;
//                } else if (x != 0) {
//                    w = y - z;
//                } else {
//                    w = y + z;
//                    oneDirection = "UpLeftAbove";
//                }
//            } else if (oneDirection.equals("UpLeftBelow")) {
//                if (table[x][y + z] != 0 && x != sizeTable - 1) {
//                    q = x + z;
//                } else if (x != sizeTable - 1) {
//                    w = y + z;
//                } else {
//                    w = y - z;
//                    oneDirection = "DownRightBelow";
//                }
//            } else if (oneDirection.equals("DownRightBelow")) {
//                if (table[x - z][y] != 0 && y != 0) {
//                    w = y - z;
//                } else if (y != 0) {
//                    q = x - z;
//                } else {
//                    q = x + z;
//                    oneDirection = "UpLeftBelow";
//                }
//            }
//        }
        int m = 0;
        while( m == 0){
            if (z == -1) {
                if (y != 0 && table[x][y + z] == 0) {
                    if (!checkFilling(q, y + z)) {
                        table[q][y + z] = 2;
                        c = q;
                        v = y + z;
                    } else {
                        w = y + z;
                        return (endMove(q, w, playerMove, a, s, z));
                    }
                } else if (x != 0 && table[x + z][y] == 0) {
                    if (!checkFilling(x + z, w)) {
                        table[x + z][w] = 2;
                        c = x + z;
                        v = w;
                    } else {
                        q = x + z;
                        return (endMove(q, w, playerMove, a, s, z));
                    }
                } else if (y != sizeTable - 1 && table[x][y - z] == 0) {
                    if (!checkFilling(q, y - z)) {
                        table[q][y - z] = 2;
                        c = q;
                        v = y - z;
                    } else {
                        w = y - z;
                        return (endMove(q, w, playerMove, a, s, z));
                    }
                } else if (x != sizeTable - 1 && table[x - z][y] == 0) {

                    if (!checkFilling(x - z, w)) {
                        table[x - z][w] = 2;
                        c = x - z;
                        v = w;
                    } else {
                        q = x - z;
                        return (endMove(q, w, playerMove, a, s, z));
                    }
                }
                else {
                    m = 1;
                }
            } else {
                if (y != sizeTable - 1 && table[x][y + z] == 0) {
                    if (!checkFilling(q, y + z)) {
                        table[q][y + z] = 2;
                        c = q;
                        v = y + z;
                    } else {
                        w = y + z;
                        return (endMove(q, w, playerMove, a, s, z));
                    }
                } else if (x != 0 && table[x - z][y] == 0) {
                    if (!checkFilling(x - z, w)) {
                        table[x - z][w] = 2;
                        c = x - z;
                        v = w;
                    } else {
                        q = x - z;
                        return (endMove(q, w, playerMove, a, s, z));
                    }
                } else if (y != 0 && table[x][y - z] == 0) {
                    if (!checkFilling(q, y - z)) {
                        table[q][y - z] = 2;
                        c = q;
                        v = y - z;
                    } else {
                        w = y - z;
                        return (endMove(q, w, playerMove, a, s, z));
                    }
                } else if (x != sizeTable - 1 && table[x + z][y] == 0) {

                    if (!checkFilling(x + z, w)) {
                        table[x + z][w] = 2;
                        c = x + z;
                        v = w;
                    } else {
                        q = x + z;
                        return (endMove(q, w, playerMove, a, s, z));
                    }
                }
                else {
                    m = 1;
                }
            }
        }
        table[c][v] = 0;
        return (endMove(c, v, playerMove, a, s, z));
    }


    private int diagonal(int a, int s, int z, int playerMove) {
        int q = x;
        int w = y;
        if (z == -1) {
            if (oneDirection.equals("Up")) {
                if (y - 1 > -1) {
                    w = y + z;
                    oneDirection = "Left";
                }
            } else if (oneDirection.equals("Left")) {
                if (x - 1 > -1) {
                    q = x + z;
                    oneDirection = "Up";
                }
            }
        } else {
            if (oneDirection.equals("Up")) {
                if (y + 1 < sizeTable) {
                    w = y + z;
                    oneDirection = "Down";
                }
            } else if (oneDirection.equals("Down")) {
                if (x + 1 < sizeTable) {
                    q = x + z;
                    oneDirection = "Up";
                }
            }
        }
        return (endMove(q, w, playerMove, a, s, z));
    }

    private int snake(int a, int s, int z, int playerMove) {
        int q = x;
        int w = y;
        if (oneDirection.equals("Down")) {
            w = y + z;
            value--;
            if (value == 0) {
                if ((z == 1 && x == 0) || (z == -1 && x == 9)) {
                    oneDirection = "Right";
                    if (pitchStroke != 1) {
                        pitchStroke++;
                    }
                    value = pitchStroke;
                } else {
                    oneDirection = "Left";
                    value = pitchStroke;
                }
            }
        } else if (oneDirection.equals("Right")) {
            q = x + z;
            value--;
            if (value == 0) {
                if ((z == 1 && y == 0) || (z == -1 && y == 9)) {
                    oneDirection = "Down";
                    pitchStroke++;
                    value = pitchStroke;
                } else {
                    oneDirection = "Up";
                    value = pitchStroke;
                }
            }
        } else if (oneDirection.equals("Up")) {
            w = y - z;
            value--;
            if (value == 0) {
                oneDirection = "Right";
                value = 1;
            }
        } else if (oneDirection.equals("Left")) {
            q = x - z;
            value--;
            if (value == 0) {
                oneDirection = "Down";
                value = 1;
            }
        }
        return (endMove(q, w, playerMove, a, s, z));
    }

    private int around(int a, int s, int z, int playerMove) {
        int q = x;
        int w = y;
        if (oneDirection.equals("Right")) {
            q = x + z;
            value--;
            if (value == 0) {
                if (pitchStroke == 9) {
                    pitchStroke--;
                } else {
                    pitchStroke = pitchStroke - 2;
                }
                oneDirection = "Down";
                value = pitchStroke;
            }
        } else if (oneDirection.equals("Down")) {
            w = y + z;
            value--;
            if (value == 0) {
                oneDirection = "Left";
                value = pitchStroke;
            }
        } else if (oneDirection.equals("Left")) {
            q = x - z;
            value--;
            if (value == 0) {
                oneDirection = "Up";
                pitchStroke = pitchStroke - 2;
                value = pitchStroke;
            }
        } else if (oneDirection.equals("Up")) {
            w = y - z;
            value--;
            if (value == 0) {
                oneDirection = "Right";
                value = pitchStroke;
            }
        }
        return (endMove(q, w, playerMove, a, s, z));
    }

    private int rowByRow(int a, int s, int z, int playerMove) {
        int q = x;
        int w = y;
        if (oneDirection.equals("Right")) {
            if (value == 0) {
                w = y + z;
                oneDirection = "Left";
                value = pitchStroke;
            } else {
                q = x + z;
                value--;
            }
        } else if (oneDirection.equals("Left")) {
            if (value == 0) {
                w = y + z;
                oneDirection = "Right";
                value = pitchStroke;
            } else {
                q = x - z;
                value--;
            }
        }
        return (endMove(q, w, playerMove, a, s, z));
    }

    private boolean check(int a, int s) {
        if (table[a][s] == 0) {
            return true;
        }
        return false;
    }

    private int endMove(int q, int w, int playerMove, int a, int s, int z) {
        if (q > -1 && w > -1 && q < sizeTable && w < sizeTable) {
            if (check(q, w)) {
                x = q;
                y = w;
                return 0;
            }
        }
        return 3;
    }

    private boolean checkFilling(int q, int w) {
        if ((q == 0 || q == 9 || w == 0 || w == 9)) {
            if (q == 0 && w == 0) {
                if (table[q + 1][w] == 0 || table[q][w + 1] == 0) {
                    return true;
                }
            } else if (q != 0 && q != 9 && w == 0) {
                if (table[q - 1][w] == 0 || table[q + 1][w] == 0 || table[q][w + 1] == 0) {
                    return true;
                }
            } else if (q == 9 && w != 0 && w != 9) {
                if (table[q - 1][w] == 0 || table[q][w - 1] == 0 || table[q][w + 1] == 0) {
                    return true;
                }
            } else if (q == 9 && w == 9) {
                if (table[q - 1][w] == 0 || table[q][w - 1] == 0) {
                    return true;
                }
            } else if (q == 9 && w == 0) {
                if (table[q - 1][w] == 0 || table[q][w + 1] == 0) {
                    return true;
                }
            } else if (q != 0 && q != 9 && w == 9) {
                if (table[q - 1][w] == 0 || table[q][w - 1] == 0 || table[q + 1][w] == 0) {
                    return true;
                }
            } else if (q == 0 && w == 9) {
                if (table[q + 1][w] == 0 || table[q][w - 1] == 0) {
                    return true;
                }
            } else if (q == 0 && w != 0 && w != 9) {
                if (table[q + 1][w] == 0 || table[q][w - 1] == 0 || table[q][w + 1] == 0) {
                    return true;
                }
            }
        } else {
            if (table[q - 1][w] == 0 || table[q + 1][w] == 0 || table[q][w - 1] == 0 ||
                    table[q][w + 1] == 0) {
                return true;
            }
        }
        return false;
    }

    public void gameBotPart2(int x, int y, int z, Controller.Tile[][] massiv) throws IOException {
        for (int i = 0; i < sizeTable; i++) {
            for (int j = 0; j < sizeTable; j++) {
                table[i][j] = massiv[i][j].valueCell;
            }
        }
        if (table[x][y] != -z) {// проверить на полное заполнение
            table[x][y] = -z;
        }
        if (tacticsPart2.equals("Attack")) {
            attack(x, y, z);
        }
        if (tacticsPart2.equals("Defence")) {
            defence(x, y, z);
        }
    }

    private void defence(int a, int s, int z) {
        q = x - a;
        w = y - s;
        convergence(q, w, a, s, z);
    }

    private void convergence(int q, int w, int a, int s, int z) {
        if (q > 0 && w > 0) { // слева вверху
            if (x - 1 == a && y == s) {
                table[x][y - 1] = z;
                y = y - 1;
            } else {
                table[x - 1][y] = z;
                x = x - 1;
            }
        } else if (q > 0 && w == 0) {//слева
            if (x - 1 == a && y == s) {
                if (y - 1 >= 0) {
                    table[x][y - 1] = z;
                    y = y - 1;
                } else {
                    table[x][y + 1] = z;
                    y = y + 1;
                }
            } else {
                table[x - 1][y] = z;
                x = x - 1;
            }

        } else if (q > 0 && w < 0) {//слева внизу
            if (x - 1 == a && y == s) {
                table[x][y + 1] = z;
                y = y + 1;

            } else {
                table[x - 1][y] = z;
                x = x - 1;
            }
        } else if (q == 0 && w < 0) {//снизу
            if (y + 1 == s && x == a) {
                if (x + 1 < sizeTable) {
                    table[x + 1][y] = z;
                    x = x + 1;
                } else {
                    table[x - 1][y] = z;
                    x = x - 1;
                }
            } else {
                table[x][y + 1] = z;
                y = y + 1;
            }

        } else if (q < 0 && w < 0) {//справа внизу
            if (x + 1 == a && y == s) {
                table[x][y + 1] = z;
                y = y + 1;

            } else {
                table[x + 1][y] = z;
                x = x + 1;
            }
        } else if (q < 0 && w == 0) {//справа
            if (x + 1 == a && y == s) {
                if (y - 1 >= 0) {
                    table[x][y - 1] = z;
                    y = y - 1;
                } else {
                    table[x][y + 1] = z;
                    y = y + 1;
                }
            } else {
                table[x + 1][y] = z;
                x = x + 1;
            }
        } else if (q < 0 && w > 0) {//справа вверху
            if (x + 1 == a && y == s) {
                table[x][y - 1] = z;
                y = y - 1;

            } else {
                table[x + 1][y] = z;
                x = x + 1;
            }
        } else if (q == 0 && w > 0) {//сверху
            if (y - 1 == s && x == a) {
                if (x + 1 < sizeTable) {
                    table[x + 1][y] = z;
                    x = x + 1;
                } else {
                    table[x - 1][y] = z;
                    x = x - 1;
                }
            } else {
                table[x][y - 1] = z;
                y = y - 1;
            }
        }
    }

    //z - положение бота
    private void attack(int a, int s, int z) throws IOException {
        int minValue = table.length + table.length + 5;
        for (int j = 0; j < table.length; j++) {
            {
                for (int i = 0; i < table.length; i++)
                    if (table[i][j] == -z) {
                        if (minValue > Math.abs(x - i) + Math.abs(y - j)) {
                            minValue = Math.abs(x - i) + Math.abs(y - j);
                            q = i;
                            w = j;
                        }
                    }
            }
        }
        q = x - q;
        w = y - w;
        convergence(q, w, a, s, z);
    }
}