/*
import java.util.ArrayList;
import java.util.List;

class Solution {
    int minX = 100001;
    int minY = 100001;

    int maxX = -100001;
    int maxY = -100001;

    List<Point> list = new ArrayList<>();

    public String[] solution(int[][] line) {
        String[] answer = {};
        getPoint(line);
        answer = printStars();
        return answer;
    }

    public void getPoint(int[][] line) {
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                getPoints(line[i][0], line[i][1], line[i][2], line[j][0], line[j][1], line[j][2]);
            }
        }
    }

    public void getPoints(int a, int b, int c, int d, int e, int f) {
        int ae_bd = a * e - b * d;
        int ce_fd = c * e - f * d;
        if (ae_bd != 0 && ce_fd % ae_bd == 0) { // 나누어 떨어진다면, 평행하지 않다면
            int x = ce_fd / ae_bd;
            if ((a * x * (-1) - c) % b == 0) {
                int y = (a * x * (-1) - c) / b;
                checkMinMax(x, y);
                Point point = new Point(x, y);
                list.add(point);
            }
        }
    }

    public class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return this.x;
        }

        int getY() {
            return this.y;
        }
    }

    public void checkMinMax(int x, int y) {
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
    }

    public String[] printStars() {
        char[][] stars = new char[maxX - minX + 1][maxY - minY + 1];
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= minY; j++) {
                stars[i - minX][j - minY] = '.';
            }
        }
        for (Point p : list) {
            int x = p.getX();
            int y = p.getY();
            stars[x - minX][y - minY] = '*';
        }

        String[] result = new String[stars.length];
        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[i].length; j++) {
                System.out.print(stars[i]);
            }
            System.out.println();
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = String.valueOf(stars[i]);
        }

        return result;
    }
}
*/

import java.util.HashSet;
import java.util.Arrays;
class Solution {
    static long minx= Long.MAX_VALUE, miny= Long.MAX_VALUE;
    static long maxx= Long.MIN_VALUE, maxy= Long.MIN_VALUE;
    
    public String[] solution(int[][] line) {
        String[] answer = {};
        HashSet<Point> pSet= new HashSet<>();
        
        //x= (bf-ed)/(ad-bc)
        //y= (ec-af)/(ad-bc)
        long x, y;
        for(int i=0; i<line.length-1; i++){
            long a= line[i][0];
            long b= line[i][1];
            long e= line[i][2];
            for(int j=i+1; j<line.length; j++){
                long c= line[j][0];
                long d= line[j][1];
                long f= line[j][2];
                
                long adbc= a*d-b*c;
                if(adbc==0) continue; //비교대상 직선과 평행함
                
                long bfed= b*f-e*d;
                if(bfed%adbc!=0) continue; //x가 정수가 아님
                
                long ecaf= e*c-a*f;
                if(ecaf%adbc!=0) continue; //y가 정수가 아님
                
                x= bfed/adbc;
                y= ecaf/adbc;
                pSet.add(new Point(x, y));
                
                minx= Math.min(minx, x);
                miny= Math.min(miny, y);
                maxx= Math.max(maxx, x);
                maxy= Math.max(maxy, y);
            }
        }
        
        long height= maxy-miny+1;
        long width= maxx-minx+1;
        answer= new String[(int)height];
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<width; i++){
            sb.append(".");
        }
        
        Arrays.fill(answer, sb.toString());
        
        long nx, ny;
        for(Point p: pSet){
            ny= maxy-p.y;
            nx= p.x-minx;
            answer[(int)ny]= answer[(int)ny].substring(0, (int)nx)+"*"
                        +answer[(int)ny].substring((int)nx+1);
        }
        
        return answer;
    }
    
    public class Point{
        long x;
        long y;
        public Point(long x, long y){
            this.x= x;
            this.y= y;
        }
    }
}
