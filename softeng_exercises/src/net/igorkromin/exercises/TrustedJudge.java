package net.igorkromin.exercises;

import net.igorkromin.Exercise;

import java.util.*;

/**
 * A city wants to elect a judge.
 *
 * Rules:
 * 1. Everyone trusts the judge
 * 2. The judge trusts no one
 *
 * Assumption:
 * There can only be 0 or 1 judges
 *
 * Write a function to find the judge.
 */
public class TrustedJudge extends Exercise {

    private class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }
    }

    HashMap<Person, List<Person>> relationships;

    // -- Elimination solution
    // Run time complexity is O(n)
    // Space complexity is O(n)

    public void solveViaElimination() {
        populateTrustRelationships();

        // create a set of candidate judges out of all people
        Set<Person> candidateJudges = new HashSet<>();
        candidateJudges.addAll(relationships.keySet());

        // eliminate any people who 'trust' someone else since they can't be the judge
        for (Person person : relationships.keySet()) {
            if (relationships.get(person).size() > 0) {
                candidateJudges.remove(person);
            }
        }

        // check if there are any candidates left
        if (candidateJudges.size() == 0) {
            out("No judges found");
        }
        // now check if the candidate judge is trusted by everyone
        else {
            // assumption: only 1 judge can exist
            Person judge = candidateJudges.iterator().next();

            boolean isTrusted = true;
            for (Person person : relationships.keySet()) {
                if (judge != person && !relationships.get(person).contains(judge)) {
                    isTrusted = false;
                    break;
                }
            }

            if (isTrusted) {
                out("Found Judge: " + judge.name);
            }
            else {
                out("No Judge trusted by all");
            }
        }
    }

    /**
     * Creates a map structure that stores the following trust relationships.
     *
     * Bob -> Dredd <- Jane -+
     *  ^     ^   ^          |
     *  |     |    \        /
     *  +-- Mike   Sarah <-+
     */
    private void populateTrustRelationships() {

        relationships = new HashMap<>();

        Person bob = new Person("Bob");
        Person jane = new Person("Jane");
        Person mike = new Person("Mike");
        Person sarah = new Person("Sarah");
        Person dredd = new Person("Dredd");

        ArrayList<Person> bobRels = new ArrayList<>();
        ArrayList<Person> janeRels = new ArrayList<>();
        ArrayList<Person> mikeRels = new ArrayList<>();
        ArrayList<Person> sarahRels = new ArrayList<>();
        ArrayList<Person> dreddRels = new ArrayList<>();

        bobRels.add(dredd);
        janeRels.add(dredd);
        mikeRels.add(dredd);
        sarahRels.add(dredd);

        mikeRels.add(bob);
        janeRels.add(sarah);

        relationships.put(bob, bobRels);
        relationships.put(jane, janeRels);
        relationships.put(mike, mikeRels);
        relationships.put(sarah, sarahRels);
        relationships.put(dredd, dreddRels);
    }


    // -- Main method

    public static final void main(String[] args) {
        run(new TrustedJudge());
    }

}
