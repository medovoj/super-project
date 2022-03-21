package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public List<CartItem> list = new ArrayList<>();

    public List<CartItem> getAllItems(){
        return list;
    }

    public void add (CartItem cartItem){
        list.add(cartItem);
    }

    public void remove (String id){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)){
                list.remove(list.get(i));
                break;
            }
        }
    }
}
