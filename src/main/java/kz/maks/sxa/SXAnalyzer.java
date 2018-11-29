package kz.maks.sxa;

import kz.maks.sxa.policies.AttributesMatchingPolicy;
import kz.maks.sxa.policies.MatchingPolicy;
import kz.maks.sxa.policies.TagNameMatchingPolicy;
import kz.maks.sxa.policies.TextMatchingPolicy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SXAnalyzer {
    private static final String DEFAULT_ORIGIN_TARGET_ELEMENT_ID = "make-everything-ok-button";
    private static final String DEFAULT_POTENTIALS_SELECTOR = "a, button, input[type=button], input[type=submit]";
    private static final String DEFAULT_CHARSET = "UTF-8";

    private List<MatchingPolicy> matchingPolicies = Arrays.asList(
            new TagNameMatchingPolicy(),
            new TextMatchingPolicy(),
            new AttributesMatchingPolicy()
    );

    private final Element elementOrigin;
    private final Elements elementsPotential;
    private final List<ElementMatchResult> elementMatchResults = new ArrayList<>();


    public SXAnalyzer(String inputOriginFilePath, String inputOtherFilePath, String targetElementId) throws IOException {
        this(inputOriginFilePath, inputOtherFilePath, targetElementId, null);
    }

    public SXAnalyzer(String inputOriginFilePath, String inputOtherFilePath, String targetElementId,
                      List<MatchingPolicy> matchingPolicies) throws IOException {
        targetElementId = targetElementId != null ? targetElementId : DEFAULT_ORIGIN_TARGET_ELEMENT_ID;

        if (matchingPolicies != null) {
            this.matchingPolicies = matchingPolicies;
        }

        File fileOrigin = new File(inputOriginFilePath);
        File fileOther = new File(inputOtherFilePath);

        Document docOrigin = Jsoup.parse(fileOrigin, DEFAULT_CHARSET, fileOrigin.getAbsolutePath());
        Document docOther = Jsoup.parse(fileOther, DEFAULT_CHARSET, fileOther.getAbsolutePath());

        elementOrigin = docOrigin.getElementById(targetElementId);
        elementsPotential = docOther.select(DEFAULT_POTENTIALS_SELECTOR);
    }

    public ElementMatchResult analyze() {
        for (Element elementPotential : elementsPotential) {
            ElementMatchResult elementMatchResult = new ElementMatchResult(elementPotential);

            for (MatchingPolicy matchingPolicy : matchingPolicies) {
                matchingPolicy.match(elementOrigin, elementPotential, elementMatchResult);
            }
            elementMatchResults.add(elementMatchResult);
        }

        Collections.sort(elementMatchResults);
        return elementMatchResults.isEmpty() ? null : elementMatchResults.get(0);
    }

}
