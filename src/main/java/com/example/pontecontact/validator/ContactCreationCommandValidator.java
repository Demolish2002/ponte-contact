package com.example.pontecontact.validator;

import com.example.pontecontact.dto.incoming.ContactCreationCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ContactCreationCommandValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ContactCreationCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target!=null){
            ContactCreationCommand command=(ContactCreationCommand) target;
            if(!(command.getEmail().contains("@")||command.getEmail().contains("."))){
                errors.rejectValue("email","not.an.email");
            }
        }
    }
}
