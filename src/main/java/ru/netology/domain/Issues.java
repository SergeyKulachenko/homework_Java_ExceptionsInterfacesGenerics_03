package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Issues{
    private int id;
    private String author;
    private String header;
    private String body;
    // private Data data;
    private Boolean openClosed;

    private Set<String> lables = new HashSet<>();

    private Set<String> assignees = new HashSet<>();

    public Issues(int id, String author, String header, String body, Boolean openClosed) {
        this.id = id;
        this.author = author;
        this.header = header;
        this.body = body;
        this.openClosed = openClosed;
        this.lables = lables;
        this.assignees = assignees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issues)) return false;
        Issues issues = (Issues) o;
        return id == issues.id &&
                Objects.equals(author, issues.author) &&
                Objects.equals(header, issues.header) &&
                Objects.equals(body, issues.body) &&
                Objects.equals(openClosed, issues.openClosed) &&
                Objects.equals(lables, issues.lables) &&
                Objects.equals(assignees, issues.assignees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, header, body, openClosed, lables, assignees);
    }
}
