public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int y, int x){this.x = x; this.y = y;}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {return x;}
    public int getY(){return y;}

    public void up(){this.y -= 1;}
    public void down(){this.y += 1;}
    public void left(){this.x -= 1;}
    public void right(){this.x += 1;}

    public void move(String direction){
        if(direction.equals("U")){this.up();}
        else if(direction.equals("D")){this.down();}
        else if(direction.equals("L")){this.left();}
        else if(direction.equals("R")){this.right();}
//        System.out.println(direction);
    }

}
