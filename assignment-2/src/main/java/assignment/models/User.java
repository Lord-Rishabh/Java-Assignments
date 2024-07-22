package assignment.models;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a User with personal details and courses.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements java.io.Serializable {

  private String fullName;

  private int age;

  private String address;

  private String rollNumber;

  private Set<Character> courses = new HashSet<>();
}
