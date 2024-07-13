package cft.intensive.potato.core.services.user.implementations;

import cft.intensive.potato.core.services.user.HashCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

@Component
@RequiredArgsConstructor
public class HashCalculatorImp implements HashCalculator {

    public long createHash(String str)  {
        CharacterIterator characterIterator = new StringCharacterIterator(str);

        long hash = 0;

        while (characterIterator.current() != CharacterIterator.DONE) {
            hash += characterIterator.current() % 31;
            characterIterator.next();
        }

        return hash;
    }
}
