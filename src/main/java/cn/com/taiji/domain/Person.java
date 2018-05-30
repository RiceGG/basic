package cn.com.taiji.domain;

import java.util.List;

import lombok.Data;

@Data
public class Person {
private String name;
private Integer age;
private List<String> hobby;
public Person(String name, Integer age, List<String> hobby) {
	super();
	this.name = name;
	this.age = age;
	this.hobby = hobby;
}


}
