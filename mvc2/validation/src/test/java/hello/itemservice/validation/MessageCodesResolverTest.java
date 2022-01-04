package hello.itemservice.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    public void messageCodesResolverObject() throws Exception {
        //given
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        //when

        //then
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    public void messageCodesResolverField() throws Exception {
        //given
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        //when

        //then
        assertThat(messageCodes).containsExactly(
            "required.item.itemName",
            "required.itemName",
            "required.java.lang.String",
            "required"
        );
    }







}
