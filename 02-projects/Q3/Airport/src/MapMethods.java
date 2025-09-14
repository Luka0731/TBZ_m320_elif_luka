import java.util.HashMap;
import java.util.Scanner;

public class MapMethods {
Scanner scanner = new Scanner (System.in);
    public void showAll(HashMap<String,String> airports){
        for(HashMap.Entry<String, String> entry:airports.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
    public void search(HashMap<String,String> airports){
        System.out.println("Enter the  which key you want to search");
        String name=scanner.nextLine();
       if (airports.containsKey(name)){
           System.out.println("The airport "+name+" has been found");
            System.out.println(name+":"+ airports.get(name));
        }
        else{
            System.out.println("There is no such airport!");
        }

    }

    public HashMap<String,String> addAirport(HashMap<String,String> airports){
        System.out.println("Enter the which key you want to add");
        String key=scanner.nextLine();
        System.out.println("Enter the  which value you want to add");
        String value=scanner.nextLine();
        if(airports.containsKey(key)|| airports.containsValue(value) || airports.containsKey(key) && airports.containsValue(value) ){
            System.out.println("There is already an such airport!");
        }
        else {
            airports.put(key,value);
            System.out.println("Successfully added!");
        }
        return airports;
    }

    public HashMap<String,String> removeAirport(HashMap<String,String> airports){
        System.out.println("Enter the key of the aiport you want to remove");
        String key=scanner.nextLine();
        if(airports.containsKey(key)){
            airports.remove(key);
        }
        else {
            System.out.println("There is no such airport!");
        }
        return airports;
    }

}
