package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.PostService;

@Component
public class PostCredentialsWriteValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return PostCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostCredentials writePostForm = (PostCredentials) target;
            String rawTags = writePostForm.getRawTags();
            if (rawTags.contains("  ") || rawTags.startsWith(" ") || rawTags.endsWith(" ")) {
                errors.rejectValue("rawTags", "rawTags.empty", "empty tag is not allowed");
            }
            if (!rawTags.matches("[A-Za-z ]+")) {
                errors.rejectValue("rawTags", "rawTags.incorrect-tag", "tag can be only word with latin letters");
            }
        }
    }
}
