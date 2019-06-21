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

  @Before
  public void setUp() {
    melodyGenerator = new MelodyGenerator();
  }

  @Test
  public void test_grade1() {
    System.out.println("C major");
    final List<List<String>> generateC = melodyGenerator.generate(MelodyGeneratorConfigs.GRADE_1, ImmutableList.of("C", "D", "E", "F", "G"));
    for (List<String> pattern : generateC) {
      System.out.println(Arrays.toString(pattern.toArray()));
    }
    System.out.println("G major");
    final List<List<String>> generateG = melodyGenerator.generate(MelodyGeneratorConfigs.GRADE_1, ImmutableList.of("G", "A", "B", "C", "D"));
    for (List<String> pattern : generateG) {
      System.out.println(Arrays.toString(pattern.toArray()));
    }
    System.out.println("A minor");
    final List<List<String>> generateA = melodyGenerator.generate(MelodyGeneratorConfigs.GRADE_1, ImmutableList.of("A", "B", "C", "D", "E"));
    for (List<String> pattern : generateA) {
      System.out.println(Arrays.toString(pattern.toArray()));
    }
  }

  @Test
  public void test_3note() {
    System.out.println("C major");
    final List<List<String>> generateC = melodyGenerator.generate(MelodyGeneratorConfigs.BASIC_3_NOTE, ImmutableList.of("C", "D", "E", "F", "G"));
    for (List<String> pattern : generateC) {
      System.out.println(Arrays.toString(pattern.toArray()));
    }
    System.out.println("G major");
    final List<List<String>> generateG = melodyGenerator.generate(MelodyGeneratorConfigs.BASIC_3_NOTE, ImmutableList.of("G", "A", "B", "C", "D"));
    for (List<String> pattern : generateG) {
      System.out.println(Arrays.toString(pattern.toArray()));
    }
    System.out.println("A minor");
    final List<List<String>> generateA = melodyGenerator.generate(MelodyGeneratorConfigs.BASIC_3_NOTE, ImmutableList.of("A", "B", "C", "D", "E"));
    for (List<String> pattern : generateA) {
      System.out.println(Arrays.toString(pattern.toArray()));
    }
  }

  @Test
  public void test_4note() {
    final List<List<String>> generate = melodyGenerator.generate(MelodyGeneratorConfigs.BASIC_4_NOTE, ImmutableList.of("C", "D", "E", "F", "G"));
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