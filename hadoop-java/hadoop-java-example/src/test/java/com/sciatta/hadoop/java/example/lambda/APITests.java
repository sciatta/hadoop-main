package com.sciatta.hadoop.java.example.lambda;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by yangxiaoyu on 2019/1/26<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * APITests
 */
public class APITests {

    private QueryPerson queryPerson;

    @Before
    public void init() {
        queryPerson = new QueryPerson();
    }

    @Test
    public void testPrintPerson() {
        // 传入函数作为参数
        queryPerson.printPerson(maker(), person -> person.getGender() == Person.Sex.MALE);

    }

    @Test
    public void testPrintPersonWithFunction() {
        queryPerson.printPersonWithFunction(maker(), person -> person.getGender() == Person.Sex.MALE, person -> person.getName(), name -> System.out.println(name));
    }

    @Test
    public void testPrintPersonWithAggregate() {
        queryPerson.printMalePersonWithAggregate(maker());
    }

    @Test
    public void testPrintPersonWithAggregateAndMethod() {
        queryPerson.printPersonWithAggregateAndMethod(maker(), person -> person.getGender() == Person.Sex.MALE, person -> System.out.println(person.getName()));
    }
    
    // @@方法引用示例@@

    // 类的静态方法引用
    @Test
    public void testStaticMethodReference() {
        // 匿名类
        // Comparator -> int compare(T o1, T o2)
        queryPerson.sortAndPrint(maker(), new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println();

        // lambda表达式
        queryPerson.sortAndPrint(maker(), (o1, o2) -> o1.getName().compareTo(o2.getName()));

        System.out.println();

        // @@静态方法引用，参数一致
        // static int desc(Person a, Person b)
        queryPerson.sortAndPrint(maker(), Person::desc);
    }

    // 对象的实例方法引用
    @Test
    public void testInstanceMethodReference() {
        // @@对象的成员方法引用，参数一致
        Person person = new Person();
        
        // int compare(T o1, T o2)
        // int asc(Person a, Person b)
        queryPerson.sortAndPrint(maker(), person::asc);
    }

    // 类的实例方法引用
    @Test
    public void testAnyClassInstanceMethodReference() {
        // @@类的任意实例的实例方法引用，可以把实际函数的第一个参数提前的类的函数传入
        // @@实际@@ int compare(T o1, T o2) -> int o1.compare(o2) -> @@lambda@@ O.compare(o2)
        queryPerson.sortAndPrint(maker(), Person::compareTo);
    }

    // 构造函数引用
    @Test
    public void testConstructorMethodReference() {
        // @@选择有参数，还是无参数的构造函数，取决于被调用方法的lambda表达式，用的是推断的方法
        // 构造器引用（无参）
        Person person = maker(Person::new);
        assertNotNull(person);

        // 构造器引用（一个参数）
        List<Person> personList = maker(Person::new, Arrays.asList("a", "b", "c"));
        assertEquals(3, personList.size());
    }

    private List<Person> maker(Function<String, Person> personSupplier, List<String> names) {
        List<Person> ret = new ArrayList<>();
        for (String name : names) {
            ret.add(personSupplier.apply(name));
        }
        return ret;
    }

    private Person maker(Supplier<Person> personSupplier) {
        return personSupplier.get();
    }

    private List<Person> maker() {
        List<Person> ps = new ArrayList<>();

        Person a = new Person();
        a.setName("A");
        a.setGender(Person.Sex.MALE);

        Person b = new Person();
        b.setName("B");
        b.setGender(Person.Sex.FEMALE);

        Person c = new Person();
        c.setName("C");
        c.setGender(Person.Sex.MALE);

        ps.add(a);
        ps.add(b);
        ps.add(c);
        return ps;
    }
}
