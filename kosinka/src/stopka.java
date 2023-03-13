import java.util.ArrayList;
public class stopka {
    private ArrayList<karta> lst;
    public stopka(){
        lst = new ArrayList<karta>();
    }
    public karta get(int nom){
        return lst.get(nom);
    }
    public void add(karta elem){
        lst.add(elem);
    }
    public void remove(int nom){
        lst.remove(nom);
    }
    public int size(){
        return lst.size();
    }
    public void clear(){
        lst.clear();
    }
}