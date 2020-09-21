package ru.netology.manager;

import ru.netology.domain.Issues;
import ru.netology.repository.Repository;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class Manager {
    private Repository repository = new Repository();

    public void save(Issues item) {
        repository.save(item);
    }

    public List<Issues> returnAll() {
        return repository.returnAll();
    }

    public List<Issues> listOpenClosed(Boolean index) {
        List<Issues> result = new ArrayList<>();
        for (Issues issues : repository.returnAll())
            if (index == issues.getOpenClosed()) {
                result.add(issues);
            }
        return result;
    }

    public List<Issues> filterByAuthor(String antor) {
        List<Issues> resuit = new ArrayList<>();
        for (Issues issues : returnAll()) {
            if (antor.equals(issues.getAuthor())) {
                resuit.add(issues);
            }
        }
        return resuit;
    }

//    Predicate<Issues> issuesPredicate=new Predicate<Issues>() {
//    @Override
//    public boolean test(Issues issues) {
//        return false;
//    }

    public List<Issues> filterByLabel(String label) {      //    По Label'у
        List<Issues> result = new ArrayList<>();
        for (Issues issues : returnAll()) {
            Set<String> labels = issues.getLables();
            if (labels.contains(label)) {
                result.add(issues);
            }
//            if (issuesPredicate.test(issues)) {            //х.з. как передать 2 значения
        }
        return result;
    }

    public List<Issues> filterByAssignee(String assignee) {      //    По Assignee (на кого назначено)
        List<Issues> result = new ArrayList<>();
        for (Issues issues : returnAll()) {
            Set<String> set = issues.getAssignees();
            if (set.contains(assignee)) result.add(issues);
        }
        return result;
    }

    Comparator<Issues> comparatorId = new Comparator<Issues>() {
        @Override
        public int compare(Issues o1, Issues o2) {
            return o1.getId() - o2.getId();
        }
    };

    public List<Issues> sortById() {
        List<Issues> issuesList = new ArrayList<>(repository.returnAll());
        Collections.sort(issuesList, comparatorId);
        return issuesList;
    }

    Comparator<Issues> comparatorAutor = new Comparator<Issues>() {
        @Override
        public int compare(Issues o1, Issues o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    };

    public List<Issues> sortByAutor() {
        List<Issues> issuesList = new ArrayList<>(repository.returnAll());
        Collections.sort(issuesList, comparatorAutor);
        return issuesList;
    }

    public Issues openClosedById(int id) {              //Закрытие/открытие Issue по id
        Issues result = repository.findById(id);
        boolean openClosed = result.getOpenClosed();
        result.setOpenClosed(!openClosed);
        return result;
    }
}
