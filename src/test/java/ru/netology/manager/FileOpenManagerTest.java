package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenManagerTest {
    private FileOpenManager fileOpenManager = new FileOpenManager();

    @BeforeEach
    public void before() {
        fileOpenManager.save("doc", "Word");
        fileOpenManager.save("str", "Str");
    }

    @Test
    public void registerNewApp() {                                //Регистрировать новое приложение
        Map<String, String> mapT = new HashMap<>();
        mapT.put("ipg", "AddSee");
        mapT.put("doc", "Word");
        mapT.put("str", "Str");
        Map expected = mapT;
        Map actual = fileOpenManager.registerNewApp("ipg", "AddSee");
        assertEquals(expected, actual);
    }

    @Test
    public void getApplicationToOpenFile() {    //Получать название приложения, предназначенного для открытия файла с определённым расширением
        String expected = "Word";
        String actual = String.valueOf(fileOpenManager.getApplicationToOpenFile("doc"));
        assertEquals(expected, actual);
    }

    @Test
    public void removeTheBindingApplications() {     //    Удалять привязку приложения к определённому расширению
        Map<String, String> mapT = new HashMap<>();
        mapT.put("doc", "Word");
        Map expected = mapT;
        Map actual = fileOpenManager.removeTheBindingApplications("str");
        assertEquals(expected, actual);
    }

    @Test
    public void getListAllRegisteredExtensionss() {     //    Получать список всех зарегистрированных расширений
        Set<String> expected = Set.of("str", "doc");
        Set actual = fileOpenManager.getListAllRegisteredExtensionss();
        assertEquals(expected, actual);
    }


    @Test
    public void getListAllApp() {     //    Получать список всех приложений
        List<String> expected = List.of("Str", "Word");
        ArrayList actual = fileOpenManager.getListAllApp();
        assertEquals(expected, actual);
    }


}