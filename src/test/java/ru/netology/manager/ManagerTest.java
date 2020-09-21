package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issues;
import ru.netology.repository.Repository;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerTest {
    //private Repository repository = new Repository();
    private Manager manager = new Manager();
    private Repository repository = new Repository();
    private Issues issues1 = new Issues(1, "Petrov", "Header1", "Text1", true);
    private Issues issues2 = new Issues(2, "Ivanov", "Header2", "Text2", false);
    private Issues issues3 = new Issues(3, "Ivanov", "Header3", "Text3", false);
    private Issues issues4 = new Issues(4, "Arseev", "Header4", "Text4", true);


    @BeforeEach
    public void before() {
        manager.save(issues2);
        manager.save(issues4);
        manager.save(issues1);
        manager.save(issues3);
        Set<String> label1 = issues1.getLables();
        label1.add("Label1_1");
        label1.add("Label1_2");
        Set<String> label2 = issues2.getLables();
        label2.add("Label2_1");
        label2.add("Label2_2");
        label2.add("Label2_3");
        Set<String> label4 = issues4.getLables();
        label4.add("Label4_1");
        Set<String> assignee1 = issues1.getAssignees();
        assignee1.add("Assignee1_1");
        assignee1.add("Assignee1_2");
        Set<String> assignee3 = issues3.getAssignees();
        assignee3.add("Assignee3_1");
        assignee3.add("Assignee3_2");
        assignee3.add("Assignee3_3");
        assignee3.add("Assignee1_1");

    }

    @Test
    public void saveIssuesNew() {                                     //Добавление Issue
        Issues issues3_N = new Issues(300, "Ivanov+++", "Header3+++", "Text3+++", true);
        manager.save(issues3_N);
        List<Issues> expected = List.of(issues2, issues4, issues1, issues3, issues3_N);
        List<Issues> actual = manager.returnAll();
//        System.out.println(manager.returnAll());
        assertEquals(expected, actual);
    }

    @Test
    public void saveIssues() {
        Collection<Issues> expected = List.of(issues1, issues2, issues3, issues4);
        repository.addAll(expected);
        Collection<Issues> actual = repository.returnAll();
        assertEquals(expected, actual);
    }

    @Test
    public void listOpen() {                                         //Отображение списка открытых Issue
        Collection<Issues> expected = List.of(issues4, issues1);
        Collection<Issues> actual = manager.listOpenClosed(true);
        assertEquals(expected, actual);
    }

    @Test
    public void listClosed() {                                          //Отображение списка закрытых Issue
        List<Issues> expected = List.of(issues2, issues3);
        List<Issues> actual = manager.listOpenClosed(false);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAuthor() {                                      //По имени автора (кто создал)
        List<Issues> expected = List.of(issues2, issues3);
        List<Issues> actual = manager.filterByAuthor("Ivanov");
        assertEquals(expected, actual);
    }

    @Test
    public void filterByLabel() {                                  //    По Label'у
        List<Issues> expected = List.of(issues1);
        Collection<Issues> actual = manager.filterByLabel("Label1_2");
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAssignee() {                             //    По Assignee (на кого назначено)
        List<Issues> expected = List.of(issues3);
        List<Issues> actual = manager.filterByAssignee("Assignee3_2");
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAssigneeFor2Ass() {                        //    По Assignee (на кого назначено)
        List<Issues> expected = List.of(issues1, issues3);
        List<Issues> actual = manager.filterByAssignee("Assignee1_1");
        assertEquals(expected, actual);
    }

    @Test
    public void sortById() {                               //сортировка по Id
        List<Issues> expected = List.of(issues1, issues2, issues3, issues4);
        List<Issues> actual = manager.sortById();
        assertEquals(expected, actual);
    }

    @Test
    public void sortByAutor() {                               //сортировка по Autor
        List<Issues> expected = List.of(issues4, issues2, issues3, issues1);
        List<Issues> actual = manager.sortByAutor();
        assertEquals(expected, actual);
    }

    @Test
    public void openClosedById() {                                  //Закрытие/открытие Issue по id
        System.out.println("до    изменений "+issues4);
        Issues issues4_N = new Issues(4, "Arseev", "Header4", "Text4", false);
        Set<String>l=issues4_N.getLables();
        l.add("Label4_1");
        List<Issues> expected = List.of(issues4_N);
        List<Issues> actual = Arrays.asList(manager.openClosedById(4));
        System.out.println("после изменений "+issues4);
        assertEquals(expected, actual);
    }

}