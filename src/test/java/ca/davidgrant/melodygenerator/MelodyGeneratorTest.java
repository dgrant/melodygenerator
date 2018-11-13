package ca.davidgrant.melodygenerator;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class MelodyGeneratorTest {

  private MelodyGenerator melodyGenerator;
  private MelodyGeneratorConfig config2;
  private MelodyGeneratorConfig config3;
  private MelodyGeneratorConfig config5;

  @Before
  public void setUp() {
    melodyGenerator = new MelodyGenerator();
    config2 = MelodyGeneratorConfig.builder()
        .biggestGap(2)
        .possibleNotes(ImmutableList.of(1,2,3,4,5))
        .numNotes(2)
        .startingNotes(ImmutableList.of(1,5))
        .endingNotes(ImmutableList.of(1,2,3,4,5))
        .biggestGap(2)
        .maxOccurrences(2)
        .maxSameInARow(2)
        .maxRepetitionsTotal(1)
        .build();
    config3 = MelodyGeneratorConfig.builder()
        .biggestGap(2)
        .possibleNotes(ImmutableList.of(1,2,3,4,5))
        .numNotes(3)
        .startingNotes(ImmutableList.of(1,5))
        .endingNotes(ImmutableList.of(1,2,3,4,5))
        .biggestGap(2)
        .maxOccurrences(2)
        .maxSameInARow(2)
        .maxRepetitionsTotal(1)
        .build();
    config5 = MelodyGeneratorConfig.builder()
        .biggestGap(2)
        .possibleNotes(ImmutableList.of(1,2,3,4,5))
        .numNotes(5)
        .startingNotes(ImmutableList.of(1,5))
        .endingNotes(ImmutableList.of(1,3,5))
        .biggestGap(2)
        .maxOccurrences(2)
        .maxSameInARow(2)
        .maxRepetitionsTotal(1)
        .maxChangesInDirection(2)
        .maxDuplicates(1)
        .build();
  }


  @Test
  public void test() {
    final List<List<String>> generate = melodyGenerator.generate(config5, ImmutableList.of("C", "D", "E", "F", "G"));
    for (List<String> pattern : generate) {
      System.out.println(Arrays.toString(pattern.toArray()));
    }
  }

  @Test
  public void numberOfChangesOfDirection_ascending() {
    int count = melodyGenerator.numberOfChangesOfDirection(ImmutableList.of(1, 2, 3, 4), 5);
    assertThat(count).isEqualTo(0);
  }

  @Test
  public void numberOfChangesOfDirection_upAndDown() {
    int count = melodyGenerator.numberOfChangesOfDirection(ImmutableList.of(1, 2, 3, 2), 1);
    assertThat(count).isEqualTo(1);
  }

  @Test
  public void numberOfChangesOfDirection_upAndDownAndUp() {
    int count = melodyGenerator.numberOfChangesOfDirection(ImmutableList.of(1, 2, 3, 2), 3);
    assertThat(count).isEqualTo(2);
  }

  @Test
  public void numberOfChangesOfDirection_upAndDownLots() {
    int count = melodyGenerator.numberOfChangesOfDirection(ImmutableList.of(1, 2, 1, 2), 1);
    assertThat(count).isEqualTo(3);
  }

  @Test
  public void numberOfChangesOfDirection_flat() {
    int count = melodyGenerator.numberOfChangesOfDirection(ImmutableList.of(1, 1, 1, 1), 1);
    assertThat(count).isEqualTo(0);
  }

}