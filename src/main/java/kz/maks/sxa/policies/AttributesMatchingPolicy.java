package kz.maks.sxa.policies;

import kz.maks.sxa.ElementMatchResult;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

public class AttributesMatchingPolicy implements MatchingPolicy {
    private static final String MESSAGE_FORMAT_CLASSNAME = "Element class name match: %s";
    private static final String MESSAGE_FORMAT_ATTRIBUTE = "Element attribute match: %s = %s";

    @Override
    public void match(Element elementOrigin, Element elementPotential, ElementMatchResult elementMatchResult) {
        for (Attribute attributeOrigin : elementOrigin.attributes()) {
            String attrVal = elementPotential.attr(attributeOrigin.getKey());
            if (attrVal != null && attrVal.trim().length() != 0) {
                if (attributeOrigin.getKey().equals("class")) {
                    for (String className : elementOrigin.classNames()) {
                        if (elementPotential.hasClass(className)) {
                            elementMatchResult.addMatch(String.format(MESSAGE_FORMAT_CLASSNAME, className));
                        }
                    }
                } else if (attrVal.equals(attributeOrigin.getValue())) {
                    elementMatchResult.addMatch(String.format(MESSAGE_FORMAT_ATTRIBUTE,
                            attributeOrigin.getKey(), attrVal));
                }
            }
        }
    }

}
