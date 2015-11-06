package ce.model.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = PhoneNumber.Validator.class)
@Documented
@Target({ ElementType.METHOD,
		ElementType.FIELD,
		ElementType.ANNOTATION_TYPE,
		ElementType.CONSTRUCTOR,
		ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

	String message() default "Invalid phone number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	class Validator implements ConstraintValidator<PhoneNumber, String> {
		@Override
		public void initialize(PhoneNumber phoneNumber) {
		}

		@Override
		public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
			s = s.replaceAll("[-()_+/.]", ""); 	  //remove all special chars from string
			return s.matches("\\d{8,14}");        //more than 8, less than 14 digits in number, only digits
		}
	}
}