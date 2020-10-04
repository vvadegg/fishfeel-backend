package ru.fishfeel.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueEmail {
    String message() default "пользователь с таким адресом уже зарегистрирован";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
