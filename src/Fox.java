public class Fox {
    private MatrixGame foxMap;
    private int totalFox;

    Fox(int totalFox) {
        this.totalFox = totalFox;
    }

    void start(){
        foxMap = new MatrixGame(Box.empty);
        for (int i=0; i<totalFox; i++){
            placeFox();
        }
        placeNum();

    }

    Box get (Coord coord) {
        return foxMap.get(coord);
    }

    private void placeFox(){
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.fox == foxMap.get(coord)) {
                continue;
            }
            foxMap.set(coord, Box.fox);
            break;
        }

    }

    private void placeNum() {
        for(Coord coord:Ranges.getAllCoord()){
            if (Box.fox !=foxMap.get(coord)) {
               foxMap.set(coord, Box.valueOf("number"+calculateFox(coord)));
            }
        }
    }

    private int calculateFox(Coord coord) {
        int fox=0;
        int x = coord.getX();
        int y = coord.getY();
        int sizeMap = Ranges.getSize().getX();
            for (int i = 0; i<sizeMap; i++) {
                //if (mapFox[i][y] == 'f') {
                if (Box.fox ==foxMap.get(new Coord(i,y))) {
                    fox++;
                }
            }
            for (int i = 0; i<sizeMap; i++) {
                //if (mapFox[x][i] == 'f') {
                if (Box.fox ==foxMap.get(new Coord(x,i))) {
                    fox++;
                }
            }
            /*int st = x < y ? x : y;
            int dx = x-st;
            int dy = y-st;
            for (int i=dx, j=dy; i<sizeMap & j<sizeMap; i++, j++){
                //if (mapFox[i][j] == 'f') {
                if (Box.fox ==foxMap.get(new Coord(i,j))){
                    fox++;
                }
            }

            /*dy = y + x;
            dx = 0;

            for (int i=dx, j=dy; i<0; i++, j--) {
                //if (mapFox[i][j] == 'f') {
                if (Box.fox ==foxMap.get(new Coord(i,j))){
                    fox++;
                }
            }

            //String str = ""+fox;
            //map[x][y] = str.charAt(0);*/

        return fox;
    }

}
