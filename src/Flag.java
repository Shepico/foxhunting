class Flag {

    private MatrixGame flagMap;

    void start() {
        flagMap = new MatrixGame(Box.empty);
        //flagMap.set(new Coord(4,4), Box.open );
    }

    Box get (Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.open);
    }

    void setFlagToBox(Coord coord) {
        flagMap.set(coord, Box.flag);
    }

    void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.empty);
    }

    void toggleFlagToBox(Coord coord) {
        switch (flagMap.get(coord)){
            case flag: setClosedToBox(coord); break;
            case empty: setFlagToBox(coord); break;
        }
    }
}
