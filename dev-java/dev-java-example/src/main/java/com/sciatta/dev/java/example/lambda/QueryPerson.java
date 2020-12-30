package com.sciatta.dev.java.example.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by yangxiaoyu on 2018/4/4<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Lambda替代匿名类
 */
public class QueryPerson {

    public interface CheckPerson {
        boolean test(Person person);    //接口只有一个方法可以使用Lambda
    }

    // 检查逻辑放到自定义接口实现中
    public void printPerson(List<Person> roster, CheckPerson check) {
        for (Person person : roster) {
            if (check.test(person)) {
                System.out.println(person.getName());
            }
        }
    }

    // 使用function工具类，使得实现抽象化
    public void printPersonWithFunction(List<Person> roster, Predicate<Person> tester, Function<Person, String> mapper, Consumer<String> block) {
        for (Person person : roster) {
            if (tester.test(person)) {
                String result = mapper.apply(person);
                block.accept(result);
            }
        }

    }

    // 使用聚集Stream
    public void printMalePersonWithAggregate(List<Person> roster) {
        roster.stream().filter(person -> person.getGender() == Person.Sex.MALE).forEach(person -> System.out.println(person.getName()));
    }

    // 使用聚集Stream，传递可扩展参数
    public void printPersonWithAggregateAndMethod(List<Person> roster, Predicate<Person> tester, Consumer<Person> result) {
        roster.stream().filter(tester).forEach(result);
    }

    public void sortAndPrint(List<Person> personList, Comparator<Person> comparator) {
        Collections.sort(personList, comparator);
        personList.forEach(p -> System.out.println(p.getName()));
    }
}
