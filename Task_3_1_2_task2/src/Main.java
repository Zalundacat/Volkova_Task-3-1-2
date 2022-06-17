import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minors = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println(minors);

        List<String> recruits = persons.stream()
                .filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() > 17 && age.getAge() < 28)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(recruits);

        List<Person> workers = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(woman -> woman.getAge() > 17 && woman.getAge() < 61)
                .filter(man -> man.getAge() > 17 && man.getAge() < 66)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workers);
    }
}
