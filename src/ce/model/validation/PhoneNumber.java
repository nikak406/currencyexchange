package ce.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

//TODO override messages
//TODO change pattern to match +380631234567
@Pattern.List({
		@Pattern(regexp = "/\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/")
})
@Constraint(validatedBy = {})
@Documented
@Target({ ElementType.METHOD,
		ElementType.FIELD,
		ElementType.ANNOTATION_TYPE,
		ElementType.CONSTRUCTOR,
		ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

	String message() default "Bad phone number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ElementType.METHOD,
			ElementType.FIELD,
			ElementType.ANNOTATION_TYPE,
			ElementType.CONSTRUCTOR,
			ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		PhoneNumber[] value();
	}
}