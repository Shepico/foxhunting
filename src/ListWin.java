import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ListWin implements Serializable {

    private Map<Double, String> listWin = null;

    public ListWin(Double rating, String name){
        if (listWin == null) {
            listWin = new TreeMap<Double, String>(Collections.reverseOrder());
        }
        addListWin(rating, name);
    }

    public ListWin(){
        if (listWin == null) {
            listWin = new TreeMap<Double, String>(Collections.reverseOrder());
        }
    }

    public void addListWin(Double rating, String name) {
        listWin.put(rating, name);
    }

    public String[] getStringAllWin() {
        String[] result = new String[10];
        int i = 0;
        for (Map.Entry <Double, String> e: listWin.entrySet()) {
            result[i] = e.getValue() + " - " + e.getKey();
            i++;
            if (i == 10) {
                continue; //прерываем
            }
        }
        return result;
    }

    public int getSizeListWin() {
        return listWin.size();
    }

}