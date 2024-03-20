package com.example.pontecontact.validator;

import com.example.pontecontact.dto.incoming.AddressCreationCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddressCreationCommandValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AddressCreationCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target!=null){
            AddressCreationCommand command=(AddressCreationCommand) target;
            for (int i = 0; i < command.getPostalCode().toString().length(); i++) {
                if(!Character.isDigit(command.getPostalCode().toString().charAt(i))){
                    errors.rejectValue("postalCode","not.valid.postal.code");
                }
            }
        }
    }
}
