package transactions;/**
 * @author Evgeny Borisov
 * @since 3.2
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Component
public @interface RegisterUDF {
}
