package kz.maks.sxa.policies;

import kz.maks.sxa.ElementMatchResult;
import org.jsoup.nodes.Element;

public class TagNameMatchingPolicy implements MatchingPolicy {
    private static final String MESSAGE_FORMAT = "Element tag name match: %s";

    @Override
    public void match(Element elementOrigin, Element elementPotential, ElementMatchResult elementMatchResult) {
        if (elementOrigin.tagName().equals(elementPotential.tagName())) {
            elementMatchResult.addMatch(String.format(MESSAGE_FORMAT, elementPotential.tagName()));
        }
    }

}
