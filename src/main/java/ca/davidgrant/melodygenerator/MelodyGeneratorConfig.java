package ca.davidgrant.melodygenerator;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MelodyGeneratorConfig {
  List<Integer> possibleNotes;
  int numNotes;
  List<Integer> startingNotes;
  List<Integer> endingNotes;
  int biggestGap;
  int maxSameInARow;
  int maxOccurrences;
  int maxRepetitionsTotal;
  int maxChangesInDirection;
  int maxDuplicates;
}
