package ru.netology.repository;


import ru.netology.domain.Issues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repository {
    private List<Issues> items = new ArrayList<>();

    public void save(Issues item) {
        items.add(item);
    }

    public List<Issues> returnAll() {
        return items;
    }

    public Issues findById(int id) {
        for (Issues item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {  //удалять по id
        items.removeIf(issues -> issues.getId() == id);
    }

    public boolean addAll(Collection<Issues> items) {    //добавить всё
        return this.items.addAll(items);
    }

    public boolean add (List<Issues> issues){ //??????
        return items.addAll(items);
    }


}
