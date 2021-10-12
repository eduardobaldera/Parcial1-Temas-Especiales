package com.pucmm.fragments.Classes;

public class Concept {
    public static String[] concepts = new String[]{"Activities", "Fragment", "onCreate()"};
    public static String[] description = new String[]{
            "An activity is the entry point for interacting with the user. It represents a single screen with a user interface.",
            "A Fragment represents a behavior or a portion of user interface in a FragmentActivity. You can combine multiple fragments in a single activity to build a multi-pane UI and reuse a fragment in multiple activities.",
            "The system calls this when creating the fragment. Within your implementation, you should initialize essential components of the fragment that you want to retain when the fragment is paused or stopped, then resumed."
    };
}
