import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private File mapDataFile;
    private Cell[][] cells;
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;


    public Map(File mapDataFile) throws NullPointerException, FileNotFoundException {
        this.mapDataFile = mapDataFile;
        LoadMap();
    }

    private void LoadMap() throws FileNotFoundException {
        Scanner reader = new Scanner(mapDataFile);
        ArrayList<String[]> parsedSentenceList = new ArrayList<>();

        String sentence = "";
        int width = 0;
        int height = 0;

        int min = 0;
        int max = 0;

        //기본 맵을 구축하기 위해 cells의 width와 height를 구하여 할당
        while(reader.hasNext()){
            sentence = reader.nextLine();
//            System.out.println(sentence);
            String[] parsedSentence = parseSentence(sentence);
            parsedSentenceList.add(parsedSentence);

            width += countWidth(parsedSentence);
            height += countHeight(parsedSentence);

            if (height<min){
                min = height; //맵 높이의 최소상대값(START기준)를 구함
//                System.out.println("new min: "+min);
            }
            if (height>max){
                max = height; //맵 높이의 최대상대값(START기준)를 구함
//                System.out.println("new max: "+max);
            }
        }

        width += 1; //맵의 너비
        height = max - min + 1; //맵의 높이
        System.out.println("width:"+width+" height:"+height);

        cells = new Cell[height][width];


        Coordinate coord = new Coordinate(0-min,0); //map의 start 시작지점 정함
        startCoordinate = new Coordinate(0-min, 0); //시작지점 정보 저장
        //기본 맵의 정보를 채우는 작업
        for(int i = 0; i<parsedSentenceList.size(); i++){
            Cell tempCell = new Cell();
            String[] tempStringArray = parsedSentenceList.get(i);

            if(i != 0){tempCell.setCard(MakeGame.findCardType(tempStringArray[0]));} //시작줄은 S cell이지만 Saw의 S와 겹치므로 제외
            else{}

            for(int j=1; j<tempStringArray.length; j++){tempCell.addDirection(tempStringArray[j]);} //cell에 방향 저장

            cells[coord.getY()][coord.getX()] = tempCell;

            if(i != parsedSentenceList.size()-1){
                coord.move(tempCell.getDirectionList().get(tempCell.getDirectionList().size()-1)); //다음 계산을 위해 이동}
            }else{
                endCoordinate = new Coordinate(coord.getY(), coord.getX());
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Coordinate getStartCoordinate() {
        return startCoordinate;
    }

    public Coordinate getEndCoordinate() {
        return endCoordinate;
    }

    private String[] parseSentence(String sentence){
        String[] parsedSentence = sentence.split(" ");

        return parsedSentence;
    }

    private int countWidth(String[] parsedSentence){
        for(String split : parsedSentence){
            if(split.equals("R")){return 1;}
        }
        return 0;
    }

    private int countHeight(String[] parsedSentence){
        String direction = "";
        if(parsedSentence.length == 3) {direction = parsedSentence[1]+parsedSentence[2]; /*System.out.println(direction);*/}
        if(direction.equals("LD") || direction.equals("UD")){return 1;}
        else if(direction.equals("LU") || direction.equals("DU")){return -1;}
        else{return 0;}
    }
}
