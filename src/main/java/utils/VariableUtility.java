package utils;

import storing.LinkedList;

public class VariableUtility {
    public static LinkedList<String> types = new LinkedList<>(){{
        types.add("Game Console");
        types.add("Computer");
        types.add("Mobile Device");
    }};
    public static LinkedList<Integer> launchYear = new LinkedList<>(){{
        for (int year=1950;year<=2024;year++){
            launchYear.add(year);
        }
    }};
    public static LinkedList<String> media = new LinkedList<>(){{
        media.add("Cartridge");
        media.add("Tape");
        media.add("CD");
        media.add("Floppy Disk");
        media.add("Blu-Ray");
    }};

}

