package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerTest {
    private Manager manager = new Manager();
    private Repository repository = new Repository();

    private Issue issue1 = new Issue(1, "Petrov", "Header1", "Text1", true,
            new HashSet<String>(Arrays.asList("label1", "label2")),
            new HashSet<String>(Arrays.asList("assignee2", "assignee1")));

    private Issue issue2 = new Issue(2, "Ivanov", "Header2", "Text2", false,
            new HashSet<String>(Arrays.asList("label3", "label2")),
            new HashSet<String>(Arrays.asList("assignee1", "assignee4")));

    private Issue issue3 = new Issue(3, "Ivanov", "Header3", "Text3", false,
            new HashSet<String>(Arrays.asList("label1", "label4")),
            new HashSet<String>(Arrays.asList("assignee5", "assignee3")));

    private Issue issue4 = new Issue(4, "Arseev", "Header4", "Text4", true,
            new HashSet<String>(Arrays.asList("label5", "label6")),
            new HashSet<String>(Arrays.asList("assignee3", "assignee2")));

    @BeforeEach
    public void before() {
        manager.save(issue2);
        manager.save(issue4);
        manager.save(issue1);
        manager.save(issue3);
    }

    @Test
    public void saveIssuesNew() {                                     //Добавление Issue
        Issue issue3_N = new Issue(300, "Ivanov+++", "Header3+++", "Text3+++", true,
                new HashSet<String>(Arrays.asList("label1", "label2")),
                new HashSet<String>(Arrays.asList("assignee1", "assignee2")));
        manager.save(issue3_N);
        List<Issue> expected = List.of(issue2, issue4, issue1, issue3, issue3_N);
        List<Issue> actual = manager.returnAll();
        assertEquals(expected, actual);
    }

    @Test
    public void saveIssues() {
        Collection<Issue> expected = List.of(issue1, issue2, issue3, issue4);
        repository.addAll(expected);
        Collection<Issue> actual = repository.returnAll();
        assertEquals(expected, actual);
    }

    @Test
    public void listOpen() {                                     //Отображение списка открытых Issue
        Collection<Issue> expected = List.of(issue4, issue1);
        Collection<Issue> actual = manager.listOpenClosed(true);
        assertEquals(expected, actual);
    }

    @Test
    public void listClosed() {                                   //Отображение списка закрытых Issue
        List<Issue> expected = List.of(issue2, issue3);
        List<Issue> actual = manager.listOpenClosed(false);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAuthor() {                               //По имени автора (кто создал) без Predicate
        List<Issue> expected = List.of(issue2, issue3);
        List<Issue> actual = manager.filterByAuthor("Ivanov");
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAuthorWithPredicate() {                   //По имени автора (кто создал) через Predicate
        Predicate<String> equalsByAuthor = t -> t.equals("Ivanov");
        List<Issue> expected = List.of(issue2, issue3);
        List<Issue> actual = manager.filterByAuthorWithPredicate(equalsByAuthor);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByLabelWithPredicate() {                                 //    По Label'у
        List<Issue> expected = List.of(issue1, issue3);
        String label = "label1";
        Predicate<String> equalsByLabel = a -> a.equals(label);
        List<Issue> actual = manager.filterByLabelWithPredicate(equalsByLabel);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAssigneeWithPredicate() {                             //    По Assignee (на кого назначено)
        List<Issue> expected = List.of(issue3);
        Predicate<String> equalsDyAssignee = t -> t.equals("assignee5");
        List<Issue> actual = manager.filterByAssigneeWithPredicate(equalsDyAssignee);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAssigneeFor2AssWithPredicate() {                        //    По Assignee (на кого назначено)
        List<Issue> expected = List.of(issue4, issue1);
        Predicate<String> equalsDyAssignee = t -> t.equals("assignee2");
        List<Issue> actual = manager.filterByAssigneeWithPredicate(equalsDyAssignee);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAssigneeFor2Ass() {                        //    По Assignee (на кого назначено)
        List<Issue> expected = List.of(issue4, issue1);
        List<Issue> actual = manager.filterByAssignee("assignee2");
        assertEquals(expected, actual);
    }

    @Test
    public void sortById() {                               //сортировка по Id
        List<Issue> expected = List.of(issue1, issue2, issue3, issue4);
        List<Issue> actual = manager.sortById();
        assertEquals(expected, actual);
    }

    @Test
    public void sortByAutor() {                               //сортировка по Autor
        List<Issue> expected = List.of(issue4, issue2, issue3, issue1);
        List<Issue> actual = manager.sortByAutor();
        assertEquals(expected, actual);
    }

    @Test
    public void openClosedById() {                                  //Закрытие/открытие Issue по id
        Issue issue_N = new Issue(4, "Arseev", "Header4", "Text4", false,
                new HashSet<String>(Arrays.asList("label5", "label6")),
                new HashSet<String>(Arrays.asList("assignee3", "assignee2")));
        List<Issue> expected = List.of(issue_N);
        List<Issue> actual = Arrays.asList(manager.openClosedById(4));
        assertEquals(expected, actual);
    }

}