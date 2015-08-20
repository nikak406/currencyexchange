package ce.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Pattern.List({
		@Pattern(regexp = "[1-9]\\d*")
})
@Constraint(validatedBy = {})
@Documented
@Target({ ElementType.METHOD,
		ElementType.FIELD,
		ElementType.ANNOTATION_TYPE,
		ElementType.CONSTRUCTOR,
		ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NaturalNumber {

	String message() default "Bad number";

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
		NaturalNumber[] value();
	}
}