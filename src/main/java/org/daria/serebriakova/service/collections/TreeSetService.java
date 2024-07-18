package org.daria.serebriakova.service.collections;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class TreeSetService {
    static TreeSet<Person> people = new TreeSet<>();

    static {
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        people.add(new Person("David", 40));
        people.add(new Person("Eve", 20));
        people.add(new Person("Frank", 32));
    }

    /*
     * Using first method to get the first (lowest) element
     * First Person: Eve (20)
     */
    public Person getFirst() {
        return people.first();
    }

    /*
     * Using last method to get the last (highest) element
     * Last Person: David (40)
     */
    public Person getLast() {
        return people.last();
    }

    /*
     * The tailSet method is used to get a view of people aged 'age' inclusive or older.
     * TailSet (aged 30 or older): [Alice (30), Frank (32), Charlie (35), David (40)]
     */
    public Set<Person> getTail(int age) {
        return people.tailSet(new Person("", age));
    }

    /*
     * Using subSet method to get people aged between 'age1' inclusive and 'age2' exclusive.
     * SubSet (aged 25 to 35): [Bob (25), Alice (30), Frank (32)]
     */
    public Set<Person> getSubSet(int age1, int age2) {
        return people.subSet(new Person("", age1), new Person("", age2));
    }

    /*
     * Using headSet method to get people aged less than 30 'age' exclusive.
     * HeadSet (aged less than 30): [Eve (20), Bob (25)]
     */
    public Set<Person> getHeadSet(int age) {
        return people.headSet(new Person("", age));
    }

    /*
     * Using floor method to find the greatest element less than or equal to age,
     * or null if there is no such element.
     * Floor Person (<= 34): Frank (32)
     */
    public Person getFloor(int age) {
        return people.floor(new Person("", age));
    }

    /*
     * Using ceiling method to find the smallest element greater than or equal to age,
     * or null if there is no such element.
     * Ceiling Person (<= 34): Charlie (35)
     */
    public Person getCeiling(int age) {
        return people.ceiling(new Person("", age));
    }

    /*
     * Using lower method to find the greatest element strictly less than age,
     * or null if there is no such element.
     * Lower Person (<= 30): Bob (25)
     */
    public Person getLower(int age) {
        return people.lower(new Person("", age));
    }

    /*
     * Using higher method to find the smallest element strictly greater than age,
     * or null if there is no such element.
     * Higher Person (> 30): Frank (32)
     */
    public Person getHigher(int age) {
        return people.higher(new Person("", age));
    }

    /*
     * Adding a new person to the original TreeSet
     * The tailSet view reflects the changes in the original TreeSet
     *      people.add(new Person("George", 38));
     *      Updated HeadSet (aged less than 30): [Alice (30), Frank (32), Charlie (35), David (40), George (38)]
     * The subSet view reflects the changes in the original TreeSet:
     *      people.add(new Person("George", 28));
     *      Updated SubSet (aged 25 to 35): [Bob (25), George (28), Alice (30), Frank (32)]
     * The headSet view reflects the changes in the original TreeSet
     *      people.add(new Person("George", 28));
     *      Updated HeadSet (aged less than 30): [Eve (20), Bob (25), George (28)]
     * The first and last methods reflect the changes in the original TreeSet
     *      people.add(new Person("George", 18));
     *      Updated First Person: George (18)
     *      people.add(new Person("Jo", 58));
     *      Updated Last Person: Jo (58)
     */
    public void addPerson(String name, int age) {
        people.add(new Person(name, age));
    }

    /*
     * The Person class implements Comparable to define natural ordering based on age.
     */
    @Data
    public static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}
