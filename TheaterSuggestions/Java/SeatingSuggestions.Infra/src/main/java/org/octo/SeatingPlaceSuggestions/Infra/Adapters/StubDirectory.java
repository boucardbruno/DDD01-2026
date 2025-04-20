package org.octo.SeatingPlaceSuggestions.Infra.Adapters;

import java.nio.file.Paths;

public class StubDirectory {
    public static String getStubDirectory() {
        var dir = Paths.get(System.getProperty("user.dir")).toAbsolutePath().toString();
        return dir.substring(0, dir.lastIndexOf("\\TheaterSuggestions")) + "\\Stubs\\AuditoriumLayouts";
    }
}
