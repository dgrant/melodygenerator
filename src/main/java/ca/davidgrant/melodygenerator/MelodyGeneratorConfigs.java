package ca.davidgrant.melodygenerator;

import com.google.common.collect.ImmutableList;

public class MelodyGeneratorConfigs {
  public static final MelodyGeneratorConfig BASIC_3_NOTE = MelodyGeneratorConfig.builder()
      .biggestGap(2)
      .possibleNotes(ImmutableList.of(1, 2, 3, 4, 5))
      .numNotes(3)
      .startingNotes(ImmutableList.of(1, 5))
      .endingNotes(ImmutableList.of(1, 2, 3, 4, 5))
      .biggestGap(2)
      .maxOccurrences(2)
      .maxSameInARow(2)
      .maxRepetitionsTotal(1)
      .maxChangesInDirection(2)
      .maxDuplicates(1)
      .build();

  public static final MelodyGeneratorConfig BASIC_4_NOTE = MelodyGeneratorConfig.builder()
      .biggestGap(2)
      .possibleNotes(ImmutableList.of(1, 2, 3, 4, 5))
      .numNotes(4)
      .startingNotes(ImmutableList.of(1, 5))
      .endingNotes(ImmutableList.of(1, 2, 3, 4, 5))
      .biggestGap(2)
      .maxOccurrences(2)
      .maxSameInARow(2)
      .maxRepetitionsTotal(1)
      .maxChangesInDirection(2)
      .maxDuplicates(1)
      .build();

  public static final MelodyGeneratorConfig GRADE_1 = MelodyGeneratorConfig.builder()
      .biggestGap(2)
      .possibleNotes(ImmutableList.of(1, 2, 3, 4, 5))
      .numNotes(5)
      .startingNotes(ImmutableList.of(1, 5))
      .endingNotes(ImmutableList.of(1, 3, 5))
      .biggestGap(2)
      .maxOccurrences(2)
      .maxSameInARow(2)
      .maxRepetitionsTotal(1)
      .maxChangesInDirection(2)
      .maxDuplicates(1)
      .build();
}
