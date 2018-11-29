package kz.maks.sxa;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ElementMatchResult implements Comparable<ElementMatchResult> {
    private final Element element;
    private int score;
    private final List<String> reasons = new ArrayList<>();

    public ElementMatchResult(Element element) {
        this.element = element;
        this.score = 0;
    }

    public Element getElement() {
        return element;
    }

    public int getScore() {
        return score;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public void addMatch(String reason) {
        reasons.add(reason);
        score++;
    }

    public String getPath() {
        Elements parents = element.parents();
        Collections.reverse(parents);
        parents.add(element);
        return parents.stream()
                .map(el -> el.nodeName() + "[" + el.elementSiblingIndex() + "]")
                .collect(Collectors.joining(" > "));
    }

    @Override
    public int compareTo(ElementMatchResult o) {
        return o.score - score;
    }
}
