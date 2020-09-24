package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class Manager {
    private Repository repository = new Repository();

    public void save(Issue item) {
        repository.save(item);
    }

    public List<Issue> returnAll() {
        return repository.returnAll();
    }

    public List<Issue> listOpenClosed(Boolean index) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.returnAll())
            if (index == issue.getOpenClosed()) {
                result.add(issue);
            }
        return result;
    }

    public List<Issue> filterByAuthor(String author) {
        List<Issue> resuit = new ArrayList<>();
        for (Issue issue : returnAll()) {
            if (author.equals(issue.getAuthor())) {
                resuit.add(issue);
            }
        }
        return resuit;
    }

    public List<Issue> filterByAuthorWithPredicate(Predicate<String> byAuthor) {
        List<Issue> resuit = new ArrayList<>();
        for (Issue issue : returnAll()) {
            if (byAuthor.test(issue.getAuthor())) {
                resuit.add(issue);
            }
        }
        return resuit;
    }

    public List<Issue> filterByLabelWithPredicate(Predicate<String> predicateLabel) {      //    По Label'у
        List<Issue> result = new ArrayList<>();
        for (Issue issue : returnAll()) {
            HashSet<String> labels = issue.getLable();
            for (String label : labels) {
                if (predicateLabel.test(label)) {
                    result.add(issue);
                }
            }
        }
        return result;
    }

    public List<Issue> filterByAssigneeWithPredicate(Predicate<String> prAssignee) {      //    По Assignee через Predicate
        List<Issue> result = new ArrayList<>();
        for (Issue issue : returnAll()) {
            HashSet<String> assignees = issue.getAssignee();
            for (String assignee : assignees)
                if (prAssignee.test(assignee)) {
                    result.add(issue);
                }
        }
        return result;
    }

    public List<Issue> filterByAssignee(String assignee) {                //    По Assignee (на кого назначено)
        List<Issue> result = new ArrayList<>();
        for (Issue issue : returnAll()) {
            Set<String> set = issue.getAssignee();
            if (set.contains(assignee)) result.add(issue);
        }
        return result;
    }

    Comparator<Issue> comparatorId = new Comparator<Issue>() {
        @Override
        public int compare(Issue o1, Issue o2) {
            return o1.getId() - o2.getId();
        }
    };

    public List<Issue> sortById() {
        List<Issue> issueList = new ArrayList<>(repository.returnAll());
        Collections.sort(issueList, comparatorId);
        return issueList;
    }

    Comparator<Issue> comparatorAutor = new Comparator<Issue>() {
        @Override
        public int compare(Issue o1, Issue o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    };

    public List<Issue> sortByAutor() {
        List<Issue> issueList = new ArrayList<>(repository.returnAll());
        Collections.sort(issueList, comparatorAutor);
        return issueList;
    }

    public Issue openClosedById(int id) {              //Закрытие/открытие Issue по id
        Issue result = repository.findById(id);
        boolean openClosed = result.getOpenClosed();
        result.setOpenClosed(!openClosed);
        return result;
    }
}
