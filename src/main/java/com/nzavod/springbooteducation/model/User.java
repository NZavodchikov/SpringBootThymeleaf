package com.nzavod.springbooteducation.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Pattern(regexp = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$",
             message = "Can nott be empty. Must start with a capital letter. Must be in the same language.")
    private String firstName;
    @Pattern(regexp = "^$|^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$",
             message = "Can be empty.Must start with a capital letter. Must be in the same language.")
    private String secondName; /*отчество*/
    @Pattern(regexp = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$",
             message = "Can not be empty. Must start with a capital letter. Must be in the same language.")
    private String lastName;
    @NotNull(message = "Can not be empty")
    @Min(value = 18,
         message = "Age must be greater or equal 18")
    private Integer age;
    @NotNull(message = "Salary can't be 0")
    private Integer salary;
    @NotEmpty
    @Email(message = "Email is incorrect")
    private String email;
    @NotEmpty
    private String work;

    @Override
    public String toString() {
        return firstName + " " +
               secondName + " " +
                lastName + " " +
                age + " " +
                salary + " " +
                email + " " +
                work + " " +
               "\n";
    }
}
