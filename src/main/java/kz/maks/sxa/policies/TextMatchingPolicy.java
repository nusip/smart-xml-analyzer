package kz.maks.sxa.policies;

import kz.maks.sxa.ElementMatchResult;
import org.jsoup.nodes.Element;

public class TextMatchingPolicy implements MatchingPolicy {
    private static final String MESSAGE_FORMAT = "Element text match: %s";

    @Override
    public void match(Element elementOrigin, Element elementPotential, ElementMatchResult elementMatchResult) {
        if (elementOrigin.hasText() && elementPotential.hasText() &&
                elementOrigin.text().equals(elementPotential.text())) {
            elementMatchResult.addMatch(String.format(MESSAGE_FORMAT, elementPotential.text()));
        }
    }

}
