package kz.maks.sxa.policies;

import kz.maks.sxa.ElementMatchResult;
import org.jsoup.nodes.Element;

public interface MatchingPolicy {

    void match(Element elementOrigin, Element elementPotential, ElementMatchResult elementMatchResult);

}
