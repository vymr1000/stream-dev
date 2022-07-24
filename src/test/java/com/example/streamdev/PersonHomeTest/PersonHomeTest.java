package com.example.streamdev.PersonHomeTest;

import com.example.streamdev.domain.Home.Home;
import com.example.streamdev.domain.Person.Person;
import com.example.streamdev.domain.PersonHome.PersonHome;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonHomeTest {

    @Test
    public void init() {
        // 데이터 생성
        List<String> nameData = Arrays.asList("김태주", "한강우", "Steve", "Nicole", "Alden", "류완범", "이보배", "Mia", "Choi사랑");
        List<String> aptData = Arrays.asList("태주 아파트", "강우 아파트", "S 아파트", "N 아파트", "A 아파트", "류 아파트", "보배 아파트", "M 아파트", "C 아파트");

        // 객체를 담을 리스트 생성
        List<Person> people = new ArrayList<>();
        List<Home> houses = new ArrayList<>();
        List<PersonHome> personHouses = new ArrayList<>();

        // 임의의 ID를 생성하여 객체를 생성하고 리스트에 넣어준다. personHouses에는 매핑정보가 들어있다.
        for(int i=0; i<nameData.size(); i++) {
            int personId = (int) (Math.random()*100);
            int homeId = (int) (Math.random()*100);

            people.add(generaterPerson(personId, nameData.get(i)));
            houses.add(generaterHome(homeId, aptData.get(i)));
            personHouses.add(generaterPeopleHome(personId, homeId));
        }

        // 결과 출력
        printPeopleWithEnglishName(people);
        printAll(people, houses, personHouses);
    }

    // Person 객체를 생성하는 메소드.
    public Person generaterPerson(Integer id, String name) {
        return Person.builder()
                .id(id)
                .name(name)
                .build();
    }

    // Home 객체를 생성하는 메소드.
    public Home generaterHome(Integer id, String name) {
        return Home.builder()
                .id(id)
                .name(name)
                .build();
    }

    // PersonHome 객체를 생성하는 메소드.
    public PersonHome generaterPeopleHome(Integer personId, Integer homeId) {
        return PersonHome.builder()
                .personId(personId)
                .homeId(homeId)
                .build();
    }

    // 영어 이름을 가진 사람을 출력하는 메소드.
    public void printPeopleWithEnglishName(List<Person> people) {
        List<String> names = people.stream()
                .filter(e -> e.getName().matches("^[a-zA-Z]*$"))
                .map(Person::getName).collect(Collectors.toList());

        System.out.println(names);
    }

    // PersonHome에 담긴 매핑정보를 가지고 Person, Home에서 이름을 가져와 Pair에 저장한다.
    public void printAll(List<Person> people, List<Home> houses, List<PersonHome> personHouses) {
        List<Pair<String, String>> obj = personHouses.stream()
                .map(
                        e -> {
                            Person p = people.stream().filter(j -> e.getPersonId().equals(j.getId())).findFirst().get();
                            Home h = houses.stream().filter(k -> e.getHomeId().equals(k.getId())).findFirst().get();
                            return new Pair<String, String>(p.getName(), h.getName());
                        }
                )
                .collect(Collectors.toList());

        // Pair 출력.
        obj.stream()
                .forEach(e -> System.out.println("(" + e.personName + ", " + e.homeName + ")"));
    }

    // Pair 클래스 선언.
    class Pair<T1, T2> {
        private T1 personName;
        private T2 homeName;

        Pair(T1 personName, T2 homeName) {
            this.personName = personName;
            this.homeName = homeName;
        }

        public T1 getPersonName() {
            return this.personName;
        }

        public T2 getHomeName() {
            return this.homeName;
        }

        public String toString() {
            return "(" + personName + ", " + homeName + ")";
        }
    }
}
