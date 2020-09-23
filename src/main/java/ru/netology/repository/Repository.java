package ru.netology.repository;


import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repository {
    private List<Issue> items = new ArrayList<>();

    public void save(Issue item) {
        items.add(item);
    }

    public List<Issue> returnAll() {
        return items;
    }

    public Issue findById(int id) {
        for (Issue item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {  //удалять по id
        items.removeIf(issues -> issues.getId() == id);
    }

    public boolean addAll(Collection<Issue> items) {    //добавить всё
        return this.items.addAll(items);
    }

    public boolean add (List<Issue> issues){ //??????
        return items.addAll(items);
    }


}
