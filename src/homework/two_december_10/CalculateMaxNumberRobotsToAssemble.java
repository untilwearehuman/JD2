package homework.two_december_10;

import java.util.*;
import java.util.stream.Collectors;

public class CalculateMaxNumberRobotsToAssemble {
    private List<String> professorPartList;

    public int getRobotsQuantity(List<String> professorPartList) {

        Map<String, Integer> frequencyPartsRepeat = professorPartList.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));

        Map.Entry<String, Integer> minNumberOfParts = frequencyPartsRepeat.entrySet().stream()
                .min(Map.Entry.comparingByValue()).orElseThrow();

        return minNumberOfParts.getValue();

    }
}