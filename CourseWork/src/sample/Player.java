package sample;

public class Player {

    /**coordinates of the game field*/
    private int x, y, move;


    public Player(int x, int y){
        this.x = x;
        this.y = y;
        move = 0;
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setMove(int c ){ move = c;}

    public int getMove(){return move;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean checkPosition(int w, int h){
        if (((x - 1 == w) || (x + 1 == w) || (x == w)) &&
                ((y - 1 == h) || (y + 1 == h) || (y == h))) {
            if ((x - 1 == w) && (y == h)) {
                return true;
            }
            if ((x == w) && (y - 1 == h)) {
                return true;
            }
            if ((x == w) && (y + 1 == h)) {
                return true;
            }
            if ((x + 1 == w) && (y  == h)) {
                return true;
            }
        }
        return false;
    }
}