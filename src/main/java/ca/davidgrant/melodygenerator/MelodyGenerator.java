package ca.davidgrant.melodygenerator;

import com.google.common.collect.ImmutableList;

import java.util.*;

public class MelodyGenerator {

  public List<List<String>> generate(MelodyGeneratorConfig config, List<String> notes) {
    List<List<String>> ret = new ArrayList<>();
    final List<List<Integer>> generate = generate(config);
    for (List<Integer> pattern : generate) {
      List<String> melody = new ArrayList<>();
      for (int index : pattern) {
        melody.add(notes.get(index-1));
      }
      ret.add(melody);
    }
    return ret;
  }

  public List<List<Integer>> generate(MelodyGeneratorConfig config) {
    List<List<Integer>> patterns = new ArrayList<>();
    for (int i=0; i < config.getNumNotes(); i++) {
      List<List<Integer>> newPatterns = new ArrayList<>();
      if (i==0) {
        // Starting note
        for (int j : config.getStartingNotes()) {
          newPatterns.add(ImmutableList.of(j));
        }
      } else {
        for (List<Integer> pattern : patterns) {
          int lastNote = pattern.get(pattern.size() - 1);
          List<Integer> candidateNotes = getCandidateNotes(i == config.getNumNotes() - 1, config.getEndingNotes(), config.getBiggestGap(), config.getPossibleNotes(), lastNote);
          for (int newNote : candidateNotes) {
            if ((count(pattern, newNote) < config.getMaxOccurrences() && countDuplicates(pattern, newNote) <= config.getMaxDuplicates())

            && (((lastNote != newNote && !check_for_repeated(pattern, newNote, config.getMaxSameInARow())) && numberOfChangesOfDirection(pattern, newNote) < config.getMaxChangesInDirection())
                || (lastNote == newNote && countRepeats(pattern) < config.getMaxRepetitionsTotal()))) {
              List<Integer> newPattern = new ArrayList<>(pattern);
              newPattern.add(newNote);
              newPatterns.add(newPattern);
            }
          }
        }
      }
      patterns = newPatterns;
    }
    Collections.shuffle(patterns);
    return patterns;
  }

  private List<Integer> getCandidateNotes(boolean isLastNote, List<Integer> endingNotes, int biggestGap, List<Integer> possibleNotes, int lastNote) {
    List<Integer> ret = new ArrayList<>();
    if (isLastNote) {
      for (int endingNote : endingNotes) {
        if (Math.abs(endingNote - lastNote) <= biggestGap) {
          ret.add(endingNote);
        }
      }
    } else {
      for (int gap = -biggestGap; gap < biggestGap + 1; gap++) {
        final int newNote = gap + lastNote;
        if (possibleNotes.contains(newNote)) {
          ret.add(newNote);
        }
      }
    }
    return ret;
  }

  private int countRepeats(List<Integer> pattern) {
    int count = 0;
    for (int i=0; i < pattern.size() - 1; i++) {
      if (pattern.get(i).equals(pattern.get(i+1))) {
        count++;
      }
    }
    return count;
  }

  private int count(List<Integer> pattern, int note) {
    int count = 0;
    for (int n : pattern) {
      if (note == n) {
        count++;
      }
    }
    return count;
  }

  private boolean check_for_repeated(List<Integer> pattern, int note, int maxSameInARow) {
    int count = 0;
    for (int i=pattern.size()-1; i >= 0; i--) {
      if (pattern.get(i) == note) {
        count++;
        if (count == maxSameInARow) {
          return true;
        }
      } else {
        return false;
      }
    }
    return false;
  }

  int numberOfChangesOfDirection(List<Integer> pattern, int newNote) {
    List<Integer> copy = new ArrayList<>(pattern);
    copy.add(newNote);
    int count = 0;
    int direction = 0;
    for (int i=0; i < copy.size() - 1; i++) {
      if (copy.get(i+1) > copy.get(i)) {
        if (direction < 0) {
          count++;
        }
        direction = 1;
      } else if (copy.get(i+1) < copy.get(i)) {
        if (direction > 0) {
          count++;
        }
        direction = -1;
      }
    }
    return count;
  }

  int countDuplicates(List<Integer> pattern, int newNote) {
    List<Integer> copy = new ArrayList<>(pattern);
    copy.add(newNote);
    Set<Integer> set = new HashSet<>(copy);
    return copy.size() - set.size();
  }
}
