package net.myselfDeb.journalApp.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import net.myselfDeb.journalApp.entity.User;

public class UserArgumentsProvider implements ArgumentsProvider {
 
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
            Arguments.of(User.builder().userName("Debdatta").password("Debdatta").build()),
            Arguments.of(User.builder().userName("Deb").password("Deb").build())
        );
    }
}
