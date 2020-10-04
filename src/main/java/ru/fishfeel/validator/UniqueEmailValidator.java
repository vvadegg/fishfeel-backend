package ru.fishfeel.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.fishfeel.domain.User;
import ru.fishfeel.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        if (userService == null) {return true;}

        try {
            User user = userService.findByEmail(value);
            return user == null;
        } catch (UsernameNotFoundException e) {
            return true;
        }

    }
}
